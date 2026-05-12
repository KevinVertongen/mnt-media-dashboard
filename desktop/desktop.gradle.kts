plugins {
    `java-library`
    id("java-common-conventions")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "be.mnt.mediadashboard"
version = "0.0.1"

javafx {
    version = "26.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {

}
