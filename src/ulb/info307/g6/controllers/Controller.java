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
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Size of window will not reset when navigating trough menus
            stage.setWidth(previousWidth);
            stage.setHeight(previousHeight);

            // Sets minimum size of the window to 480p (720x480), accounting for the (os-dependent) window overhead
            double widthOverhead = stage.getWidth() - scene.getWidth();
            double heightOverhead = stage.getHeight() - scene.getHeight();
            stage.setMinWidth(720 + widthOverhead);
            stage.setMinHeight(480 + heightOverhead);

            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
