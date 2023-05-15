package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point of the program (called by launcher).
 * Is the main controller of the app, with no view associated to it.
 * Creates the first menu / controller with a view (WelcomeController).
 */
public class MainController extends Application {
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
            primaryStage.getIcons().add(new Image("/ulb/info307/g6/views/images/mainlogo.png"));
            new WelcomeController(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
