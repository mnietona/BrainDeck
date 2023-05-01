package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Statistics;

public class StatisticsController extends ControllerWithDeckList implements Statistics.StatisticsListener  {
    public final StatisticsDao statisticsDatabase = new StatisticsDao();  //
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
        int nbDecks = deckDatabase.getNumberOfDecks();
        int nbCards = deckDatabase.getTotalNumberOfCards();
        int dayStreak = statisticsDatabase.getCurrentDayStreak();
        int longestDayStreak = statisticsDatabase.getLongestDayStreak();
        long totalTimeSpent = deckDatabase.getTotalTimeSpent();

        statisticsView.setGlobalStatistics(nbDecks, nbCards, dayStreak, longestDayStreak, totalTimeSpent);
    }

    @Override
    public void showCardsStatistics() {
        new CardsStatisticsController(stage, statisticsView.getSelectedDeck());
    }

    @Override
    public void clickDeck(Deck deck) {
        statisticsView.activateButton(true);
        String deckName = deck.getName();
        int nbCards = deck.getSize();
        long timeSpent = deck.getTimeSpent();
        int knowledgeLevel = deck.getKnowledgeLevel();

        statisticsView.setDeckStatistics(deckName, timeSpent, nbCards, knowledgeLevel);
    }

}