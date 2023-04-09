package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.Welcome;
import java.io.IOException;

/**
 * Main menu (called Welcome) controller. Creates window and handles
 */
public class WelcomeController implements Welcome.WelcomeListener {
    private final Stage stage;

    public WelcomeController(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/Welcome.fxml"));
            Parent root = loader.load();
            Welcome welcomeView = loader.getController(); // to get the controller of the new window
            welcomeView.setListener(this);

            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Menu");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void studyButtonAction() {
        new StudyController(stage);
    }

    @Override
    public void editButtonAction() {
        new EditDeckController(stage);
    }
}
