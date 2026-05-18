module be.mnt.mediadashboard.desktop {
    requires transitive javafx.graphics; // Already required for be.mnt.mediadashboard.application
    requires javafx.controls;
    requires javafx.fxml;

    // Note: exports is package-scoped.
    // Exporting be.mnt.mediadashboard.desktop does NOT automatically export its sub-packages.
    exports be.mnt.mediadashboard.desktop;
}
