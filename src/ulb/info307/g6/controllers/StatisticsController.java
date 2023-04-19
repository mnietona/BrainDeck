package ulb.info307.g6.controllers;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.Statistics;

public class StatisticsController extends ControllerWithDeckList implements Statistics.StatisticsListener  {
    private final Statistics statisticsView;
    public StatisticsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Statistics.fxml", "Statistics");
        statisticsView = (Statistics) view;
        statisticsView.setNumberOfDecks(database.getNumberOfDecks());
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickDeck(Deck deck) {
        // TODO Auto-generated method stub

    }

}
