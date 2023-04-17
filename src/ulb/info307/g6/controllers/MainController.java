package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {
    private final int MIN_WIDTH = 716, MIN_HEIGHT = 439;  // Default size of window with contents of 700x400

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
            primaryStage.setMinWidth(MIN_WIDTH);
            primaryStage.setMinHeight(MIN_HEIGHT);
            new WelcomeController(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
