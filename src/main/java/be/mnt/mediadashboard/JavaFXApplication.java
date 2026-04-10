package be.mnt.mediadashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class JavaFXApplication extends Application {

    private static Stage stage;

    private ConfigurableApplicationContext applicationContext;

    private final Resource fxmlDashboard = new ClassPathResource("./fxml/dashboard.fxml");

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(SpringApplication.class).run();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        stage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(fxmlDashboard.getURL());
        final Scene scene = new Scene(root);

        stage = primaryStage;
        stage.setTitle("Media Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
