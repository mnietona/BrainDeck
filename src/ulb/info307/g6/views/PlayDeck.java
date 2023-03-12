package ulb.info307.g6.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayDeck {
    @FXML
    public Button returnButton;
    @FXML
    public Button flipButton;
    @FXML
    public Button nextButton;



    @FXML
    public void clickReturn(ActionEvent event) {
        System.out.println("test3");

    }
    @FXML
    public void clickFlip(ActionEvent event) {
        System.out.println("test2");

    }
    @FXML
    public void clickNext(ActionEvent event) {
        System.out.println("test1");

    }
}
