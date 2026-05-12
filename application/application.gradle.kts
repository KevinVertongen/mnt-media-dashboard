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
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics,org.xerial.sqlitejdbc")
}

javafx {
    version = "26.0.1"
    modules = listOf("javafx.controls")
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

    runtimeOnly("org.xerial:sqlite-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
}
