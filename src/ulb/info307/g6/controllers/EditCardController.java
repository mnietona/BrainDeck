package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.views.EditCard;

public class EditCardController extends Controller implements EditCard.EditCardListener {
    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();
    private Deck deck;
    private EditCard editCardView;

    public EditCardController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/EditCard.fxml", "Edit cards in the deck");
        this.deck = deck;
        editCardView = (EditCard) view;
        editCardView.setCardList(deck);
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
        return new Card(editCardView.getQuestionInput(), editCardView.getAnswerInput());
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
        new EditDeckController(stage);
    }
}
