package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditDeck;
import java.util.List;

public class EditDeckController extends Controller implements EditDeck.EditDeckListener {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    private EditDeck editDeckView;

    public EditDeckController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/EditDeck.fxml", "Edit Deck");
        editDeckView = (EditDeck) view;
        setDeckList();
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickEdit() {
        List<Deck> decks = database.getAllDecks();
        if (decks != null) {
            Deck selectedItem = editDeckView.deckListViewEditDeck.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new EditCardController(stage, selectedItem);
            }
        }
    }

    @Override
    public void clickCreate() {
        new CreateDeckController(stage);
    }

    public void setDeckList() {
        List<Deck> decks = database.getAllDecks();
        editDeckView.setDeckListView(decks);
        editDeckView.updateDeckTitle();
    }

    @Override
    public void clickRemove() {
        Deck selectedItem = editDeckView.deckListViewEditDeck.getSelectionModel().getSelectedItem();
        database.deleteDeck(selectedItem);
        database.updateDeck(selectedItem);
        setDeckList();
    }
}
