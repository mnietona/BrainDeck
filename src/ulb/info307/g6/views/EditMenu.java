package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ulb.info307.g6.models.Deck;

import java.util.List;

public class EditMenu {
    @FXML
    public Button buttonCreate;
    @FXML
    public Button buttonRemove;
    @FXML
    private Button buttonHome;
    @FXML
    public ListView<Deck> deckListViewEditMenu = new ListView();
    @FXML
    private VBox root;
    @FXML
    public Text deckTitle;

    private EditMenuListener listener;

    public void setListener(EditMenuListener listener) {
        this.listener = listener;
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
    public void clickChoice() {
        listener.clickChoice();
    }

    @FXML
    public void clickRemove() {
        listener.clickRemove();
    }
    public void updateDeckTitle() {
        Deck selectedItem = deckListViewEditMenu.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deckTitle.setText(selectedItem.getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    public void setDeckListView(List<Deck> decks) {
        deckListViewEditMenu.getItems().clear();
        for (Deck deck : decks) {
            deckListViewEditMenu.getItems().add(deck);
        }
        deckListViewEditMenu.setOnMouseClicked(event -> { // click on an item
            updateDeckTitle();
        });
    }


    public interface EditMenuListener {
        void clickHome();
        void clickEdit();
        void clickCreate();
        void clickChoice();
        void clickRemove();
    }
}
