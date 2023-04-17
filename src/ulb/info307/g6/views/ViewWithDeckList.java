package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ulb.info307.g6.models.Deck;
import java.util.List;
/**
 * The ViewWithDeckList class implements the View interface and is the controller for the ViewWithDeckList.fxml file.
 * It is used to display a list of decks.
 * It has a list view to select a deck.
 */
public abstract class ViewWithDeckList implements View {
    @FXML
    private ListView<Deck> deckListView = new ListView();

    public Deck getSelectedDeck() {
        return deckListView.getSelectionModel().getSelectedItem();
    }

    public boolean isDeckSelected() {
        return getSelectedDeck() != null;
    }

    public void setDeckListView(List<Deck> decks) {
        deckListView.getItems().clear();
        for (Deck deck : decks) {
            deckListView.getItems().add(deck);
        }
        deckListView.setOnMouseClicked(event -> {  // click on an item
            if (isDeckSelected()) actionOnDeckSelection();
        });
    }

    protected abstract void actionOnDeckSelection();
}
