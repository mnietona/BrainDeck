package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.Statistics;

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
        statisticsView.setTimeSpentOfAllDeck(database.getTotalTimeSpent());
        statisticsView.setTotalNumberOfCards(database.getTotalNumberOfCards());
    }

    @Override
    public void clickDeck(Deck deck) {
        statisticsView.setDeckName(deck.getName());
        statisticsView.setTimeSpentOfDeck(deck.getTimeSpent());
        statisticsView.setNumberCardsOfDeck(deck.getSize());
        statisticsView.setKnowledgeLevelOfDeck(deck.getKnowledgeLevel());
    }

}
