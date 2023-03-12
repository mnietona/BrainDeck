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
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.Deck;

import java.util.List;

import java.io.IOException;
import java.util.ArrayList;

public class EditMenu {
    static DeckDaoNitriteImplementation ddni = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    public List<String> deckNames = new ArrayList<String>();
    public EditMenu() {
        // TODO: if possible create ddni in the main/globally...
        for (Deck d : ddni.getAllDecks()) {
            deckNames.add(d.getName());
        }
        /*
        deckNames.add("Math");
        deckNames.add("Algo");
        deckNames.add("Python");
        deckNames.add("Java");
         */
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
        deckNames.remove(selectedItem);
        //ddni.deleteDeck(selectedItem); // TODO: delete the deck from the database (with id or name)
        setCardPackLists();
        cardPack.setValue("");
        updateRectangleColor();
    }

    public void homeButtonAction() {
        accessNewWindow("/ulb/info307/g6/views/MainMenu.fxml");
    }

    public void setCardPackLists() {
        cardPack.getItems().clear();
        cardPack.getItems().addAll(deckNames);
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

