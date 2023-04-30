package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import org.w3c.dom.Element;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

import java.util.Base64;
import java.util.Iterator;

/**
 * View controller of the EditCard menu, implements View interface and is the controller for the EditCard.fxml file.
 * Contains :
 * - a list view to select a card,
 * - a back button, and buttons to create a new card or edit/remove the selected card
 * - two text areas to enter the question and the answer of the card
 * - a checkbox to select if the card is a gap fill card when creating/editing a card
 */
public class EditCard implements View {
    private Stage preview_window = null;
    private boolean preview = false;
    private WebView web_preview = null;
    @FXML
    private ListView<Card> cardListView = new ListView<>();
    @FXML
    private CheckBox gapFillCheckBox;
    @FXML
    private TextArea questionInput, answerInput;
    @FXML
    private Button buttonEditCard, buttonCreateCard, buttonRemoveCard, button_preview;
    private EditCardListener listener;

    @Override
    public void setListener(Object listener) {
        this.listener = (EditCardListener) listener;
    }

    @FXML
    public void clickBack() {
        listener.clickBack();
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

    private String get_page_url(String text) {
        String page_url = getClass().getResource("showCard.html").toExternalForm();
        page_url += "?text=" + Base64.getUrlEncoder().encodeToString(text.getBytes()); // Encode the text in base64 to avoid problems with special characters
        return page_url;
    }

    @FXML
    private void click_preview() {
        //TODO: move this with the other relevant code, this is just a proof of concept
        //TODO: really "hacky" code for now, needs cleanup
        if (preview) {
            web_preview = null;
            preview_window = null;
            preview = false;
            return;
        } else {
            preview = true;
            web_preview = new WebView();
            web_preview.getEngine().load(get_page_url("c"));
            VBox vbox = new VBox(web_preview);
            vbox.setAlignment(Pos.CENTER);
            Scene secondScene = new Scene(vbox, 600, 600);
            Stage preview_window = new Stage();
            preview_window.setTitle("Check your latex preview!"); //le preview sert a l'utilisateur pour verifier que le latex est correct
            preview_window.setScene(secondScene);
            preview_window.show();
        }
    }

    /**
     * Method called when input changes in the question or answer text areas.
     */
    @FXML
    private void inputChange() {
        if (atLeastOneInputIsEmpty()) {
            activateEditButton(false);
            activateCreateButton(false);
        } else {
            activateCreateButton(true);
            if (isCardSelected()) {
                activateEditButton(true);
                if (preview) {
                    web_preview.getEngine().load(get_page_url("showCard.html"));
                }
            }
        }
    }

    /**
     * Method to set the card list view with the cards from the given deck.
     */
    public void setCardList(Deck deck) {
        cardListView.getItems().clear();
        Iterator<Card> cardIterator = deck.getCardIterator();
        while (cardIterator.hasNext()) {
            cardListView.getItems().add(cardIterator.next());
        }
        cardListView.setOnMouseClicked(event -> {
            updateQuestionAnswer();
            if (isCardSelected()) activateButtons(true);
        });
    }

    private boolean isCardSelected() {
        return cardListView.getSelectionModel().getSelectedItem() != null;
    }

    public void updateQuestionAnswer() {
        Card selectedCard = cardListView.getSelectionModel().getSelectedItem();
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
        activateButtons(false);
    }

    public void activateRemoveButton(boolean activate) {
        buttonRemoveCard.setDisable(!activate);
    }

    public void activateEditButton(boolean activate) {
        buttonEditCard.setDisable(!activate);
    }

    public void activateCreateButton(boolean activate) {
        buttonCreateCard.setDisable(!activate);
    }

    public void activateButtons(boolean activate) {
        activateRemoveButton(activate);
        activateEditButton(activate);
        activateCreateButton(activate);
    }

    public String getQuestionInput() {
        return questionInput.getText();
    }

    public String getAnswerInput() {
        return answerInput.getText();
    }

    public boolean atLeastOneInputIsEmpty() {
        return questionInput.getText().isEmpty() || answerInput.getText().isEmpty();
    }

    public Card getSelectedCard() {
        return cardListView.getSelectionModel().getSelectedItem();
    }

    public boolean cardIsGapFill() {
        return gapFillCheckBox.isSelected();
    }


    public interface EditCardListener {
        void clickBack();
        void clickEditCard();
        void clickCreateCard();
        void clickRemoveCard();
    }
}
