package be.mnt.mediadashboard.application;

import be.mnt.mediadashboard.desktop.DashboardStage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class JavaFXApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(JavaFXApplication.class);
    private DashboardStage dashboardStage;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(SpringApplication.class).run(getArguments());
    }

    private String[] getArguments() {
        final List<String> argumentList = getParameters().getRaw();
        final int argumentSize = argumentList.size();
        return argumentList.toArray(new String[argumentSize]);
    }

    @Override
    public void stop() {
        applicationContext.close();
        dashboardStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("primaryStage: " + primaryStage);
        this.dashboardStage = new DashboardStage(primaryStage);
        this.dashboardStage.show();
    }
}
