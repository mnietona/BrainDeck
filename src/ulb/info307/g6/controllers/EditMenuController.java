package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.EditMenu;

import java.io.IOException;

public class EditMenuController implements EditMenu.EditMenuListener {
    private final Stage stage;
    private final Listener listener;

    private EditMenu editMenuController;


    public EditMenuController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
            loader.load();
            editMenuController = loader.getController();
            editMenuController.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root, 600, 408));
            this.stage.setTitle("Edit your decks");
            this.stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickHome() {

        listener.clickHome();
        this.stage.hide();
    }



    public interface Listener {
        void clickHome();
    }
}
