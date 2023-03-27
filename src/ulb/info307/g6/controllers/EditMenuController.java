package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.EditMenu;

import java.io.IOException;

public class EditMenuController {
    private final Stage stage;
    private final Listener listener;

    public EditMenuController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
            Parent root = loader.load();
            EditMenu editMenu = loader.getController();
            editMenu.setListener(new EditMenu.EditMenuListener() {
                @Override
                public void clickHome() {
                    listener.clickHome();
                }
            });
            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Edit your decks");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        stage.hide();
    }

    public interface Listener {
        void clickHome();
    }
}
