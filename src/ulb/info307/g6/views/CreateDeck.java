package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class CreateDeck implements View {

    private CreateDeckMenuListener listener;

    @FXML
    private TextField inputBox;

    @FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonBack;

    @FXML
    private void clickConfirm() {
        String inputText = inputBox.getText();
        listener.clickConfirm(inputText);
    }

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (CreateDeckMenuListener) listener;
    }

    public interface CreateDeckMenuListener {
        void clickBack();
        void clickConfirm(String inputText);
    }

}
