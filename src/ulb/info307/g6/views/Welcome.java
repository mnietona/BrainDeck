package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * The Welcome class implements the View interface and is the controller for the Welcome.fxml file.
 * It is used to display the welcome screen.
 * It has two buttons to go to the study or edit mode.
 */
public class Welcome implements View {
    @FXML
    private Button studyButton, editButton;
    private WelcomeListener listener;

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

    /**
     * Controller (passed in constructor) will implement the listener interface to "listen" to then handle
     * actions that happens in view controller.
     */
    public interface WelcomeListener {
        void studyButtonAction();
        void editButtonAction();
    }
}
