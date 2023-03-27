package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

public class EditCardMenu {

    private Deck deck;

    private EditCardMenuListener listener;

    @FXML
    private TextArea questionInput;

    @FXML
    private TextArea answerInput;

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonEditCard;

    @FXML
    public ComboBox<Card> pickCard;

    @FXML
    private Button buttonAddCard;

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public void setListener(EditCardMenuListener listener) {
        this.listener = listener;
    }

    @FXML
    private void clickEditCard() {
        listener.clickEditCard();
    }

    @FXML
    private void clickCreateCard() {
        listener.clickCreateCard();
    }

    @FXML
    private void clickChoice() {
        listener.clickChoice();
    }

    @FXML
    private void clickRemoveCard() {
        listener.clickRemoveCard();
    }

    @FXML
    private void backButtonAction() {
        listener.clickBack();
    }

    public void setCardLists() {
        pickCard.getItems().clear();
        for (Card card : deck.getCardList()) {
            pickCard.getItems().add(card);
        }
        pickCard.setOnAction(event -> { // click on an item
            updateQuestionAnswer();
        });
    }

    public void updateQuestionAnswer() {
        Card selectedItem = pickCard.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            questionInput.setText(selectedItem.getQuestion());
            answerInput.setText(selectedItem.getAnswer());
        } else {
            questionInput.setText("");
            answerInput.setText("");
        }
    }

    public String getQuestionInput() {
        return questionInput.getText();
    }

    public String getAnswerInput() {
        return answerInput.getText();
    }

    public Card getSelectedCard() {
        return pickCard.getSelectionModel().getSelectedItem();
    }

    public interface EditCardMenuListener {
        void clickEditCard();
        void clickCreateCard();
        void clickChoice();
        void clickRemoveCard();
        void clickBack();
    }
}
