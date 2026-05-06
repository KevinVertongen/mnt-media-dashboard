plugins {
    application
    java
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "be.mnt"
version = "0.0.1"

application {
    mainModule.set("be.mnt.mediadashboard")
    mainClass.set("be.mnt.mediadashboard.SpringApplication")
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

javafx {
    version = "26"
    modules = listOf("javafx.controls", "javafx.fxml")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-community-dialects:7.3.0.Final")
    runtimeOnly("org.xerial:sqlite-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
