package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ulb.info307.g6.views.ChooseDeckPlay;
import ulb.info307.g6.views.ChooseDeckPlay.*;
import ulb.info307.g6.views.EditMenu;
import ulb.info307.g6.views.MainMenu;
import ulb.info307.g6.views.MainMenu.*;
import ulb.info307.g6.views.EditMenu.*;

import java.io.IOException;


public class MainController extends Application implements MenuController.Listener, ChooseDeckPlayController.Listener, EditMenuController.Listener {


    private MenuController menuController;
    private Stage currentStage;


    @Override
    public void start(Stage primaryStage) {
        menuController = new MenuController(primaryStage, this);
        try {
            menuController.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void studyButtonAction() {
        // Actions à effectuer lors du clic sur le bouton "Study"
        menuController.hide();
        Stage stage = new Stage();
        ChooseDeckPlayController chooseDeckPlayController = new ChooseDeckPlayController(stage, this);
        chooseDeckPlayController.show();

    }


    @Override
    public void editButtonAction() {
        menuController.hide();
        Stage stage = new Stage();
        EditMenuController editMenuController = new EditMenuController(stage, this);
        editMenuController.show();

    }


    @Override
    public void clickHome() {
        // Actions à effectuer lors du clic sur le bouton "Home" de la vue ChooseDeckPlay
        currentStage.hide();
        System.out.println("Home");
        menuController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

