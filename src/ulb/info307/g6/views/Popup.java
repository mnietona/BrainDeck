package ulb.info307.g6.views;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {

    private final Alert alert;

    public Popup(String message) {
        alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.initModality(Modality.APPLICATION_MODAL);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        stage.setAlwaysOnTop(true);
    }

    public void showAndWait() {
        alert.showAndWait();
    }
}

