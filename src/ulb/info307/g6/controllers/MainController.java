package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {

    /**
     * Entry point of the program, called by launcher.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Entry point of the JavaFX app.
     *
     * @param primaryStage Window of the app, passed to all controllers.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            new WelcomeController(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
