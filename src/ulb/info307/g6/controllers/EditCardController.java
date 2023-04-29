package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.EditCard;

import ulb.info307.g6.models.DeckProbabilities;

public class EditCardController extends Controller implements EditCard.EditCardListener {
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();  // The deck database implementation
    private final DeckProbabilities deck;  // The deck being edited
    private final EditCard editCardView;  // The view for editing a card

    public EditCardController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/EditCard.fxml", "Edit cards in the deck");
        this.deck = new DeckProbabilities(deck);
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
                deck.normalizeProbability();
                databaseDeck.updateDeck(deck);
                editCardView.setCardList(deck);
                deck.printProbability();
            }
        }
        editCardView.clearTextFields();
    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            deck.normalizeProbability();
            databaseDeck.updateDeck(deck);
            editCardView.setCardList(deck);
            editCardView.clearTextFields();
            editCardView.activateButtons(false);
            deck.printProbability();
        }
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
    public void clickBack() {
        new EditDeckController(stage);
    }
}
