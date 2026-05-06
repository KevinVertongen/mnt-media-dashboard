plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.jar {
    archiveFileName = "${rootProject.name}-${project.name}-${project.version}.jar"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
