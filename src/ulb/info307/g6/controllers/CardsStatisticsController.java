package ulb.info307.g6.controllers;

import ulb.info307.g6.views.CardsStatistics;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;

/**
 * This class represents a controller for the CardsStatistics view.
 * It extends the ControllerWithCardList class and implements the CardsStatisticsListener interface.
 */

public class CardsStatisticsController extends ControllerWithCardList implements CardsStatistics.CardsStatisticsListener {
    private final CardsStatistics cardsStatisticsView;

    public CardsStatisticsController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/CardsStatistics.fxml", "Cards statistics of deck " + deck.getName(), deck);
        cardsStatisticsView = (CardsStatistics) view;
    }

    @Override
    public void clickBack() {
        new StatisticsController(stage);
    }

    @Override
    public void showCardStatistics() {
        cardsStatisticsView.setCardKnowledgeLevel(cardsStatisticsView.getSelectedCard().getKnowledgeLevel());
        cardsStatisticsView.setCardTimeSpent(cardsStatisticsView.getSelectedCard().getTimeSpent());
        cardsStatisticsView.setCardProbability(cardsStatisticsView.getSelectedCard().getProbability());
        cardsStatisticsView.setCardNumberOfAppearances(cardsStatisticsView.getSelectedCard().getNumberOfAppearances());
    }
}
