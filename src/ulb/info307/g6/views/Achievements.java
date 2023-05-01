package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;

    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double progress1,double progress2, double progress3, double progress4, double progress5) {
        progressBar1.setProgress(progress1);
        progressBar2.setProgress(progress2);
        progressBar3.setProgress(progress3);
        progressBar4.setProgress(progress4);
        progressBar5.setProgress(progress5);
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

