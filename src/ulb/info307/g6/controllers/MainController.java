package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ulb.info307.g6.views.MainMenu;
import java.io.IOException;
import java.net.URL;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //URL tt = MainController.class.getResource("../views/MainMenu.fxml");
            //System.out.println(MainController.class.getResource());
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/ulb/info307/g6/views/MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu");
            primaryStage.show();

        } catch (IOException e){
            showErrorAlert();
        }

    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

