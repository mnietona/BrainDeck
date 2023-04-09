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
    private EditCard editCardView;

    public EditCardController(Stage stage, Deck deck) {
        this.stage = stage;
        this.deck = deck;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditCard.fxml"));
            Parent root = loader.load();
            editCardView = loader.getController();
            editCardView.setListener(this);

            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Edit cards in the deck");
            stage.show();
            editCardView.setCardList(deck);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickEditCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null && !editCardView.getQuestionInput().isEmpty() && !editCardView.getAnswerInput().isEmpty()) {
            selectedItem.setQuestion(editCardView.getQuestionInput());
            selectedItem.setAnswer(editCardView.getAnswerInput());
            databaseCard.updateCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
            editCardView.updateQuestionAnswer();
        }
    }

    @Override
    public void clickCreateCard() {
        Card card = getCardEntered();
        if (card.isValid()) {
            deck.addCard(card);
            databaseCard.updateCard(card);
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
        }
        editCardView.clearTextFields();
    }

    @NotNull
    private Card getCardEntered() {
        Card card;
        if (editCardView.cardIsGapFill()) { // Gap-fill card
            card = new CardGapFill(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        } else {
            card = new Card(editCardView.getQuestionInput(), editCardView.getAnswerInput()); // Classic card
        }
        return card;
    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            databaseCard.deleteCard(selectedItem);
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
            editCardView.clearTextFields();
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
