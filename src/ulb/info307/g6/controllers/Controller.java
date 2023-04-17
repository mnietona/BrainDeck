package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.View;
import java.io.IOException;

/**
 * Base class for all controllers.
 * Updates (or creates) window from loaded view + title and sets the listener.
 */
public class Controller {
    protected final Stage stage;
    protected View view;
    private final int WIDTH_OVERHEAD = 16, HEIGHT_OVERHEAD = 39;  // Window border size

    public Controller(Stage stage, String fxmlPath, String title) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            view = loader.getController(); // To get the controller of the new menu
            view.setListener(this);

            // Window will not reset size when changing menu
            Scene scene = new Scene(root, stage.getWidth()-WIDTH_OVERHEAD, stage.getHeight()-HEIGHT_OVERHEAD);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
