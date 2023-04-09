package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.Welcome;
import javafx.scene.control.Alert;
import java.io.IOException;

/**
 * Main menu (called Welcome) controller. Creates window and handles
 */
public class WelcomeController implements Welcome.WelcomeListener {
    private final Stage stage;

    public WelcomeController(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/Welcome.fxml"));
            Parent root = loader.load();
            Welcome welcomeViewController = loader.getController(); // to get the controller of the new window
            welcomeViewController.setListener(this);
            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Menu");
            stage.show();

        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText("An error occurred while loading the window. Please try again.");
        alert.showAndWait();
    }

    @Override
    public void studyButtonAction() {
        stage.hide();
        //Stage stage = new Stage();
        //StudyController StudyController = new StudyController(stage, this);
        //StudyController.show();
    }

    @Override
    public void editButtonAction() {
        stage.hide();
        //Stage stage = new Stage();
        //EditDeckController editDeckController = new EditDeckController(stage, this);
        //editDeckController.show();
    }
}

