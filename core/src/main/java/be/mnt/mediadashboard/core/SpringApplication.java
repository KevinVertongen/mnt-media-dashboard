package be.mnt.mediadashboard.core;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

}
