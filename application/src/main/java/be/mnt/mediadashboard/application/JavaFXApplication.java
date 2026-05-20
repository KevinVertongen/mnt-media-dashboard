package be.mnt.mediadashboard.application;

import be.mnt.mediadashboard.desktop.DashboardStage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private DashboardStage dashboardStage;

    @Override
    public void init() {
        applicationContext = org.springframework.boot.SpringApplication.run(SpringApplication.class, getArguments());
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
        this.dashboardStage = new DashboardStage(primaryStage);
        this.dashboardStage.show();
    }
}
