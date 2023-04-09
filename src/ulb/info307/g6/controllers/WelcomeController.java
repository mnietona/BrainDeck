package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.Welcome;
import ulb.info307.g6.controllers.Controller;
import java.io.IOException;

/**
 * Main menu (called Welcome) controller. Creates window and handles
 */
public class WelcomeController extends Controller implements Welcome.WelcomeListener  {

    public WelcomeController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Welcome.fxml", "Menu");
    }

    @Override
    public void studyButtonAction() {
        new StudyController(stage);
    }

    @Override
    public void editButtonAction() {
        new EditDeckController(stage);
    }
}
