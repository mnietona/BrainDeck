package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.views.EditCardMenu;
import java.io.IOException;


public class EditCardMenuController implements EditCardMenu.EditCardMenuListener {

    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();

    private final Stage stage;
    private final Listener listener;
    private Deck deck;
    private EditCardMenu editCardMenu;

    private final String GAP_FILL_MARKER = "/gapfill ";

    public EditCardMenuController(Stage stage, Listener listener, Deck deck) {
        this.stage = stage;
        this.listener = listener;
        this.deck = deck;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditCardMenu.fxml"));
            loader.load();
            editCardMenu = loader.getController();
            editCardMenu.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root));
            this.stage.setTitle("Edit cards in the deck");
            this.stage.show();
            editCardMenu.setCardList(deck);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickEditCard() {
        Card selectedItem = editCardMenu.getSelectedCard();
        if (selectedItem != null && !editCardMenu.getQuestionInput().isEmpty() && !editCardMenu.getAnswerInput().isEmpty()) {
            selectedItem.setQuestion(editCardMenu.getQuestionInput());
            selectedItem.setAnswer(editCardMenu.getAnswerInput());
            databaseCard.updateCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCardMenu.setCardList(deck);
            editCardMenu.updateQuestionAnswer();
        }
    }

    @Override
    public void clickCreateCard() {
        Card card;
        if (editCardMenu.getQuestionInput().contains(GAP_FILL_MARKER)) { // Gap-fill card
            String cleanQuestion = editCardMenu.getQuestionInput().replaceAll(GAP_FILL_MARKER, "") + " "; // replaceAll to remove the marker, space at the end for split subtleties when the separator is at the end
            card = new CardGapFill(cleanQuestion, editCardMenu.getAnswerInput());
        } else {
            card = new Card(editCardMenu.getQuestionInput(), editCardMenu.getAnswerInput()); // Classic card
        }
        if (card.isValid()) {
            deck.addCard(card);
            databaseCard.updateCard(card);
            databaseDeck.updateDeck(deck);
            editCardMenu.setCardList(deck);
        }
        editCardMenu.clearTextFields();
    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCardMenu.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            databaseCard.deleteCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCardMenu.setCardList(deck);
            editCardMenu.clearTextFields();
        }
    }


    @Override
    public void clickBack() {
        listener.clickBack();
        this.stage.hide();
    }

    public interface Listener {
        void clickBack();
    }
}
