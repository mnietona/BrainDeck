package ulb.info307.g6.controllers;

import ulb.info307.g6.views.CardsStatistics;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;

public class CardsStatisticsController extends ControllerWithCardList implements CardsStatistics.CardsStatisticsListener {

    public CardsStatisticsController(Stage stage, Deck deck) {
        super(stage, "/ulb/info307/g6/views/CardsStatistics.fxml", "Cards statistics", deck);
        CardsStatistics cardsStatisticsView = (CardsStatistics) view;
    }

    @Override
    public void clickBack() {
        new StatisticsController(stage);
    }

    @Override
    public void clickDeckStatistics() {
        //TODO
    }
}
