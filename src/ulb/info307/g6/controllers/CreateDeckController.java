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

    private final Stage stage;

    private CreateDeck createDeck;

    private final Listener listener;

    public CreateDeckController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/CreateDeck.fxml"));
            loader.load();
            createDeck = loader.getController();
            createDeck.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root));
            this.stage.setTitle("Create a new deck");
            this.stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickBack() {
        listener.clickBack();
        this.stage.hide();
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
