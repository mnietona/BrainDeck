package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.CreateDeck;

public class CreateDeckController extends Controller implements CreateDeck.CreateDeckListener {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();  // Initialize the DAO for the database
    private CreateDeck createDeckView;

    public CreateDeckController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/CreateDeck.fxml", "Create a new deck");
        createDeckView = (CreateDeck) view;
        createDeckView.activateButton(false);  // Disable the confirm button until the user inputs a deck name
    }

    @Override
    public void clickBack() {
        new EditDeckController(stage);  // Create a new EditDeckController when the back button is clicked
    }

    @Override
    public void clickConfirm(String inputText) {
        Deck deck = new Deck(inputText);  // Create a new deck with the user input
        database.addDeck(deck);  // Add the deck to the database
        database.updateDeck(deck);  // Update the deck in the database
        clickBack();
    }
}
