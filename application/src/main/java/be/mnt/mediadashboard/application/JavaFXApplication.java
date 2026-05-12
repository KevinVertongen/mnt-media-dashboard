package be.mnt.mediadashboard.application;

import be.mnt.mediadashboard.desktop.DashboardStage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(JavaFXApplication.class);
    private DashboardStage dashboardStage;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(SpringApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        dashboardStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("dashboardStage: " + primaryStage);
        this.dashboardStage = new DashboardStage(primaryStage);
        this.dashboardStage.show();
    }
}
