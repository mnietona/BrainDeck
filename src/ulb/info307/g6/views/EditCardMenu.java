package ulb.info307.g6.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

import java.util.List;

public class EditCardMenu {
    @FXML
    public ListView<Card> cardListViewEditCardMenu = new ListView();
    private Deck deck;

    private EditCardMenuListener listener;

    @FXML
    private TextArea questionInput;

    @FXML
    private TextArea answerInput;

    @FXML
    private Button buttonRemoveCard;
    @FXML
    private Button buttonEditCard;


    @FXML
    private Button buttonCreateCard;

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

    public void setCardLists(Deck deck) {
        cardListViewEditCardMenu.getItems().clear();
        for (Card card : deck.getCardList()) {
            cardListViewEditCardMenu.getItems().add(card);
            System.out.println(card);
        }
        cardListViewEditCardMenu.setOnMouseClicked(event -> {
            updateQuestionAnswer();
        });
    }

    public void updateQuestionAnswer() {
        Card selectedItem = cardListViewEditCardMenu.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            questionInput.setText(selectedItem.getQuestion());
            answerInput.setText(selectedItem.getAnswer());
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
        void clickChoice();
        void clickRemoveCard();
        void clickBack();
    }
}
