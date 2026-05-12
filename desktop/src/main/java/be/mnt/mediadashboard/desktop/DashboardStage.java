package be.mnt.mediadashboard.desktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class DashboardStage {

    private final Stage primaryStage;
    private final URL fxmlDashboard = ClassLoader.getSystemResource("fxml/dashboard.fxml");

    public DashboardStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() throws IOException {
        final Parent root = FXMLLoader.load(fxmlDashboard);
        final Scene scene = new Scene(root);

        primaryStage.setTitle("Media Dashboard");
        primaryStage.setScene(scene);
    }

    public void close() {
        primaryStage.close();
    }
}
