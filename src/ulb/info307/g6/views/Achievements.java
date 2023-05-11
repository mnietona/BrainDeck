package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8;

    @FXML
    private ImageView achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8;

    @FXML
    private VBox vboxAchiev;

    @FXML
    private Image backgroundImage = new Image("/ulb/info307/g6/views/images/main_bckg.jpg");
    private BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double[] progresses) {
        ProgressBar[] progressBars = new ProgressBar[]{progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8};
        for (int i = 0; i < progresses.length; i++) {
            progressBars[i].setProgress(progresses[i]);
        }
    }

    @FXML
    public void showAchievements(boolean[] achievements) {
        ImageView[] achievementImages = new ImageView[]{achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8};
        for (int i = 0; i < achievements.length; i++) {
            achievementImages[i].setVisible(achievements[i]);
        }
    }

    public void setBackgroundImage() {
        vboxAchiev.setBackground(new Background(background));
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

