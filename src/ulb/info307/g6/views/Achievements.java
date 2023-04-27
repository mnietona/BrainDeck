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

    @FXML
    private ListView<Achievement> cardListView;

    @FXML
    private void initialize() {
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement("Achieve 3 cards in a row"));
        achievements.add(new Achievement("Achieve 10 cards in a row"));
        achievements.add(new Achievement("Finish a deck without error"));
        // Ajouter d'autres achievements ici

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

