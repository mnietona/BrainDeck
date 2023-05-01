package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.views.PreviewCard;

import java.io.IOException;

public class PreviewCardController {
    Stage stage;
    private PreviewCard previewCardView;
    private PreviewCardControllerListener listener;
    PreviewCardController(String url, Stage mainWindowStage) {
        int MIN_WIDTH = 416, MIN_HEIGHT = 439;  // Window of size 400x400 with overhead
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/CardPreview.fxml"));
            Parent root = loader.load();
            previewCardView = loader.getController();  // To get the controller of the new menu
            previewCardView.setPageView(url);
            Scene scene = new Scene(root, 400, 400);
            stage = new Stage();
            stage.setX(mainWindowStage.getX() + mainWindowStage.getWidth());
            stage.setY(mainWindowStage.getY());
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setOnCloseRequest(ev -> listener.closeRequest());
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

    public void changePage(String url) {
        previewCardView.setPageView(url);
    }

    public void setListener(Object object) {
        listener = (PreviewCardControllerListener) object;
    }
}
