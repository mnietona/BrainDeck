package ulb.info307.g6.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Achievements implements View {
    private AchievementsListener listener;
    List<Integer> achievedListView;
    @FXML
    private ListView<ulb.info307.g6.models.Achievements> cardListView;
    @FXML
    private void initialize() {

    }

    public void showAchievementsList()
    {
        listener.setAchievements();
        List<ulb.info307.g6.models.Achievements> achievements = createAchievements();
        ObservableList<ulb.info307.g6.models.Achievements> observableAchievements = FXCollections.observableArrayList(achievements);
        cardListView.setItems(observableAchievements);

        // Centrer le texte horizontalement
        cardListView.setCellFactory(lv -> {
            ListCell<ulb.info307.g6.models.Achievements> cell = new ListCell<>() {
                @Override
                protected void updateItem(ulb.info307.g6.models.Achievements item, boolean empty) {
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

    private List<ulb.info307.g6.models.Achievements> createAchievements()
    {
        List<ulb.info307.g6.models.Achievements> achievements = new ArrayList<>();
        achievements.add(new ulb.info307.g6.models.Achievements( "["+achievedListView.get(0)+"/1] Novice: Achieve a perfect score on your first set of flashcards. " ));
        achievements.add(new ulb.info307.g6.models.Achievements("["+achievedListView.get(1)+"/1] Master of Cards: Achieve a perfect score on all sets of flashcards."));
        achievements.add(new ulb.info307.g6.models.Achievements("["+achievedListView.get(2)+"/1] Regular Player: Play the flashcard game every day for 3 consecutive days."));
        achievements.add(new ulb.info307.g6.models.Achievements("["+achievedListView.get(3)+"/1] Explorer: Complete a set of flashcards that contains LaTeX formulas."));
        achievements.add(new ulb.info307.g6.models.Achievements("["+achievedListView.get(4)+"/1] Regular Player: Play the flashcard game every day for 10 consecutive days."));
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

