package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.views.Achievements;

public class AchievementsController extends Controller implements Achievements.AchievementsListener {

    private final Achievements achievementsView;

    public AchievementsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Achievements.fxml", "Achievements");
        achievementsView = (Achievements) view;

    }

    @Override
    public void clickBack() {
        new WelcomeController(stage);
    }

}
