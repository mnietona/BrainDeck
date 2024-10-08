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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            view = loader.getController();
            view.setListener(this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error, could not load fxml file"); // It happens here that if we make an error in the code (Wrong path fxml)
        }
    }
}
