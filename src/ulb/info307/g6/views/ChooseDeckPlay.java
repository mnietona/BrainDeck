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

public class ChooseDeckPlay {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    public List<Deck> decks;
    public ChooseDeckPlay() {}

    // definir
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonBackCard;

    @FXML
    private Button buttonFlipCard;

    @FXML
    private Button buttonNextCard;

    @FXML
    private ComboBox<Deck> cardPack;

    @FXML
    private Rectangle cardRectangle;

    @FXML
    protected void clickFlipCard() {
        System.out.println("Flip");
    }
    @FXML
    protected void clickNextCard() {
        System.out.println("Next");

    }
    @FXML
    protected void clickBackCard() {
        System.out.println("Back");

    }
    @FXML
    protected void clickChoice() {
        setCardPackLists();
    }


    public void clickHome() {
        accessNewWindow("/ulb/info307/g6/views/MainMenu.fxml");
    }

    public void setCardPackLists() {
        decks = database.getAllDecks();
        cardPack.getItems().clear();
        for (Deck deck : decks) {
            cardPack.getItems().add(deck);
        }
        cardPack.setOnAction(event -> { // click on an item
            updateRectangleColor();
        });

    }

    public void updateRectangleColor() {
        Deck selectedItem = cardPack.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            switch (selectedItem.getName()) {
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

