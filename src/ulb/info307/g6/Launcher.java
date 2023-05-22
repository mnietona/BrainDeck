package ulb.info307.g6;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ulb.info307.g6.controllers.MainController;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        Application.launch(MainController.class, args);
    }
}
