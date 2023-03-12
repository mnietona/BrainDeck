package ulb.info307.g6.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseDeckPlay {



    @FXML
    private Button backButton;

    @FXML
    private Button deck1;




//Todo mettre des hbox pour pouvoir amplifier


    @FXML
    void backClick(ActionEvent event) {
        accessNewWindow("/ulb/info307/g6/views/MainMenu.fxml");

    }
    @FXML
    void clickDeck1(ActionEvent event) {
        //accessNewWindow("/ulb/info307/g6/views/PlayDeck.fxml");

    }



    public void accessNewWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            //MainMenu newWindowMenu = loader.getController();
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }









