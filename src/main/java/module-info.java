module be.mnt.mediadashboard {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    opens be.mnt.mediadashboard to javafx.fxml;
}
