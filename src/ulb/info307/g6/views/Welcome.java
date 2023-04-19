package ulb.info307.g6.views;

/**
 * View controller of the Welcome menu, implements View interface and is the controller for the Welcome.fxml file.
 * Contains :
 * - two buttons, to go to the study or edit menus
 */
public class Welcome implements View {
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

    public void statisticsButtonAction() {
        listener.statisticsButtonAction();
    }

    /**
     * Controller (passed in constructor) will implement the listener interface to "listen" to then handle
     * actions that happens in view controller.
     */
    public interface WelcomeListener {
        void studyButtonAction();
        void editButtonAction();
        void statisticsButtonAction();
    }
}
