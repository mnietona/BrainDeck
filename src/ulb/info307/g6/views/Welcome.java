package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * View controller of the Welcome menu, implements View interface and is the controller for the Welcome.fxml file.
 * Contains :
 * - two buttons, to go to the study or edit menus
 */
public class Welcome implements View {
    private WelcomeListener listener;
    @FXML
    private WebView welcomeWebView;

    @Override
    public void setListener(Object listener) {
        this.listener = (WelcomeListener) listener;
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
    }
}
