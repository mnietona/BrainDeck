package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.views.Help;

public class HelpController extends Controller implements Help.HelpListener {
    public final Help helpView;

    public HelpController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Help.fxml", "Help");
        helpView = (Help) view;
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }
}
