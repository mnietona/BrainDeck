package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MainMenu {

    @FXML
    public Button playButton;

    @FXML
    public Button editButton;

    public void studyButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Study"
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
            Parent root = loader.load();
            ChooseDeckPlay chooseDeckPlay = loader.getController(); // to get the controller of the new window
            Scene scene = new Scene(root, 600, 408);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Study your decks");
            stage.show();
            chooseDeckPlay.showChoice();
            closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Edit"
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
            Parent root = loader.load();
            // EditMenu editMenu = loader.getController(); // to get the controller of the new window
            Scene scene = new Scene(root, 600, 408);
            Stage stage = new Stage();
            stage.setTitle("Edit your decks");
            stage.setScene(scene);
            stage.show();
            closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow() {
        // to close the window when a new window is call.
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
    }



}

