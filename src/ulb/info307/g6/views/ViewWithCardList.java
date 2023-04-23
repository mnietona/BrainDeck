package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import java.util.Iterator;

/**
 * Parent class of view controllers containing a ListView of cards.
 */
public abstract class ViewWithCardList implements View {
    @FXML
    private ListView<Card> cardListView = new ListView<>();

    public Card getSelectedCard() {
        return cardListView.getSelectionModel().getSelectedItem();
    }

    public boolean isCardSelected() {
        return getSelectedCard() != null;
    }

    public void setCardListView(Deck deck) {
        cardListView.getItems().clear();
        Iterator<Card> cardIterator = deck.getCardIterator();
        while (cardIterator.hasNext()) {
            cardListView.getItems().add(cardIterator.next());
        }
        cardListView.setOnMouseClicked(event -> {  // Click on an item of the list
            if (isCardSelected()) actionOnCardSelection();
        });
    }

    protected abstract void actionOnCardSelection();
}
