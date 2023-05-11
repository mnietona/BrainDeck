package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11, progressBar12, progressBar13, progressBar14;

    @FXML
    private ImageView achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, achievement11, achievement12, achievement13, achievement14;


    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double[] progresses) {
        ProgressBar[] progressBars = new ProgressBar[]{progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11, progressBar12, progressBar13, progressBar14};
        for (int i = 0; i < progresses.length; i++) {
            progressBars[i].setProgress(progresses[i]);
        }
    }

    @FXML
    public void showAchievements(boolean[] achievements) {
        ImageView[] achievementImages = new ImageView[]{achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, achievement11, achievement12, achievement13, achievement14};
        for (int i = 0; i < achievements.length; i++) {
            achievementImages[i].setVisible(achievements[i]);
        }
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

