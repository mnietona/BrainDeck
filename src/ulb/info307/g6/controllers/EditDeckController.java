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

    public void setDeckList() {
        editDeckView.setDeckListView(database.getAllDecks());
        editDeckView.updateDeckTitle();
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickEdit() {
        if (editDeckView.isDeckSelected()) {
            new EditCardController(stage, editDeckView.getSelectedDeck());
        }
    }

    @Override
    public void clickCreate() {
        new CreateDeckController(stage);
    }

    @Override
    public void clickRemove() {
        if (editDeckView.isDeckSelected()) {
            database.deleteDeck(editDeckView.getSelectedDeck());
            database.updateDeck(editDeckView.getSelectedDeck());
            setDeckList();
        }
    }
}
