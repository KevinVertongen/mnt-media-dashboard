package be.mnt.mediadashboard;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(SpringApplication.class).run();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
