package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import ulb.info307.g6.models.Statistics;
import ulb.info307.g6.models.database.DatabaseConnection;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.database.StatisticsDao;
import ulb.info307.g6.views.Welcome;

public class WelcomeController extends Controller implements Welcome.WelcomeListener  {
    public final StatisticsDao StatisticsDatabase = new StatisticsDao();  //

    public WelcomeController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Welcome.fxml", "Menu");
        Statistics statistics = StatisticsDatabase.getStatistics();
        if (statistics == null) {
            statistics = new Statistics();
            StatisticsDatabase.insert(statistics);
        }
        statistics.updateLastDay();
        StatisticsDatabase.update(statistics);
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
}
