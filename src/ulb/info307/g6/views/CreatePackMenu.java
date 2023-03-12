package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.Deck;

import java.io.IOException;

public class CreatePackMenu {
    @FXML
    private TextField inputBox;

    @FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonBack;

    @FXML
    private void clickConfirm() {
        DeckDaoNitriteImplementation ddni = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
        String inputText = inputBox.getText();
        System.out.println("User input: " + inputText);
        Deck deck = new Deck(inputText); // Create a new deck with the user input
        ddni.addDeck(deck); // Add the deck to the database
        ddni.updateDeck(deck); // Update the deck in the database


        accessNewWindow("/ulb/info307/g6/views/EditMenu.fxml");
    }

    @FXML
    private void clickBack() {
        accessNewWindow("/ulb/info307/g6/views/EditMenu.fxml");
    }

    public void accessNewWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            //MainMenu newWindowMenu = loader.getController();
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
