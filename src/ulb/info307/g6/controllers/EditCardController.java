package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.models.*;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.views.EditCard;
import ulb.info307.g6.views.Popup;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class EditCardController extends ControllerWithCardList implements EditCard.EditCardListener, PreviewCardController.PreviewCardControllerListener {
    static DeckDaoNitriteImplementation databaseDeck = new DeckDaoNitriteImplementation();  // The deck database implementation
    private final DeckProbabilities deck;  // The deck being edited
    private final EditCard editCardView; // The view for editing a card
    private PreviewCardController previewCardController;
    private boolean previewEnabled = false;


    public EditCardController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/EditCard.fxml", "Edit cards in the deck " + deck.getName(),deck);
        this.deck = new DeckProbabilities(deck);
        editCardView = (EditCard) view;
        editCardView.activateButtons(false);
    }

    @Override
    public void clickEditCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null && !editCardView.atLeastOneInputIsEmpty()) {
            selectedItem.setQuestion(editCardView.getQuestionInput());
            selectedItem.setAnswer(editCardView.getAnswerInput());
            if (editCardView.cardIsGapFill()) {
                selectedItem.setCardType(CardTypes.GAP_FILL);
            } else if (editCardView.cardIsQCM()) {
                selectedItem.setCardType(CardTypes.QCM);
            } else {
                selectedItem.setCardType(CardTypes.NORMAL);
            }
            databaseDeck.updateDeck(deck);
            editCardView.setCardListView(deck);
        }
        editCardView.clearTextFields();
    }

    private String getPageUrl() {
        String page_url = "http://localhost:14757/PreviewCard.html";
        page_url += "?q=" + Base64.getUrlEncoder().encodeToString(editCardView.getQuestionInput().getBytes(StandardCharsets.UTF_8)); // Encode the text in base64 to avoid problems with special characters
        page_url += "&a=" + Base64.getUrlEncoder().encodeToString(editCardView.getAnswerInput().getBytes(StandardCharsets.UTF_8));
        return page_url;
    }


    public void clickPreview() {
        if (previewEnabled) {
            previewCardController.close();
            previewEnabled = false;
        } else {
            previewCardController = new PreviewCardController(getPageUrl(), stage);
            previewCardController.setListener(this);
            previewEnabled = true;
        }
    }

    @Override
    public void cardChanged() {
        if (previewEnabled) {
            previewCardController.changePage(getPageUrl());
        }
    }

    @Override
    public void clickCreateCard() {
        if (!editCardView.atLeastOneInputIsEmpty()) {
            Card card = getCardEntered();
            if (card.isValid()) {
                // that way the normalization will be coherent
                card.setProbability(deck.isEmpty() ? 1.0 : (1.0 / deck.getSize()));
                if (card instanceof CardGapFill) {
                    card.setCardType(CardTypes.GAP_FILL);
                } else if (card instanceof CardQCM) {
                    card.setCardType(CardTypes.QCM);
                } else {
                    card.setCardType(CardTypes.NORMAL);
                }
                deck.addCard(card);
                deck.normalizeProbability();
                databaseDeck.updateDeck(deck);
                editCardView.setCardListView(deck);
                deck.printProbability();
            }
            else {
                String text = "";
                if (card instanceof CardGapFill) {
                    text = """
                            Error, The card is not valid.
                                                        
                            Example of correct usage:
                            Question = The captain of the Titanic was _
                            Answer = Edward Smith
                                
                            For multiple blanks:
                            Question = The primary colors are _, _, and _.
                            Answer = red, blue, yellow
                            """;
                } else if (card instanceof CardQCM) {
                    text = """
                            Error, The card is not valid.
                                                        
                            Example of correct usage:
                            Question = What is the capital of France?
                            Answer = {Paris}, London, Berlin, Rome
                                                        
                            The correct answer is between { }
                            """;
                }
                new Popup(text).showAndWait();
            }
        }

    }

    @Override
    public void clickRemoveCard() {
        Card selectedItem = editCardView.getSelectedCard();
        if (selectedItem != null) {
            deck.removeCard(selectedItem);
            deck.normalizeProbability();
            databaseDeck.updateDeck(deck);
            editCardView.setCardListView(deck);
            editCardView.clearTextFields();
            editCardView.activateButtons(false);
            deck.printProbability();
        }
    }

    @NotNull
    private Card getCardEntered() {
        if (editCardView.cardIsGapFill()) {
            return new CardGapFill(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        } else if (editCardView.cardIsQCM()) {
            return new CardQCM(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        }
        else {
            return new Card(editCardView.getQuestionInput(), editCardView.getAnswerInput());
        }
    }

    @Override
    public void closeRequest() {
        previewEnabled = false;
    }


    @Override
    public void clickBack() {
        if (previewEnabled) {
            previewCardController.close();
        }
        new EditDeckController(stage);
    }
}
