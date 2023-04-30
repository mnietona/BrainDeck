package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Statistics;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Achievements;

import java.util.ArrayList;
import java.util.List;

public class AchievementsController extends Controller implements Achievements.AchievementsListener {

    private final Achievements achievementsView;
    public final StatisticsDao StatisticsDatabase = new StatisticsDao();
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
        Statistics statistics = StatisticsDatabase.getStatistics();
        double progress1 = statistics.getLongestDayStreak() / 10.0;
        achievementsView.showProgressBars(progress1);
    }
}
