package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.MainMenu;
import javafx.scene.control.Alert;

import java.io.IOException;

public class MenuController {
    private final Stage stage;
    private final Listener listener;

    public MenuController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/MainMenu.fxml"));
            Parent root = loader.load();
            MainMenu menuController = loader.getController(); // to get the controller of the new window
            menuController.setListener(new MainMenu.MainMenuListener(){
                @Override
                public void studyButtonAction() {
                    listener.studyButtonAction();
                }

                @Override
                public void editButtonAction() {
                    listener.editButtonAction();
                }
            }); // to set the main controller of the new window
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

    public void hide() {
        stage.hide();
    }


    public interface Listener {
        // Define methods that the listener should implement
        void studyButtonAction();
        void editButtonAction();
    }
}

