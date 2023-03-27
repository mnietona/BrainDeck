package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditMenu;

import java.io.IOException;
import java.util.List;

public class EditMenuController implements EditMenu.EditMenuListener, CreatePackMenuController.Listener, EditCardMenuController.Listener {

    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    private final Stage stage;
    private final Listener listener;
    private EditMenu editMenuController;



    public EditMenuController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/EditMenu.fxml"));
            loader.load();
            editMenuController = loader.getController();
            editMenuController.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root, 600, 408));
            this.stage.setTitle("Edit your decks");
            this.stage.show();

            // Call the clickChoice method when the controller is shown to populate the cardPack list
            clickChoice();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickHome() {
        listener.clickHome();
        this.stage.hide();
    }

    @Override
    public void clickEdit() {
        List<Deck> decks = fetchDecksFromDatabase();
        if (decks != null) {
            Deck selectedItem = editMenuController.cardPack.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                this.stage.hide();
                Stage stage = new Stage();
                EditCardMenuController editCardMenuController = new EditCardMenuController(stage, this, selectedItem);
                editCardMenuController.show();
            }
        }
    }


    @Override
    public void clickCreate() {
        this.stage.hide();
        Stage stage = new Stage();
        CreatePackMenuController createPackMenuController = new CreatePackMenuController(stage, this);
        createPackMenuController.show();
    }


    @Override
    public void clickChoice() {
        List<Deck> decks = fetchDecksFromDatabase();
        editMenuController.setCardPackLists(decks);
        editMenuController.updateDeckTitle();
    }


    @Override
    public void clickRemove() {
        Deck selectedItem = editMenuController.cardPack.getSelectionModel().getSelectedItem();
        database.deleteDeck(selectedItem);
        database.updateDeck(selectedItem);
        clickChoice();
    }


    private List<Deck> fetchDecksFromDatabase() {
        return database.getAllDecks();
    }
    @Override
    public void clickBack(){
        show();
    }

    public interface Listener {
        void clickHome();
    }
}
