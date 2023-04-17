package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * The CreateDeck class implements the View interface and is the controller for the CreateDeck.fxml file.
 * It is used to create a new deck.
 * It has a text field to enter the name of the new deck and a button to confirm the creation.
 */
public class CreateDeck implements View {
    @FXML
    private TextField inputBox;
    @FXML
    private Button buttonConfirm, buttonBack;
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
    // Method to activate/deactivate the confirmation button depending on the validity of the input
    public void activateButton(boolean activate) {
        buttonConfirm.setDisable(!activate);
    }

    public interface CreateDeckListener {
        void clickConfirm(String inputText);
        void clickBack();
    }
}
