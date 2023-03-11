package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class EditMenu {
    public ArrayList<String> packNames = new ArrayList<String>();
    public EditMenu() {
        packNames.add("Math");
        packNames.add("Algo");
        packNames.add("Python");
    }
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonEdit;

    @FXML
    private ComboBox<String> cardPack;

    @FXML
    private VBox root;
    @FXML
    private Rectangle cardRectangle;


    @FXML
    protected void clickEdit() {
        System.out.println("Edit");
    }

    @FXML
    private Button buttonAdd;

    @FXML
    protected void clickCreate() {
        System.out.println("Add");
        accessNewWindow("/ulb/info307/g6/views/CreatePackMenu.fxml");

    }

    @FXML
    protected void clickChoice() {
        setCardPackLists();
    }


    @FXML
    protected void clickRemove() {
        String selectedItem = cardPack.getSelectionModel().getSelectedItem();
        packNames.remove(selectedItem);
        setCardPackLists();
        cardPack.setValue("");
        updateRectangleColor();
    }

    public void homeButtonAction() {
        accessNewWindow("/ulb/info307/g6/views/MainMenu.fxml");
    }

    public void setCardPackLists() {
        cardPack.getItems().clear();
        cardPack.getItems().addAll(packNames);
        cardPack.setOnAction(event -> { // click on an item
            String selectedItem = cardPack.getSelectionModel().getSelectedItem();
            updateRectangleColor();

        });

    }

    public void updateRectangleColor() {
        String selectedItem = cardPack.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            switch (selectedItem) {
                case "Math":
                    cardRectangle.setFill(Color.RED);
                    break;
                case "Algo":
                    cardRectangle.setFill(Color.GREEN);
                    break;
                case "Python":
                    cardRectangle.setFill(Color.BLUE);
                    break;
                default:
                    cardRectangle.setFill(Color.GRAY);
                    break;
            }
        }

    }

    public void accessNewWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            //MainMenu newWindowMenu = loader.getController();
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) buttonHome.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

