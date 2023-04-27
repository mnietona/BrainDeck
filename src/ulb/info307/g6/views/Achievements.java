package ulb.info307.g6.views;

import javafx.fxml.FXML;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

import java.util.Iterator;

public class Achievements implements View {
    private AchievementsListener listener;


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
