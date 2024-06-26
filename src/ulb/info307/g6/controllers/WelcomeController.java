package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Statistics;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Welcome;

/**
 * Controller for the welcome menu.
 * This menu is the first menu shown when the app is launched.
 * It allows the user to access the other menus.
 * It also shows some statistics about the user's progress.
 */

public class WelcomeController extends Controller implements Welcome.WelcomeListener  {
    public final StatisticsDao statisticsDatabase = new StatisticsDao();
    public final Welcome welcomeView;

    public WelcomeController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Welcome.fxml", "Menu");
        welcomeView = (Welcome) view;
        onLaunchStatisticsUpdate();
        showMainMenuStatistics();
    }

    /**
     * Some statistics (the current day streak for example) must be updated when the app is opened,
     * which is done here (when the first menu is shown).
     */
    private void onLaunchStatisticsUpdate() {
        Statistics statistics = statisticsDatabase.getStatistics();
        if (statistics == null) {  // Insert statistics in database if empty (first launch)
            statistics = new Statistics();
            statisticsDatabase.insert(statistics);
        } else {
            statistics.updateDayStreak();  // DayStreak is updated on opening of the app
            statisticsDatabase.update(statistics);
        }
    }

    @Override
    public void showMainMenuStatistics() { //2 stats now, more to come
        int dayStreak = statisticsDatabase.getCurrentDayStreak();
        int longestDayStreak = statisticsDatabase.getLongestDayStreak();

        welcomeView.setGlobalStatistics(dayStreak, longestDayStreak);
    }

    @Override
    public void studyButtonAction() {
        new StudyController(stage);
    }

    @Override
    public void editButtonAction() {
        new EditDeckController(stage);
    }

    @Override
    public void statisticsButtonAction() {
        new StatisticsController(stage);
    }

    @Override
    public void achievementsButtonAction() {
        new AchievementsController(stage);
    }

    @Override
    public void helpButtonAction() {
        new TutorialController(stage);
    }
}
