package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Statistics;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Achievements;
import ulb.info307.g6.models.Deck;
import java.util.List;

public class AchievementsController extends Controller implements Achievements.AchievementsListener {

    private final Achievements achievementsView;
    public final StatisticsDao StatisticsDatabase = new StatisticsDao();

    public final DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();
    private final Statistics statistics = StatisticsDatabase.getStatistics();

    public AchievementsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Achievements.fxml", "Achievements");
        achievementsView = (Achievements) view;
        setProgressBars();
    }

    @Override
    public void clickBack() {
        new WelcomeController(stage);
    }

    @Override
    public void setProgressBars() {
        double progress1 = statistics.getLongestDayStreak() / 10.0;
        double progress2 = statistics.getLongestDayStreak() / 5.0;
        double progress3 = getHighestKnowledgeLevel() / 5.0;
        double progress4 = getHighestNumberOfCardsPerDeck() / 10.0;
        achievementsView.showProgressBars(progress1,progress2,progress3,progress4);
    }

    private int getHighestKnowledgeLevel() {
        int max = 0;
        List<Deck> decks = database.getAllDecks();
        for (Deck deck : decks) {
            if (deck.getKnowledgeLevel() > max) {
                max = deck.getKnowledgeLevel();
            }
        }
        return max;
    }

    private int getHighestNumberOfCardsPerDeck() {
        int max = 0;
        List<Deck> decks = database.getAllDecks();
        for (Deck deck : decks) {
            if (deck.getSize() > max) {
                max = deck.getSize();
            }
        }
        return max;
    }
}
