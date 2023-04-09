package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditDeck;

import java.io.IOException;
import java.util.List;

public class EditDeckController implements EditDeck.EditMenuListener, CreateDeckController.Listener, EditCardController.Listener {

    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    private final Stage stage;
    private final Listener listener;
    private EditDeck editDeckController;



    public EditDeckController(Stage stage, Listener listener) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditDeck.fxml"));
            Parent root = loader.load();
            editDeckView = loader.getController();
            editDeckView.setListener(this);

            Scene scene = new Scene(root, 600, 408);
            stage.setScene(scene);
            stage.setTitle("Edit your decks");

            // Call the setDeckList method when the controller is shown to populate the cardPack list
            setDeckList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickEdit() {
        List<Deck> decks = database.getAllDecks();
        if (decks != null) {
            Deck selectedItem = editDeckView.deckListViewEditMenu.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new EditCardController(stage, selectedItem);
            }
        }
    }


    @Override
    public void clickCreate() {
        this.stage.hide();
        Stage stage = new Stage();
        CreateDeckController createDeckController = new CreateDeckController(stage, this);
        createDeckController.show();
    }

    public void setDeckList() {
        List<Deck> decks = database.getAllDecks();
        editDeckView.setDeckListView(decks);
        editDeckView.updateDeckTitle();
    }


    @Override
    public void clickRemove() {
        Deck selectedItem = editDeckView.deckListViewEditMenu.getSelectionModel().getSelectedItem();
        database.deleteDeck(selectedItem);
        database.updateDeck(selectedItem);
        setDeckList();
    }
}
