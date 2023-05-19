package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.control.Label;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11, progressBar12, progressBar13, progressBar14;
    @FXML
    private ImageView achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, achievement11, achievement12, achievement13, achievement14;
    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14;

    @FXML
    private VBox vboxAchiev;
    @FXML
    private final Image backgroundImage = new Image("/ulb/info307/g6/views/images/main_bckg.jpg");
    private final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private AchievementsListener listener;

    @FXML
    private void clickHome() {
        listener.clickHome();
    }

    @FXML
    public void showProgressBars(double[] progresses) {
        ProgressBar[] progressBars = new ProgressBar[]{progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11, progressBar12, progressBar13, progressBar14};
        for (int i = 0; i < progresses.length; i++) {
            progressBars[i].setProgress(progresses[i]);
        }
    }

    public void showAchievements(boolean[] achieved, boolean[] active) {
        ImageView[] achievementImages = new ImageView[]{achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, achievement11, achievement12, achievement13, achievement14};
        Label[] labels = {label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14};

        for (int i = 0; i < active.length; i++) {
            if (active[i]) {
                achievementImages[i].setVisible(true);
                if (achieved[i]) {
                    Image newImage = new Image("/ulb/info307/g6/views/images/succes.png");
                    achievementImages[i].setImage(newImage);
                }
            }
            else {
                labels[i].setStyle("-fx-text-fill: gray;");
            }
        }
    }

    public void adjustLabelLength() {
        Label[] labels = {label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14};

        int maxLength = 0;
        for (Label label : labels) {
            if (label.getText().length() > maxLength) {
                maxLength = label.getText().length();
            }
        }

        for (int i = 0; i < labels.length; i++) {
            int difference = maxLength - labels[i].getText().length() + 3;
            if (i>=3) difference += 3;
            if (i>=4) difference += 2;
            String spaces = " ".repeat(difference);
            labels[i].setText(labels[i].getText() + spaces);
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
        void clickHome();
        void setProgressBars();
    }
}

