package ulb.info307.g6.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ulb.info307.g6.views.PreviewCard;

import java.io.IOException;

public class PreviewCardController {
    Stage stage;
    private PreviewCard previewCardView;
    private PreviewCardControllerListener listener;
    PreviewCardController(String url) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/CardPreview.fxml"));
            Parent root = loader.load();
            previewCardView = loader.getController();  // To get the controller of the new menu
            previewCardView.setPageView(url);
            Scene scene = new Scene(root, 600, 600);
            stage = new Stage();
            stage.setOnCloseRequest(ev -> {
                listener.closeRequest();
            });
            stage.setTitle("Card Preview");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }

    public interface PreviewCardControllerListener {
        void closeRequest();
    }

    public void setListener(Object object) {
        listener = (PreviewCardControllerListener) object;
    }
}
