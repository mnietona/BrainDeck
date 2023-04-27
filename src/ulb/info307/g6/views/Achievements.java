package ulb.info307.g6.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ulb.info307.g6.models.Achievement;

import java.util.ArrayList;
import java.util.List;

public class Achievements implements View {
    private AchievementsListener listener;
    List<Integer> achievedListView;
    @FXML
    private ListView<Achievement> cardListView;
    @FXML
    private void initialize() {

    }

    public void showAchievementsList()
    {
        listener.setAchievements();
        List<Achievement> achievements = createAchievements();
        ObservableList<Achievement> observableAchievements = FXCollections.observableArrayList(achievements);
        cardListView.setItems(observableAchievements);

        // Centrer le texte horizontalement
        cardListView.setCellFactory(lv -> {
            ListCell<Achievement> cell = new ListCell<>() {
                @Override
                protected void updateItem(Achievement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item.getDescription());
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
            return cell;
        });
    }
    public void setAchievementsView(List<Integer> achievedAchievements)
    {
        this.achievedListView = achievedAchievements;
    }

    private List<Achievement> createAchievements()
    {
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement( "["+achievedListView.get(0)+"/1] Novice: Achieve a perfect score on your first set of flashcards. " ));
        achievements.add(new Achievement("["+achievedListView.get(1)+"/1] Master of Cards: Achieve a perfect score on all sets of flashcards."));
        achievements.add(new Achievement("["+achievedListView.get(2)+"/1] Regular Player: Play the flashcard game every day for 3 consecutive days."));
        achievements.add(new Achievement("["+achievedListView.get(3)+"/1] Explorer: Complete a set of flashcards that contains LaTeX formulas."));
        achievements.add(new Achievement("["+achievedListView.get(4)+"/1] Regular Player: Play the flashcard game every day for 10 consecutive days."));
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
        void setAchievements();
        void showAchievementsList();
    }

}

