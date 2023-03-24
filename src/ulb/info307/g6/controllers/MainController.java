package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.views.ChooseDeckPlay;
import ulb.info307.g6.views.MainMenu;
import ulb.info307.g6.views.MainMenu.*;

import java.io.IOException;


public class MainController extends Application implements MainMenuListener {


    private Stage currentStage; // to store the current stage

    @Override
    public void start(Stage primaryStage) {
        currentStage = primaryStage;
        try {
            FXMLLoader loader = getMainMenu();
            Parent root = loader.load();
            MainMenu mainMenu = loader.getController(); // to get the controller of the new window
            mainMenu.setListener(this); // to set the main controller of the new window
            Scene scene = new Scene(root, 600, 408);
            currentStage = new Stage(); // to set the current stage
            currentStage.setScene(scene);
            currentStage.setTitle("Menu");
            currentStage.show();

        } catch (IOException e) {
            showErrorAlert();
        }
    }


    @NotNull
    private static FXMLLoader getMainMenu() {
        return new FXMLLoader(MainController.class.getResource("/ulb/info307/g6/views/MainMenu.fxml"));
    }

    public void studyButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Study"
        currentStage.hide();
        try {
            FXMLLoader loader = getChooseDeckPlay();
            Parent root = loader.load();
            ChooseDeckPlay chooseDeckPlay = loader.getController(); // to get the controller of the new window
            Scene scene = new Scene(root, 600, 408);
            currentStage = new Stage(); // to set the current stage
            currentStage.setScene(scene);
            currentStage.setTitle("Study your decks");
            currentStage.show();
            chooseDeckPlay.showChoice();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private FXMLLoader getChooseDeckPlay() {
        return new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
    }

    public void editButtonAction() {
        currentStage.hide();
        // Actions à effectuer lors du clic sur le bouton "Edit"
        try {
            FXMLLoader loader = getEditMenu();
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 408);
            currentStage = new Stage(); // to set the current stage
            currentStage.setTitle("Edit your decks");
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    @NotNull
    private FXMLLoader getEditMenu() {
        return new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
    }


    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
    }


    public static void main(String[] args) {
        launch(args);
    }
}

