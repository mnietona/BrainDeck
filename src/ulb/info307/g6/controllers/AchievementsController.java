package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Statistics;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Achievements;
import ulb.info307.g6.models.Deck;
import java.util.List;
import java.util.Arrays;

public class AchievementsController extends Controller implements Achievements.AchievementsListener {
    private final Achievements achievementsView;
    public final StatisticsDao StatisticsDatabase = new StatisticsDao();
    public final DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();
    private final Statistics statistics = StatisticsDatabase.getStatistics();

    public AchievementsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Achievements.fxml", "Achievements");
        achievementsView = (Achievements) view;
        setProgressBars();
        achievementsView.setBackgroundImage();
        achievementsView.adjustLabelLength();
    }

    @Override
    public void clickBack() {
        new WelcomeController(stage);
    }

    @Override
    public void setProgressBars() {
        double[] progresses = new double[14];
        boolean[] achievementsActive = new boolean[progresses.length];
        Arrays.fill(achievementsActive, false);

        double longestDayStreak = statistics.getLongestDayStreak();
        progresses[0] = longestDayStreak / 5.0;
        achievementsActive[0] = true;
        if (longestDayStreak > 5.0) {
            progresses[1] = longestDayStreak / 10.0;
            achievementsActive[1] = true;
        }
        if (longestDayStreak > 10.0) {
            progresses[2] = longestDayStreak / 20.0;
            achievementsActive[2] = true;
        }
        if (longestDayStreak > 20.0) {
            progresses[3] = longestDayStreak / 50.0;
            achievementsActive[3] = true;
        }

        progresses[3] = getHighestKnowledgeLevel() / 5.0;
        achievementsActive[3] = true;

        int highestNumberOfCardsPerDeck = getHighestNumberOfCardsPerDeck();
        progresses[4] = highestNumberOfCardsPerDeck / 10.0;
        achievementsActive[4] = true;
        if (highestNumberOfCardsPerDeck >= 10.0) {
            progresses[5] = highestNumberOfCardsPerDeck / 20.0;
            achievementsActive[5] = true;
        }
        if (highestNumberOfCardsPerDeck >= 20.0) {
            progresses[6] = highestNumberOfCardsPerDeck / 50.0;
            achievementsActive[6] = true;
        }

        int totalNumberOfCards = database.getTotalNumberOfCards();
        progresses[7] = totalNumberOfCards / 100.0;
        achievementsActive[7] = true;
        if (totalNumberOfCards >= 100.0) {
            progresses[8] = totalNumberOfCards / 200.0;
            achievementsActive[8] = true;
        }
        if (totalNumberOfCards >= 200.0) {
            progresses[9] = totalNumberOfCards / 500.0;
            achievementsActive[9] = true;
        }
        if (totalNumberOfCards >= 500.0) {
            progresses[10] = totalNumberOfCards / 1000.0;
            achievementsActive[10] = true;
        }

        double allTimeSpent = getAllTimeSpent();
        progresses[11] = allTimeSpent / 3600.0;
        achievementsActive[11] = true;
        if (allTimeSpent >= 3600.0) {
            progresses[12] = allTimeSpent / (3600.0 * 5.0);
            achievementsActive[12] = true;
        }
        if (allTimeSpent >= 3600.0 * 5.0) {
            progresses[13] = allTimeSpent / (3600.0 * 10.0);
            achievementsActive[13] = true;
        }

        boolean[] achievements = new boolean[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            achievements[i] = progresses[i] >= 1.0;
        }

        achievementsView.showProgressBars(progresses);
        achievementsView.showAchievements(achievements,achievementsActive);
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

    private double getAllTimeSpent() {
        double timeSpent = 0;
        List<Deck> decks = database.getAllDecks();
        for (Deck deck : decks) {
            timeSpent += deck.getTimeSpent();
        }
        return timeSpent;
    }
}
