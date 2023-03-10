package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MainMenu {

    @FXML
    public Button playButton;

    @FXML
    public Button editButton;

    public void playButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Play"
    }

    public void editButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Edit"
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
            Parent root = loader.load();
            EditMenu editMenu = loader.getController();
            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
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

