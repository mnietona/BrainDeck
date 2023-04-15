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
    private Button buttonEdit, buttonCreate, buttonRemove, buttonResetProba, buttonExport, buttonImport;
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
    @FXML
    public void clickResetProba() { listener.clickResetProba(); }

    @FXML
    public void clickImport() {
        listener.clickImport();
    }

    @FXML
    public void clickExport() {
        listener.clickExport();
    }

    public void updateDeckTitle() {
        if (getSelectedDeck() != null) {
            deckTitle.setText(getSelectedDeck().getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    public void activateActionButtons(boolean activate) {
        buttonEdit.setDisable(!activate);
        buttonRemove.setDisable(!activate);
        buttonResetProba.setDisable(!activate);
    }

    @Override
    protected void actionOnDeckSelection() {
        listener.deckSelected();
    }

    public interface EditDeckListener {
        void clickHome();
        void clickEdit();
        void clickCreate();
        void clickRemove();
        void clickResetProba();
        void deckSelected();
        void clickImport();
        void clickExport();
    }
}
