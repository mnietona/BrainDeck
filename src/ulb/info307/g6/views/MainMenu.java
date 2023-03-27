package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ulb.info307.g6.controllers.MainController;

public class MainMenu {

    @FXML
    public Button playButton;

    @FXML
    public Button editButton;

    private MainMenuListener listener;


    public void setListener(MainMenuListener listener) {
        this.listener = listener;
    }

    public void studyButtonAction() {
        listener.studyButtonAction();



    }

    public void editButtonAction() {
        listener.editButtonAction();

    }

    public interface MainMenuListener {
        void studyButtonAction();
        void editButtonAction();
    }



}

