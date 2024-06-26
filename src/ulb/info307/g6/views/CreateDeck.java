package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * View controller of the CreateDeck menu, implements View interface and is the controller for the CreateDeck.fxml file.
 * Contains a text field to enter the name of the new deck and a buttons to confirm its creation or go back.
 */
public class CreateDeck implements View {
    @FXML
    private TextField inputBox;
    @FXML
    private Button buttonConfirm;
    private CreateDeckListener listener;

    @Override
    public void setListener(Object listener) {
        this.listener = (CreateDeckListener) listener;
    }

    @FXML
    private void clickConfirm() {
        String inputText = inputBox.getText();
        listener.clickConfirm(inputText);
    }

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    private void textChange() {
        activateButton(inputIsValid());
    }

    public boolean inputIsValid() {
        return !inputBox.getText().isEmpty();
    }

    /**
     * Method to activate/deactivate the confirmation button depending on the validity of the input.
     */
    public void activateButton(boolean activate) {
        buttonConfirm.setDisable(!activate);
    }

    public interface CreateDeckListener {
        void clickConfirm(String inputText);
        void clickBack();
    }
}
