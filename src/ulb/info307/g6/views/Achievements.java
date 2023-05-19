package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Achievements implements View {
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11, progressBar12, progressBar13, progressBar14;
    @FXML
    private ImageView achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, achievement11, achievement12, achievement13, achievement14;
    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14;
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
                if (!achieved[i]) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1); // Set saturation to -1 for full grayness
                    achievementImages[i].setEffect(colorAdjust);
                }
            }
            else {
                labels[i].setStyle("-fx-text-fill: gray;");
            }
        }
        for (ImageView imageView : achievementImages) {
            imageView.setFitHeight(75);
            imageView.setFitWidth(75);
        }
    }

    public void adjustLabelLength() {
        Label[] labels = {label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14};

        int maxLength = 0;
        for (Label label : labels) {
            if (getTextLength(label.getText()) > maxLength) {
                maxLength = getTextLength(label.getText());
            }
        }

        for (Label label : labels) {
            int difference = maxLength - getTextLength(label.getText()) + 3;
            int emptySpaceLength = getTextLength(" ");
            difference = difference / emptySpaceLength;
            String spaces = " ".repeat(difference);
            label.setText(label.getText() + spaces);
        }
    }

    int getTextLength(String text) {
        double totalTextLength = 0;
        for (int i = 0; i < text.length(); i++) {
            String character = text.substring(i, i + 1);
            Text textNode = new Text(character);

            double characterLength = textNode.getLayoutBounds().getWidth();
            totalTextLength += characterLength;
        }
        return (int) totalTextLength;
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
