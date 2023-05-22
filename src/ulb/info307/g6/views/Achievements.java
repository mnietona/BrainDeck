package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ulb.info307.g6.models.Achievement;

public class Achievements implements View {
    private class AchievementHbox extends HBox {
        AchievementHbox(Achievement a) {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(25.0);
            getStyleClass().add("achievement-box");
            getChildren().add(new Label(a.getName()));
            ProgressBar achievementProgressBar = new ProgressBar();
            achievementProgressBar.setProgress(a.getProgress());
            achievementProgressBar.setPrefWidth(250.0);
            ImageView achievementImageView = new ImageView(new Image("/ulb/info307/g6/views/icons/checkmark.png"));
            achievementImageView.setFitHeight(75);
            achievementImageView.setFitWidth(75);
            if (!a.isAchieved()) {
                System.out.println(a.getProgress());
                achievementImageView.setVisible(false);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1); // Set saturation to -1 for full grayness
                achievementImageView.setEffect(colorAdjust);
            } else {
                achievementImageView.setVisible(true);
            }

            getChildren().add(achievementProgressBar);
            getChildren().add(achievementImageView);
        }
    }
    @FXML
    private VBox achievementsVbox;
    private AchievementsListener listener;
    @FXML
    private void clickHome() {
        listener.clickHome();
    }


    public void addNewAchievement(Achievement a) {
        achievementsVbox.getChildren().add(new AchievementHbox(a));
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (Achievements.AchievementsListener) listener;
    }

    public interface AchievementsListener {
        void clickHome();
    }
}
