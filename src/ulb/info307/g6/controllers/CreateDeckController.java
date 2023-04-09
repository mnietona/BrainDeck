package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.CreateDeck;

import java.io.IOException;

public class CreateDeckController implements CreateDeck.CreateDeckMenuListener {

    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database

    private CreateDeck createDeckView;

    private final Listener listener;

    public CreateDeckController(Stage stage, Listener listener) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/CreateDeck.fxml"));
            Parent root = loader.load();
            createDeckView = loader.getController();
            createDeckView.setListener(this);

            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Create a new deck");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickBack() {
        new EditDeckController(stage);
    }

    @Override
    public void clickConfirm(String inputText) {
        Deck deck = new Deck(inputText); // Create a new deck with the user input
        database.addDeck(deck); // Add the deck to the database
        database.updateDeck(deck); // Update the deck in the database
        clickBack();
    }

    public interface Listener {
        void clickBack();
    }
}
