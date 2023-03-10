package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EditMenu {
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonEdit;

    @FXML
    protected void clickEdit() {
        System.out.println("Edit");
    }

    @FXML
    private Button buttonAdd;

    @FXML
    protected void clickAdd() {
        System.out.println("Add");
    }

    @FXML
    private Button buttonRemove;

    @FXML
    protected void clickRemove() {
        System.out.println("Remove");
    }

    public void backButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/MainMenu.fxml"));
            Parent root = loader.load();
            MainMenu mainMenu = loader.getController();
            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

