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
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement("Achieve 3 cards in a row"));
        // Ajouter d'autres achievements ici

        ObservableList<Achievement> observableAchievements = FXCollections.observableArrayList(achievements);
        cardListView.setItems(observableAchievements);
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

