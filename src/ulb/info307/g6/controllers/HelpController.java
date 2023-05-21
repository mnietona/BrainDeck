package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.views.Help;

/**
 * Controller for the help view.
 * This controller is used to display the help view.
 */

public class HelpController extends Controller implements Help.HelpListener {
    public final Help helpView;
    private int step = 0;

    public HelpController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Help.fxml", "Help");
        helpView = (Help) view;
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    private void displayHelp(int step){
        helpView.updateText("Next");
        System.out.println(step);
    }

    @Override
    public void clickNext() {
        displayHelp(step++);
    }
}
