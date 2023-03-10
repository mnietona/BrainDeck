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

public class EditMenu {
    @FXML
    private Button buttonBack;
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
    protected void clickAdd() {
        System.out.println("Add");
    }

    @FXML
    protected void clickChoice() {
        setCardPackLists();
    }


    @FXML
    protected void clickRemove() {
        System.out.println("Remove");
    }

    public void backButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/MainMenu.fxml"));
            Parent root = loader.load();
            MainMenu mainMenu = loader.getController();
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCardPackLists() {
        cardPack.getItems().clear();
        cardPack.getItems().addAll("Math", "Algo", "Python");
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
                    cardRectangle.setFill(Color.WHITE);
                    break;
            }
        }

    }

}

