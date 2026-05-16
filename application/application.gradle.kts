plugins {
    application
    id("java-common-conventions")
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "be.mnt.mediadashboard"
version = "0.0.1"

application {
    mainModule.set("be.mnt.mediadashboard.application")
    mainClass.set("be.mnt.mediadashboard.application.SpringApplication")
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics")
}

javafx {
    version = "26.0.1"
    modules = listOf("javafx.graphics") // Change to "javafx.controls" for working example.
}

dependencies {
    implementation(project(":desktop"))
    implementation("org.springframework.boot:spring-boot-starter")
}
