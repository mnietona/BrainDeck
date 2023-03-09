package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ulb.info307.g6.views.MainMenu;
import java.io.IOException;
import java.net.URL;

public class EditCardController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/ulb/info307/g6/views/EditCardMenu.fxml"));
            VBox root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Edit Card Menu");
            primaryStage.show();

        } catch (IOException e)
        {
            showErrorAlert();
        }

    }

    private void showErrorAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

