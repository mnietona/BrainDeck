package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.ChooseDeckPlay;
import ulb.info307.g6.views.ChooseDeckPlay.*;

import java.io.IOException;

public class ChooseDeckPlayController implements ChooseDeckPlayListener{
    private final Stage stage;
    private final Listener listener;

    private ChooseDeckPlay chooseDeckPlayController;


    public ChooseDeckPlayController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
            loader.load();
            chooseDeckPlayController = loader.getController();
            chooseDeckPlayController.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root, 600, 408));
            this.stage.setTitle("Study your decks");
            this.stage.show();
            chooseDeckPlayController.showChoice();
            // quand on clique sur le bouton "home", on appelle la méthode clickHome()


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public interface Listener {
        void clickHome();
    }
}

