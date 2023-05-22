package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ulb.info307.g6.models.Achievement;

public class Achievements implements View {
    private class AchievementGridPane extends GridPane {
        AchievementGridPane(Achievement a, int tier) {
            super();
            getStyleClass().add("achievement-box");
            Label achievmentLabel = new Label(a.getName());

            add(achievmentLabel, 0, 0);
            HBox barAndChecksContainer = new HBox();
            barAndChecksContainer.setAlignment(Pos.CENTER_LEFT);
            barAndChecksContainer.setSpacing(0.0);
            ProgressBar achievementProgressBar = new ProgressBar();
            achievementProgressBar.setProgress(a.getProgress());
            achievementProgressBar.setPrefWidth(250.0);
            barAndChecksContainer.getChildren().add(achievementProgressBar);
            if (tier == 0) {
                ImageView achievementImageView = new ImageView(new Image("/ulb/info307/g6/views/icons/checkmark.png"));
                achievementImageView.setFitHeight(75);
                achievementImageView.setFitWidth(75);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1); // Set saturation to -1 for full grayness
                achievementImageView.setEffect(colorAdjust);
                barAndChecksContainer.getChildren().add(achievementImageView);
            } else {
                for(int i=0;i<tier;i++){
                    ImageView achievementImageView = new ImageView(new Image("/ulb/info307/g6/views/icons/checkmark.png"));
                    achievementImageView.setFitHeight(75);
                    achievementImageView.setFitWidth(75);
                    barAndChecksContainer.getChildren().add(achievementImageView);
                }
            }
            add(barAndChecksContainer,1, 0);
            ColumnConstraints textColumn = new ColumnConstraints();
            textColumn.setPercentWidth(50);
            ColumnConstraints barAndChecksColumn = new ColumnConstraints();
            barAndChecksColumn.setPercentWidth(50);
            getColumnConstraints().addAll(textColumn, barAndChecksColumn);
        }
    }
    @FXML
    private VBox achievementsVbox;
    private AchievementsListener listener;
    @FXML
    private void clickHome() {
        listener.clickHome();
    }


    public void addNewAchievement(Achievement a, int tier) {
        achievementsVbox.getChildren().add(new AchievementGridPane(a, tier));
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (Achievements.AchievementsListener) listener;
    }

    public interface AchievementsListener {
        void clickHome();
    }
}
