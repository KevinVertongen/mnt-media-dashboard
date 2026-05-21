import org.springframework.boot.gradle.tasks.bundling.BootJar
import utils.isJavafxJar

plugins {
    application
    id("java-common-conventions")
    id("javafx-common-conventions")
    id("jpackage-common-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

application {
    mainClass.set("be.mnt.mediadashboard.application.SpringApplication")
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED,javafx.graphics")
}

tasks.named<BootJar>("bootJar") {
    setClasspath(
        files(classpath?.filter { file ->
            !isJavafxJar(file)
        })
    )

    archiveBaseName.set(rootProject.name)
    archiveVersion.set(project.version.toString())
    archiveClassifier.set("boot")
}

val projectPropertiesProvider = providers.provider {
    project.properties.mapValues { it.value?.toString() ?: "" }
}

tasks.named<ProcessResources>("processResources") {
    filesMatching("application.yaml") {
        expand(projectPropertiesProvider.get())
    }
}

dependencies {
    implementation(project(":desktop"))

    implementation("jakarta.cdi:jakarta.cdi-api:5.0.0.Alpha5")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.5")
    implementation("com.fasterxml:classmate:1.7.3")
    implementation("net.bytebuddy:byte-buddy:1.18.7")
    implementation("org.jboss.logging:jboss-logging:3.6.3.Final")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-community-dialects:7.3.0.Final")

    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
}
