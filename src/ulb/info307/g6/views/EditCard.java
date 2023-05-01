package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import ulb.info307.g6.models.Card;

/**
 * View controller of the EditCard menu, implements View interface and is the controller for the EditCard.fxml file.
 * Contains :
 * - a list view to select a card,
 * - a back button, and buttons to create a new card or edit/remove the selected card
 * - two text areas to enter the question and the answer of the card
 * - a checkbox to select if the card is a gap fill card when creating/editing a card
 */
public class EditCard extends ViewWithCardList {
    @FXML
    private CheckBox gapFillCheckBox;
    @FXML
    private TextArea questionInput, answerInput;
    @FXML
    private Button buttonEditCard, buttonCreateCard, buttonRemoveCard;
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
    public void clickPreview() {
        listener.clickPreview();
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

    /**
     * Method called when input changes in the question or answer text areas.
     */
    @FXML
    private void inputChange() {
        listener.cardChanged();
        if (atLeastOneInputIsEmpty()) {
            activateEditButton(false);
            activateCreateButton(false);
        } else {
            activateCreateButton(true);
            if (isCardSelected()) {
                activateEditButton(true);
            }
        }
    }

    @Override
    protected void actionOnCardSelection() {
        updateQuestionAnswer();
        if (isCardSelected()) {
            activateButtons(true);
            listener.cardChanged();
        }
    }

    public void updateQuestionAnswer() {
        listener.cardChanged();
        Card selectedCard = getSelectedCard();
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

    public boolean cardIsGapFill() {
        return gapFillCheckBox.isSelected();
    }

    public interface EditCardListener {
        void clickBack();
        void clickEditCard();
        void clickCreateCard();
        void clickRemoveCard();
        void clickPreview();
        void cardChanged();
    }
}
