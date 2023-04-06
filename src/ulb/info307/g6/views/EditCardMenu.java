package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

public class EditCardMenu {
    @FXML
    public ListView<Card> cardListViewEditCardMenu = new ListView();
    private EditCardMenuListener listener;
    @FXML
    private TextArea questionInput;
    @FXML
    private TextArea answerInput;

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
    private void clickRemoveCard() {
        listener.clickRemoveCard();
    }

    @FXML
    private void backButtonAction() {
        listener.clickBack();
    }

    public void setCardList(Deck deck) {
        cardListViewEditCardMenu.getItems().clear();
        for (Card card : deck.getCardList()) {
            cardListViewEditCardMenu.getItems().add(card);
        }
        cardListViewEditCardMenu.setOnMouseClicked(event -> {
            updateQuestionAnswer();
        });
    }

    public void updateQuestionAnswer() {
        Card selectedCard = cardListViewEditCardMenu.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            questionInput.setText(selectedCard.getQuestion());
            answerInput.setText(selectedCard.getAnswer());
        } else {
            clearTextFields();
        }
    }

    public void clearTextFields() {
        questionInput.setText("");
        answerInput.setText("");
    }

    public String getQuestionInput() {
        return questionInput.getText();
    }

    public String getAnswerInput() {
        return answerInput.getText();
    }

    public Card getSelectedCard() {
        return cardListViewEditCardMenu.getSelectionModel().getSelectedItem();
    }

    public void clickBack() {
        listener.clickBack();
    }

    public interface EditCardMenuListener {
        void clickEditCard();
        void clickCreateCard();
        void clickRemoveCard();
        void clickBack();
    }
}
