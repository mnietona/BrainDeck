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
        double[] progresses = new double[8];
        progresses[0] = statistics.getLongestDayStreak() / 5.0;
        progresses[1] = statistics.getLongestDayStreak() / 10.0;
        progresses[2] = getHighestKnowledgeLevel() / 5.0;
        progresses[3] = getHighestNumberOfCardsPerDeck() / 10.0;
        progresses[4] = database.getTotalNumberOfCards() / 100.0;
        progresses[5] = database.getTotalNumberOfCards() / 200.0;
        progresses[6] = database.getTotalNumberOfCards() / 500.0;
        progresses[7] = database.getTotalNumberOfCards() / 1000.0;

        boolean[] achievements = new boolean[progresses.length];
        // boolean a8 = progress8 >= 1.0;
        for (int i = 0; i < progresses.length; i++) {
            achievements[i] = progresses[i] >= 1.0;
        }

        achievementsView.showProgressBars(progresses);
        achievementsView.showAchievements(achievements);
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
