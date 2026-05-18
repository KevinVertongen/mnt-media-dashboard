module be.mnt.mediadashboard.desktop {
    requires transitive javafx.graphics; // Already required for be.mnt.mediadashboard.application
    requires javafx.controls;
    requires javafx.fxml;

    exports be.mnt.mediadashboard.desktop;
}
