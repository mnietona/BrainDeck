package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.views.EditCard;

import java.io.IOException;


public class EditCardController implements EditCard.EditCardMenuListener {

    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();

    private final Stage stage;
    private final Listener listener;
    private Deck deck;
    private EditCard editCard;

    private final String GAP_FILL_MARKER = "/gapfill ";

    public EditCardController(Stage stage, Listener listener, Deck deck) {
        this.stage = stage;
        this.listener = listener;
        this.deck = deck;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditCard.fxml"));
            loader.load();
            editCard = loader.getController();
            editCard.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root));
            this.stage.setTitle("Edit cards in the deck");
            this.stage.show();
            editCard.setCardList(deck);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickEditCard() {
        Card selectedItem = editCard.getSelectedCard();
        if (selectedItem != null && !editCard.getQuestionInput().isEmpty() && !editCard.getAnswerInput().isEmpty()) {
            selectedItem.setQuestion(editCard.getQuestionInput());
            selectedItem.setAnswer(editCard.getAnswerInput());
            databaseCard.updateCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCard.setCardList(deck);
            editCard.updateQuestionAnswer();
        }
    }

    @Override
    public void clickCreateCard() {
        Card card = getCardEntered();
        if (card.isValid()) {
            deck.addCard(card);
            databaseCard.updateCard(card);
            databaseDeck.updateDeck(deck);
            editCard.setCardList(deck);
        }
        editCard.clearTextFields();
    }

    @NotNull
    private Card getCardEntered() {
        Card card;
        if (editCard.cardIsGapFill()) { // Gap-fill card
            String cleanQuestion = editCard.getQuestionInput().replaceAll(GAP_FILL_MARKER, "") + " "; // replaceAll to remove the marker, space at the end for split subtleties when the separator is at the end
            card = new CardGapFill(cleanQuestion, editCard.getAnswerInput());
        } else {
            card = new Card(editCard.getQuestionInput(), editCard.getAnswerInput()); // Classic card
        }
        return card;
    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCard.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            databaseCard.deleteCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCard.setCardList(deck);
            editCard.clearTextFields();
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
