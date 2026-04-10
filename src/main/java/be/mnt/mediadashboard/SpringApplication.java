package be.mnt.mediadashboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    static void main(String[] args) {
        javafx.application.Application.launch(JavaFXApplication.class, args);
    }

}
