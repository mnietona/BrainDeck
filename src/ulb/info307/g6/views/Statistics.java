
package ulb.info307.g6.views;
import javafx.fxml.FXML;
import ulb.info307.g6.models.Deck;
import javafx.scene.text.Text;

public class Statistics extends ViewWithDeckList {
    private StatisticsListener listener;
    @FXML
    private Text upperText;
    @FXML
    private Text middleText;
    @FXML
    private Text lowerText;

    @FXML
    public void clickHome() {
        listener.clickHome();
    }

    @FXML
    public void clickGlobalStatistics() {
        listener.showGlobalStatistics();
    }

    public void setNumberOfDecks(int n) {
        upperText.setText("Number of decks: " + n);
    }

    public void setTotalNumberOfCards(int n) {
        lowerText.setText("Total number of cards: " + n);
    }

    public void setDeckName(String name) {
        upperText.setText("Deck name: " + name);
    }

    public void setNumberCardsOfDeck(int n) {
        lowerText.setText("Number of cards in this deck: " + n);
    }

    public void setTimeSpentOfDeck(String timeSpent) {middleText.setText("Time spent: " + timeSpent);}

    public void setTimeSpentOfAllDeck(String timeSpent) {middleText.setText("Total Time: " + timeSpent);}

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
        void showGlobalStatistics();

    }
}
