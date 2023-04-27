package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.views.Achievements;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class AchievementsController extends Controller implements Achievements.AchievementsListener {

    private final Achievements achievementsView;

    public AchievementsController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Achievements.fxml", "Achievements");
        achievementsView = (Achievements) view;
        setAchievements();
        showAchievementsList();
    }

    @Override
    public void clickBack() {
        new WelcomeController(stage);
    }

    @Override
    public void setAchievements() {
        List<Integer> achieved = new ArrayList<>();
        achieved.add(0);
        achieved.add(1);
        achieved.add(0);
        achieved.add(1);
        achieved.add(1);
        achievementsView.setAchievementsView(achieved);
    }

    @Override
    public void showAchievementsList() {
        achievementsView.showAchievementsList();
    }


}
