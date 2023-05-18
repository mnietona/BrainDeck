package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.database.CardDaoNitriteImplementation;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.ViewWithCardList;

/**
 * Base class for all controllers that have a card list.
 */
public class ControllerWithCardList extends Controller {

    public final CardDaoNitriteImplementation database = new CardDaoNitriteImplementation();  // Initialize the DAO for the database
    protected final ViewWithCardList viewWithCardList;

    public ControllerWithCardList(Stage stage, String fxmlPath, String title, Deck deck) {
        super(stage, fxmlPath, title);
        viewWithCardList = (ViewWithCardList) view;
        setCardList(deck);
    }

    /**
     * Sets the card list in the view with all the cards from the deck.
     */
    protected void setCardList(Deck deck) {
        viewWithCardList.setCardListView(deck);
    }
}
