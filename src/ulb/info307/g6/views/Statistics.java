package ulb.info307.g6.views;
import javafx.fxml.FXML;
import ulb.info307.g6.models.Deck;
import javafx.scene.text.Text;

public class Statistics extends ViewWithDeckList {
    private StatisticsListener listener;

    @FXML
    private Text textNumberOfDecks;

    @FXML
    public void clickHome() {
        listener.clickHome();
    }

    public void setNumberOfDecks(int n) {
        textNumberOfDecks.setText("Number of decks: " + n);
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (StatisticsListener) listener;
    }

    @Override
    protected void actionOnDeckSelection() {
        listener.clickDeck(getSelectedDeck());
    }

    public interface StatisticsListener {
        void clickHome();
        void clickDeck(Deck deck);
    }
}
