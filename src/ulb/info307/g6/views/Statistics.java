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
    private Text globalStat1, globalStat2, globalStat3, globalStat4, globalStat5;
    @FXML
    private Text deckStat1, deckStat2, deckStat3, deckStat4;

    @FXML
    public void clickHome() {
        listener.clickHome();
    }

    @FXML
    public void clickCardsStatistics() {
        listener.showCardsStatistics();
    }

    public void setGlobalStatistics(int nbDecks, int nbCards, int dayStreak, int longestDayStreak, long totalTimeSpent) {
        globalStat1.setText(nbDecks+"\nTotal\nDecks");
        globalStat2.setText(nbCards+"\nTotal\nCards");
        globalStat3.setText(dayStreak+"\nCurrent\nday streak");
        globalStat3.setText(getTimeSpentAsString(totalTimeSpent)+"\nHours\nstudied");
        globalStat4.setText(dayStreak+"\nCurrent\nday streak");
        globalStat5.setText(longestDayStreak+"\nLongest\nday streak");
    }

    public void setDeckStatistics(String deckName, long timeSpent, int nbCards, int knowledgeLevel) {
        deckStat1.setText(deckName);
        deckStat2.setText(getTimeSpentAsString(timeSpent)+" hours studied");
        deckStat3.setText(nbCards+" cards");
        deckStat4.setText("Level: "+getKnowledgeLevelString(knowledgeLevel));
    }

    public String getKnowledgeLevelString(int knowledgeLevel) {
        return switch (knowledgeLevel) {
            case 0 -> "Beginner";
            case 1 -> "Intermediate";
            case 2 -> "Advanced";
            case 3 -> "Expert";
            case 4 -> "Master";
            default -> "N/A";
        };
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
        void showGlobalStatistics();
        void clickDeck(Deck deck);
        void showCardsStatistics();
    }
}
