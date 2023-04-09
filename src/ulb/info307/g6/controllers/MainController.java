package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Start JavaFX.
 */
public class MainController extends Application {  // StudyController.Listener, EditDeckController.Listener

    /**
     * Entry point of the application, called by launcher.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Entry point of JavaFX application, called on launch() in main.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            WelcomeController welcomeController = new WelcomeController(primaryStage);
            welcomeController.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
