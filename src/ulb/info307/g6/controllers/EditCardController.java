package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.models.CardProbabilities;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.EditCard;

public class EditCardController extends Controller implements EditCard.EditCardListener {
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();
    private Deck deck;
    private EditCard editCardView;

    public EditCardController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/EditCard.fxml", "Edit cards in the deck");
        this.deck = deck;
        editCardView = (EditCard) view;
        editCardView.setCardList(deck);
        editCardView.activateButtons(false);
    }

    @Override
    public void clickEditCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null && !editCardView.atLeastOneInputIsEmpty()) {
            selectedItem.setQuestion(editCardView.getQuestionInput());
            selectedItem.setAnswer(editCardView.getAnswerInput());
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
        }
        editCardView.clearTextFields();
    }

    @Override
    public void clickCreateCard() {
        if (!editCardView.atLeastOneInputIsEmpty()) {
            Card card = getCardEntered();
            if (card.isValid()) {
                // that way the normalization will be coherent
                card.setProbability(deck.isEmpty() ? 1.0 : (1.0 / deck.getSize()));
                deck.addCard(card);
                CardProbabilities.normalizeProbability(deck);
                databaseDeck.updateDeck(deck);
                editCardView.setCardList(deck);
                CardProbabilities.printProbability(deck);
            }
        }
        editCardView.clearTextFields();
    }

    @NotNull
    private Card getCardEntered() {
        if (editCardView.cardIsGapFill()) {
            return new CardGapFill(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        } else {
            return new Card(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        }
    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            CardProbabilities.normalizeProbability(deck);
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
            editCardView.clearTextFields();
            editCardView.activateButtons(false);
            CardProbabilities.printProbability(deck);
        }
    }

    @Override
    public void clickBack() {
        new EditDeckController(stage);
    }
}
