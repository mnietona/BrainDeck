package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Entry point of the program (called by launcher).
 * Is the main controller of the app, with no view associated to it.
 * Creates the first menu / controller with a view (WelcomeController).
 */

public class MainController extends Application {
    final double RATIO_SIZE = 0.75;
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

            // Set default size of window to 75% of user's screen
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getBounds();
            System.out.println("screen dimensions: " + bounds.getWidth() + "," + bounds.getHeight());
            primaryStage.setHeight(bounds.getHeight() * RATIO_SIZE);
            primaryStage.setWidth(bounds.getWidth() * RATIO_SIZE);

            new WelcomeController(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
