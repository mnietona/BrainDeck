package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;


public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/ulb/info307/g6/views/MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 408);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu");
            primaryStage.show();

        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

