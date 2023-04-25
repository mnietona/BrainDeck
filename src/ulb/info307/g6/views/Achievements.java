package ulb.info307.g6.views;

import javafx.fxml.FXML;

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
