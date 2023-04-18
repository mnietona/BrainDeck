package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * View controller of the EditDeck menu, implements View interface and is the controller for the EditDeck.fxml file.
 * Contains :
 * - a ListView to select a deck
 * - a home button, buttons to create a new deck, edit/remove/reset the probability the selected deck, and import/export a deck.
 */
public class EditDeck extends ViewWithDeckList {
    @FXML
    private Text deckTitle;
    @FXML
    private Button buttonEdit, buttonRemove, buttonResetProba, buttonExport;
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
        buttonExport.setDisable(!activate);
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
