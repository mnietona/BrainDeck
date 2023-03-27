package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.ChooseDeckPlay;
import ulb.info307.g6.views.ChooseDeckPlay.*;

import java.io.IOException;



public class ChooseDeckPlayController implements ChooseDeckPlayListener {
    private final Stage stage;
    private final Listener listener;

    private ChooseDeckPlay chooseDeckPlay;

    public ChooseDeckPlayController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
            Parent root = loader.load();
            chooseDeckPlay = loader.getController();
            chooseDeckPlay.setListener(this);
            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Study your decks");
            stage.show();
            chooseDeckPlay.showChoice();
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


