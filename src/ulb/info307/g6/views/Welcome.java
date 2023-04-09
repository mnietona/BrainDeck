package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Welcome implements View {
    @FXML
    public Button playButton;
    @FXML
    public Button editButton;
    private WelcomeListener listener;

    public void setListener(WelcomeListener listener) {
        this.listener = listener;
    }

    public void studyButtonAction() {
        listener.studyButtonAction();
    }

    public void editButtonAction() {
        listener.editButtonAction();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (WelcomeListener) listener;
    }

    /**
     * Controller (passed in constructor) listens to actions that happens in view controller.
     */
    public interface WelcomeListener {
        void studyButtonAction();
        void editButtonAction();
    }
}
