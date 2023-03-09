package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EditMenu {
    @FXML
    private Button buttonEdit;

    @FXML
    protected void clickEdit() {
         System.out.println("Edit");
     }

    @FXML
    private Button buttonAdd;

    @FXML
    protected void clickAdd() {
        System.out.println("Add");
    }

    @FXML
    private Button buttonRemove;

    @FXML
    protected void clickRemove() {
        System.out.println("Remove");
    }
}
