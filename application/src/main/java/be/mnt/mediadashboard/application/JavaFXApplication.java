package be.mnt.mediadashboard.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

public class JavaFXApplication extends Application {

    private static Stage stage;

    private ConfigurableApplicationContext applicationContext;

    private final URL fxmlDashboard = ClassLoader.getSystemResource("fxml/dashboard.fxml");

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(SpringApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        stage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(fxmlDashboard);
        final Scene scene = new Scene(root);

        stage = primaryStage;
        stage.setTitle("Media Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
