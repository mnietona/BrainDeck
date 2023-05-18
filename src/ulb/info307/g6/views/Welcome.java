package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * View controller of the Welcome menu, implements View interface and is the controller for the Welcome.fxml file.
 * Contains :
 * - two buttons, to go to the study or edit menus
 */
public class Welcome implements View {
    private WelcomeListener listener;
    @FXML
    private Text mainMenuStat1, mainMenuStat2;

    @Override
    public void setListener(Object listener) {
        this.listener = (WelcomeListener) listener;
    }

    public void setGlobalStatistics(int dayStreak, int longestDayStreak) {
        mainMenuStat1.setText(dayStreak+"\nCurrent\nday streak");
        mainMenuStat2.setText(longestDayStreak+"\nLongest\nday streak");
    }

    public void studyButtonAction() {
        listener.studyButtonAction();
    }

    public void editButtonAction() {
        listener.editButtonAction();
    }

    public void statisticsButtonAction() {
        listener.statisticsButtonAction();
    }

    public void achievementsButtonAction() {
        listener.achievementsButtonAction();
    }

    public void helpButtonAction() {
        listener.helpButtonAction();
    }

    /**
     * Controller (passed in constructor) will implement the listener interface to "listen" to then handle
     * actions that happens in view controller.
     */
    public interface WelcomeListener {
        void studyButtonAction();
        void editButtonAction();
        void statisticsButtonAction();
        void achievementsButtonAction();
        void showMainMenuStatistics();
        void helpButtonAction();
    }
}
