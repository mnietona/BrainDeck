package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.Deck;

import java.util.List;

import java.io.IOException;

public class EditMenu {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    public List<Deck> decks;
    public EditMenu() {}
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonEdit;

    @FXML
    private ComboBox<Deck> cardPack;

    @FXML
    private VBox root;
    @FXML
    private Text deckTitle;

    // creer une varibale qui va contenir le deck selectionné
    public static Deck selectedDeck;
    @FXML
    protected void clickEdit() {
        System.out.println("Edit");
        // faire passer le deck dans la scene edit
        if (decks != null) {
            Deck selectedItem = cardPack.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedDeck = selectedItem;
                accessNewWindow("/ulb/info307/g6/views/EditCardMenu.fxml");
            }
        }
        //accessNewWindow("/ulb/info307/g6/views/EditCardMenu.fxml");
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
        Deck selectedItem = cardPack.getSelectionModel().getSelectedItem();
        database.deleteDeck(selectedItem);
        database.updateDeck(selectedItem);
        setCardPackLists();
        updateDeckTitle();
    }

    public void homeButtonAction() {
        accessNewWindow("/ulb/info307/g6/views/MainMenu.fxml");
    }

    public void setCardPackLists() {
        decks = database.getAllDecks();
        cardPack.getItems().clear();
        for (Deck deck : decks) {
            cardPack.getItems().add(deck);
        }
        cardPack.setOnAction(event -> { // click on an item
            updateDeckTitle();
        });
    }

    public void updateDeckTitle() {
        Deck selectedItem = cardPack.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deckTitle.setText(selectedItem.getName());
        } else {
            deckTitle.setText("No deck selected");
        }
    }

    public void accessNewWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            //MainMenu newWindowMenu = loader.getController();
            Scene scene = new Scene(root, 600, 408);
            Stage stage = (Stage) buttonHome.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

