package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ulb.info307.g6.views.MainMenu;
import java.io.IOException;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
            VBox root = loader.load();
            MainMenu controller = loader.getController();
            controller.playButton.setOnAction(event -> controller.playButtonAction());
            controller.editButton.setOnAction(event -> controller.editButtonAction());
            Scene scene = new Scene(root, 300, 200);
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

