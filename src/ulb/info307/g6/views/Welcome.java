package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.time.Duration;
import java.util.Objects;

/**
 * View controller of the Welcome menu, implements View interface and is the controller for the Welcome.fxml file.
 * Contains :
 * - two buttons, to go to the study or edit menus
 */
public class Welcome implements View {
    private WelcomeListener listener;
    @FXML
    private WebView welcomeWebView;
    @FXML
    private Text mainMenuStat1, mainMenuStat2;

    @Override
    public void setListener(Object listener) {
        this.listener = (WelcomeListener) listener;
    }

    public void setupWelcomeWebView() {
        WebEngine webEngine = welcomeWebView.getEngine();
        String htmlFilePath = Objects.requireNonNull(getClass().getResource("Welcome.html")).toExternalForm();
        webEngine.load(htmlFilePath);
    }

    public void setGlobalStatistics(int dayStreak, int longestDayStreak) {
        mainMenuStat1.setText(dayStreak+"\nCurrent\nday streak");
        mainMenuStat2.setText(longestDayStreak+"\nLongest\nday streak");
        // TODO: move totaltimespent to statistics db
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

    public void setColorWebView() {
        welcomeWebView.setPageFill(Color.TRANSPARENT);
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
    }
}
