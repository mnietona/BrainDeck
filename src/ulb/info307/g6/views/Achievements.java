package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class Achievements implements View {
    @FXML
    private ProgressBar firstProgressBar, secondProgressBar, thirdProgressBar, fourthProgressBar, fifthProgressBar;

    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double progress1,double progress2, double progress3, double progress4, double progress5) {
        firstProgressBar.setProgress(progress1);
        secondProgressBar.setProgress(progress2);
        thirdProgressBar.setProgress(progress3);
        fourthProgressBar.setProgress(progress4);
        fifthProgressBar.setProgress(progress5);
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (Achievements.AchievementsListener) listener;
    }

    public interface AchievementsListener {
        void clickBack();
        void setProgressBars();
    }
}

