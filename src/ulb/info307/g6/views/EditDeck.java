package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import ulb.info307.g6.models.Deck;
import java.util.List;

public class EditDeck implements View {
    @FXML
    private Button buttonHome;
    @FXML
    public ListView<Deck> deckListViewEditDeck = new ListView();
    @FXML
    public Text deckTitle;
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
        Deck selectedItem = deckListViewEditDeck.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deckTitle.setText(selectedItem.getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    public void setDeckListView(List<Deck> decks) {
        deckListViewEditDeck.getItems().clear();
        for (Deck deck : decks) {
            deckListViewEditDeck.getItems().add(deck);
        }
        deckListViewEditDeck.setOnMouseClicked(event -> {  // click on an item
            updateDeckTitle();
        });
    }

    public interface EditDeckListener {
        void clickHome();
        void clickEdit();
        void clickCreate();
        void clickRemove();
    }
}
