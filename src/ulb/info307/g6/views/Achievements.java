package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8;

    @FXML
    private ImageView achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8;


    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double progress1,double progress2, double progress3, double progress4, double progress5, double progress6, double progress7, double progress8) {
        progressBar1.setProgress(progress1);
        progressBar2.setProgress(progress2);
        progressBar3.setProgress(progress3);
        progressBar4.setProgress(progress4);
        progressBar5.setProgress(progress5);
        progressBar6.setProgress(progress6);
        progressBar7.setProgress(progress7);
        progressBar8.setProgress(progress8);
    }

    @FXML
    public void showAchievements(boolean a1, boolean a2, boolean a3, boolean a4, boolean a5, boolean a6, boolean a7, boolean a8) {
        achievement1.setVisible(a1);
        achievement2.setVisible(a2);
        achievement3.setVisible(a3);
        achievement4.setVisible(a4);
        achievement5.setVisible(a5);
        achievement6.setVisible(a6);
        achievement7.setVisible(a7);
        achievement8.setVisible(a8);
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

