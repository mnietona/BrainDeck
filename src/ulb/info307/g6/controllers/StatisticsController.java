package ulb.info307.g6.controllers;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.Statistics;

import java.time.Duration;

public class StatisticsController extends ControllerWithDeckList implements Statistics.StatisticsListener  {
    private final Statistics statisticsView;
    public StatisticsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Statistics.fxml", "Statistics");
        statisticsView = (Statistics) view;
        showGlobalStatistics();
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void showGlobalStatistics() {
        statisticsView.setNumberOfDecks(database.getNumberOfDecks());
        String timeSpentAsString = getTimeSpentAsString(database.getTotalTimeSpent());
        statisticsView.setTimeSpentOfAllDeck(timeSpentAsString);
        statisticsView.setTotalNumberOfCards(database.getTotalNumberOfCards());
    }

    @Override
    public void clickDeck(Deck deck) {
        statisticsView.setDeckName(deck.getName());
        String timeSpentAsString = getTimeSpentAsString(deck.getTimeSpent());
        statisticsView.setTimeSpentOfDeck(timeSpentAsString);
        statisticsView.setNumberCardsOfDeck(deck.getSize());
    }

    public String getTimeSpentAsString(long timeSpentSecond) {
        Duration duration = Duration.ofSeconds(timeSpentSecond);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


}
