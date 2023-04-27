package ulb.info307.g6.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ulb.info307.g6.models.Achievement;

import java.util.ArrayList;
import java.util.List;

public class Achievements implements View {
    private AchievementsListener listener;

    @FXML
    private ListView<Achievement> cardListView;

    @FXML
    private void initialize() {
        List<Integer> achieved = new ArrayList<>();
        achieved.add(1);
        achieved.add(1);
        achieved.add(0);
        achieved.add(0);
        achieved.add(0);

        List<Achievement> achievements = createAchievements(achieved);
        ObservableList<Achievement> observableAchievements = FXCollections.observableArrayList(achievements);
        cardListView.setItems(observableAchievements);
    }

    private List<Achievement> createAchievements(List<Integer> achieved)
    {
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement( "["+achieved.get(0)+"/1] Novice: Achieve a perfect score on your first set of flashcards. " ));
        achievements.add(new Achievement("["+achieved.get(1)+"/1] Master of Cards: Achieve a perfect score on all sets of flashcards."));
        achievements.add(new Achievement("["+achieved.get(2)+"/1] Regular Player: Play the flashcard game every day for 3 consecutive days."));
        achievements.add(new Achievement("["+achieved.get(3)+"/1] Explorer: Complete a set of flashcards that contains LaTeX formulas."));
        achievements.add(new Achievement("["+achieved.get(4)+"/1] Regular Player: Play the flashcard game every day for 10 consecutive days."));
        return achievements;
    }




    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (Achievements.AchievementsListener) listener;
    }

    public interface AchievementsListener {
        void clickBack();
    }
}

