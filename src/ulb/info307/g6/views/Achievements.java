package ulb.info307.g6.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Achievements implements View {
    @FXML
    private ProgressBar firstProgressBar;
    @FXML
    private ProgressBar secondProgressBar;
    private AchievementsListener listener;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    public void showProgressBars(double progress1,double progress2) {
        firstProgressBar.setProgress(progress1);
        secondProgressBar.setProgress(progress2);
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

