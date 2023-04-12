package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.CardProbabilities;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.EditDeck;

public class EditDeckController extends ControllerWithDeckList implements EditDeck.EditDeckListener {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    private EditDeck editDeckView;
    private CardProbabilities cardProbabilities = new CardProbabilities();

    public EditDeckController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/EditDeck.fxml", "Edit Deck");
        editDeckView = (EditDeck) view;
        editDeckView.activateActionButtons(false);
    }

    @Override
    public void deckSelected(){
        editDeckView.updateDeckTitle();
        editDeckView.activateActionButtons(true);
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
            editDeckView.updateDeckTitle();
            editDeckView.activateActionButtons(false);
        }
    }

    @Override
    public void clickResetProba() {
        if (editDeckView.isDeckSelected()) {
            cardProbabilities.resetProbability(editDeckView.getSelectedDeck());
            database.updateDeck(editDeckView.getSelectedDeck());
        }
    }
}