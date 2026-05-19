import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    id("com.gradleup.shadow")
}

tasks.named("jar") {
    enabled = false // Application is distributed as a 'shadow jar'
}

tasks.shadowJar {
    manifest {
        attributes(mapOf("Main-Class" to application.mainClass.get()))
    }

    dependencies {
        exclude { dep -> dep.moduleGroup == "org.openjfx" }
    }
    exclude("module-info.class")
    exclude("**/*.txt")
    exclude("**/DEPENDENCIES*")
    exclude("**/LICENSE*")
    exclude("**/licenses/**")
    exclude("**/NOTICE*")

    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    archiveVersion.set(project.version.toString())
}

val buildJpackage = layout.buildDirectory.dir("jpackage")
val inputDir = buildJpackage.map { it.dir("input").asFile }
val javafxModsDir = buildJpackage.map { it.dir("javafx-mods").asFile }
val runtimeImageDir = buildJpackage.map { it.dir("runtime-image").asFile }
val jdkHome = providers.environmentVariable("JAVA_HOME")
    .orElse(providers.provider { System.getProperty("java.home") })
    .map { File(it) }
val shadowJar = tasks.named<ShadowJar>("shadowJar").flatMap { it.archiveFile }

tasks.register<Copy>("prepareJpackageInput") {
    group = "distribution"
    description = "Copy app jar into build/jpackage/input"
    inputs.file(shadowJar)

    from(shadowJar)
    into(inputDir.get())
}

tasks.register<Copy>("prepareJavafxModules") {
    group = "distribution"
    description = "Copy JavaFX jars from runtimeClasspath into build/jpackage/javafx-mods"
    dependsOn(tasks.named("jar"))

    from(configurations.runtimeClasspath) {
        include { file ->
            val name = file.name.lowercase()
            (name.startsWith("javafx") || name.startsWith("openjfx"))
        }
    }
    into(javafxModsDir.get())
}

tasks.register<Exec>("jlinkCreateRuntime") {
    group = "distribution"
    description = "Run jlink to create a runtime image including JavaFX modules"
    dependsOn("prepareJpackageInput", "prepareJavafxModules")

    val javaxModules = listOf("java.naming", "java.sql")
    val javafxModules = listOf("javafx.controls", "javafx.fxml")

    doFirst {
        val javaHomeDir = jdkHome.get()
        val jlinkExe = File(javaHomeDir, "bin/jlink").absolutePath
        val modulePath = listOf(javafxModsDir.get().absolutePath, File(javaHomeDir, "jmods").absolutePath)
            .joinToString(File.pathSeparator)
        val addModulesCsv = (javaxModules + javafxModules).joinToString(",")
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
        val mainJarName = shadowJar.get().asFile.name
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
