import org.gradle.jvm.tasks.Jar

plugins {
    application
}

val buildJpackage = layout.buildDirectory.dir("jpackage")
val inputDir = buildJpackage.map { it.dir("input").asFile }
val javafxModsDir = buildJpackage.map { it.dir("javafx-mods").asFile }
val runtimeImageDir = buildJpackage.map { it.dir("image").asFile }
val jdkHome = providers.environmentVariable("JAVA_HOME")
    .orElse(providers.provider { System.getProperty("java.home") })
    .map { File(it) }
val appJar = tasks.named<Jar>("jar").flatMap { it.archiveFile }

fun isJavafxJar(file: File): Boolean {
    val name = file.name.lowercase()
    return name.startsWith("javafx") || name.startsWith("openjfx")
}

tasks.register<Copy>("prepareJpackageInput") {
    group = "distribution"
    description = "Copy app jar and non-JavaFX runtime jars into build/jpackage/input"
    dependsOn(":desktop:jar", tasks.named("jar"))

    from(appJar.map { it.asFile }) {
        into("")
    }

    from({
        configurations.getByName("runtimeClasspath").resolvedConfiguration.resolvedArtifacts
            .map { it.file }
            .filter { file -> !isJavafxJar(file) }
    }) {
        into("")
    }

    into(inputDir.get())
}

tasks.register<Copy>("prepareJavafxModules") {
    group = "distribution"
    description = "Copy JavaFX jars from runtimeClasspath into build/jpackage/javafx-mods"
    dependsOn(tasks.named("jar"))

    from({
        configurations.getByName("runtimeClasspath").resolvedConfiguration.resolvedArtifacts
            .map { it.file }
            .filter { file -> isJavafxJar(file) }
    }) {
        into("")
    }

    into(javafxModsDir.get())
}

tasks.register<Exec>("jlinkCreateRuntime") {
    group = "distribution"
    description = "Run jlink to create a runtime image including JavaFX modules"
    dependsOn("prepareJpackageInput", "prepareJavafxModules")

    doFirst {
        val javaHomeDir = jdkHome.get()
        val jlinkExe = File(javaHomeDir, "bin/jlink").absolutePath
        val modulePath = listOf(javafxModsDir.get().absolutePath, File(javaHomeDir, "jmods").absolutePath)
            .joinToString(File.pathSeparator)
        val addModulesCsv = listOf("javafx.controls", "javafx.fxml").joinToString(",")
        val output = runtimeImageDir.get().absolutePath

        commandLine = listOf(
            jlinkExe,
            "--module-path", modulePath,
            "--add-modules", addModulesCsv,
            "--output", output,
            "--strip-debug",
            "--compress", "zip-6",
            "--no-header-files",
            "--no-man-pages"
        )
    }
}

tasks.register<Exec>("jpackageCreateInstaller") {
    group = "distribution"
    description = "Run jpackage to create an installer using the runtime image"
    dependsOn("jlinkCreateRuntime")

    doFirst {
        val javaHomeDir = jdkHome.get()
        val jpackageExe = File(javaHomeDir, "bin/jpackage").absolutePath
        val input = inputDir.get().absolutePath
        val runtimeImg = runtimeImageDir.get().absolutePath
        val mainJarName = appJar.get().asFile.name
        val mainClass = application.mainClass.get()
        val outDir = buildJpackage.map { it.dir("output").asFile }.get().absolutePath

        commandLine = mutableListOf<String>().apply {
            add(jpackageExe)
            add("--win-console")
            addAll(listOf("--type", "app-image")) // change per platform: msi, dmg, pkg, deb, rpm
            addAll(listOf("--name", "MediaDashboard"))
            addAll(listOf("--app-version", version.toString()))
            addAll(listOf("--input", input))
            addAll(listOf("--main-jar", mainJarName))
            addAll(listOf("--main-class", mainClass))
            addAll(listOf("--runtime-image", runtimeImg))
            addAll(listOf("--dest", outDir))
            addAll(listOf("--java-options", "--enable-native-access=ALL-UNNAMED,javafx.graphics"))
            // add additional flags (icon, vendor, resource-dir) as needed
        }
    }
}
