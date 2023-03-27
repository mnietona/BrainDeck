package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ulb.info307.g6.models.Deck;

import java.util.List;

public class EditMenu {
    public List<Deck> decks;
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonAdd;
    @FXML
    public ComboBox<Deck> cardPack;
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
        Deck selectedItem = cardPack.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deckTitle.setText(selectedItem.getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    public void setCardPackLists(List<Deck> decks) {
        this.decks = decks;
        cardPack.getItems().clear();
        for (Deck deck : decks) {
            cardPack.getItems().add(deck);
        }
        cardPack.setOnAction(event -> { // click on an item
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
