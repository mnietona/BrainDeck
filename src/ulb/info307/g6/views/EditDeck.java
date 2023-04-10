package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class EditDeck extends ViewWithDeckList {
    @FXML
    private Button buttonHome;
    @FXML
    private Text deckTitle;
    @FXML
    private Button buttonEdit, buttonCreate, buttonRemove;
    private EditDeckListener listener;

    @Override
    public void setListener(Object listener) {
        this.listener = (EditDeckListener) listener;
    }

    @FXML
    public void clickHome() {
        listener.clickHome();
    }

    @FXML
    public void clickEdit() {
        listener.clickEdit();
    }

    @FXML
    public void clickCreate() {
        listener.clickCreate();
    }
    
    @FXML
    public void clickRemove() {
        listener.clickRemove();
    }

    public void updateDeckTitle() {
        if (getSelectedDeck() != null) {
            deckTitle.setText(getSelectedDeck().getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    @Override
    protected void actionOnDeckSelection() {
        updateDeckTitle();
    }

    public interface EditDeckListener {
        void clickHome();
        void clickEdit();
        void clickCreate();
        void clickRemove();
    }
}
