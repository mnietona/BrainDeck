package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ulb.info307.g6.models.Deck;
import javafx.scene.text.Text;
import java.time.Duration;

public class Statistics extends ViewWithDeckList {
    private StatisticsListener listener;
    @FXML
    private Button cardsStatisticsButton;
    @FXML
    private Text firstText, secondText, thirdText, fourthText, fifthText;

    @FXML
    public void clickHome() {
        listener.clickHome();
    }

    @FXML
    public void clickGlobalStatistics() {
        listener.showGlobalStatistics();
    }

    @FXML
    public void clickCardsStatistics() {
        listener.showCardsStatistics();
    }

    public void setNumberOfDecks(int n) {
        firstText.setText("Number of decks: " + n);
    }

    public void setTotalNumberOfCards(int n) {
        thirdText.setText("Total number of cards: " + n);
    }

    public void setDeckName(String name) {
        firstText.setText("Deck name: " + name);
    }

    public void setNumberCardsOfDeck(int n) {
        thirdText.setText("Number of cards in this deck: " + n);
    }

    public void setTimeSpentOfDeck(long timeSpent) {
        String timeSpentString = getTimeSpentAsString(timeSpent);
        secondText.setText("Time spent: " + timeSpentString);
    }

    public void setTimeSpentOfAllDeck(long timeSpent) {
        String timeSpentString = getTimeSpentAsString(timeSpent);
        secondText.setText("Total Time: " + timeSpentString);
    }

    public void setKnowledgeLevelOfDeck(int knowledgeLevel) {
        String s = switch (knowledgeLevel) {
            case 0 -> "Beginner";
            case 1 -> "Intermediate";
            case 2 -> "Advanced";
            case 3 -> "Expert";
            case 4 -> "Master";
            default -> "N/A";
        };
        fourthText.setText("Knowledge level: "+ s);
    }

    public void setDayStreak(int currentDayStreak, int longestDayStreak) {
        fifthText.setText("Current day streak: " + currentDayStreak + "\n" + "Longest day streak: " + longestDayStreak);
    }

    public String getTimeSpentAsString(long timeSpentSecond) {
        Duration duration = Duration.ofSeconds(timeSpentSecond);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void activateButton(boolean activate) {
        cardsStatisticsButton.setDisable(!activate);
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
        void showGlobalStatistics();
        void showCardsStatistics();
    }
}
