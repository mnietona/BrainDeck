package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EditMenu {
    @FXML
    Button buttonEdit;

     public void clickEdit() {
         System.out.println("Edit");
     }

    @FXML
    Button buttonAdd;

    public void clickAdd() {
        System.out.println("Add");
    }

    @FXML
    Button buttonRemove;

    public void clickRemove() {
        System.out.println("Remove");
    }
}
