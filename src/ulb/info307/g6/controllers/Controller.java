package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.View;

import java.io.IOException;

public class Controller {
    protected final Stage stage;
    protected View view;
    public Controller(Stage stage, String fxmlPath, String title) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            view = loader.getController(); // to get the controller of the new window
            view.setListener(this);

            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
