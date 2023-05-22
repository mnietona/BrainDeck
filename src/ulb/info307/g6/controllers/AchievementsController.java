package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Achievement;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Achievements;
import ulb.info307.g6.models.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the achievements view. It is used to display the achievements of the user.
 * It also displays the progress of the user towards the achievements.
 */

public class AchievementsController extends Controller implements Achievements.AchievementsListener {
    private final Achievements achievementsView;

    private List<Achievement> achievements;

    public final StatisticsDao StatisticsDatabase = new StatisticsDao();
    public final DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();

    public AchievementsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Achievements.fxml", "Achievements");
        achievementsView = (Achievements) view;
        this.achievements = new ArrayList<>();
        generateAchievementList();
        addGeneratedAchievementsToView();
    }

    private void generateAchievementList() {
        achievements.add(new Achievement("5 consecutive days of using the app", 5, StatisticsDatabase::getLongestDayStreak));
        achievements.add(new Achievement("10 consecutive days of using the app", 10, StatisticsDatabase::getLongestDayStreak));
        achievements.add(new Achievement("20 consecutive days of using the app", 20, StatisticsDatabase::getLongestDayStreak));
        achievements.add(new Achievement("Highest knowledge level of deck", 5, this::getHighestKnowledgeLevel));
        achievements.add(new Achievement("Deck with at least 10 cards", 10, this::getHighestNumberOfCardsPerDeck));
        achievements.add(new Achievement("Deck with at least 20 cards", 20, this::getHighestNumberOfCardsPerDeck));
        achievements.add(new Achievement("Deck with at least 100 cards", 100, this::getHighestNumberOfCardsPerDeck));
        achievements.add(new Achievement("100 cards created", 100, database::getTotalNumberOfCards));
        achievements.add(new Achievement("200 cards created", 200, database::getTotalNumberOfCards));
        achievements.add(new Achievement("500 cards created", 500, database::getTotalNumberOfCards));
        achievements.add(new Achievement("1000 cards created", 1000, database::getTotalNumberOfCards));
        achievements.add(new Achievement("1 hour studying", 3600, this::getAllTimeSpent));
        achievements.add(new Achievement("5 hour studying", 3600*5, this::getAllTimeSpent));
        achievements.add(new Achievement("10 hour studying", 3600*10, this::getAllTimeSpent));
    }

    private void addGeneratedAchievementsToView() {
        for (Achievement a : achievements) {
            achievementsView.addNewAchievement(a);
        }
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
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
