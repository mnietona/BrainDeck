package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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

    public interface CreateDeckListener {
        void clickConfirm(String inputText);
        void clickBack();
    }
}
