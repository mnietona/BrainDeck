package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.database.DeckDaoInterface;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.ViewWithDeckList;

/**
 * Base class for all controllers that have a deck list.
 */
public class ControllerWithDeckList extends Controller {
    final DeckDaoInterface database = new DeckDaoNitriteImplementation();  // Initialize the DAO for the database
    protected final ViewWithDeckList viewWithDeckList;

    public ControllerWithDeckList(Stage stage, String fxmlPath, String title) {
        super(stage, fxmlPath, title);
        viewWithDeckList = (ViewWithDeckList) view;
        setDeckList();
    }

    /**
     * Sets the deck list in the view with all the decks from the database.
     */
    protected void setDeckList() {
        viewWithDeckList.setDeckListView(database.getAllDecks());
    }
}
