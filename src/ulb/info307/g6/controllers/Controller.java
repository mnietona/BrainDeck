package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.View;
import java.io.IOException;

/**
 * Base class for all controllers.
 * Updates window content from loaded view + title, and sets the listener.
 */
public class Controller {
    protected final Stage stage;
    protected View view;

    public Controller(Stage stage, String fxmlPath, String title) {
        this.stage = stage;
        double previousWidth = stage.getWidth(), previousHeight = stage.getHeight();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            view = loader.getController();  // To get the controller of the new menu
            view.setListener(this);

            System.out.println("Previous dimensions: " + previousWidth + "," + previousHeight);
            Scene scene = new Scene(root);
            System.out.println("before setScene scene dimensions: " + scene.getWidth() + ","+scene.getHeight());
            stage.setScene(scene);
            // set height and width of the scene to the stage

            System.out.println("stage dimensions: " + stage.getWidth() + " " + stage.getHeight());
            System.out.println("after setScene scene dimensions: " + scene.getWidth() + ","+scene.getHeight());

            // Size of window will not reset when navigating trough menus
            stage.setWidth(previousWidth);
            stage.setHeight(previousHeight);

            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
