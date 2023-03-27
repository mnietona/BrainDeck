package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.controllers.CardDaoNitriteImplementation;
import ulb.info307.g6.controllers.MainController;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;

import java.util.List;

import java.io.IOException;
import javafx.scene.text.Text;
import javafx.beans.value.*;

import java.util.Random;


// TODO: Removes spaces before "Answer" to allign it in the middle

public class ChooseDeckPlay {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation(); // Initialize the DAO for the database
    public List<Deck> decks;

    private int[] lastIndex = new int[2];
    private int cardIndex = 0;
    private Deck currentDeck = null;
    public ChooseDeckPlay() {}

    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonBackCard;

    @FXML
    private Button buttonFlipCard;

    @FXML
    private Button buttonNextCard;

    @FXML
    private ListView<String> listDecks = new ListView();

    @FXML
    private Button selectDeck;

    @FXML
    private Text displayTitle;

    @FXML
    private Text displayTextQA;
    @FXML
    private ComboBox<String> knowledgeLevel;


    // Pour click knowledge
    public void setChoice() {
        knowledgeLevel.getItems().clear();
        ObservableList<String> options = FXCollections.observableArrayList("Very bad", "Bad", "Average", "Good", "Very good");
        for (String option : options) {
            knowledgeLevel.getItems().add(option);
        }
        knowledgeLevel.setOnAction(event -> { // click on an item
            updateKnowledgeLevel();
        });
    }

    @FXML
    protected void clickKnowledgeLevel() {
        if (currentDeck != null) { // Checks if a deck is currently selected
            setChoice();
        }
    }

    public void updateKnowledgeLevel() {
        if (currentDeck.getCardList().size() > 0) {
            Card card = currentDeck.getCardList().get(cardIndex);
            System.out.println("Knowledge level: " + card.getKnowledgeLevel());
            if (knowledgeLevel.getValue() == "Very bad") {
                card.setKnowledgeLevel(0);
            } else if (knowledgeLevel.getValue() == "Bad") {
                card.setKnowledgeLevel(1);
            } else if (knowledgeLevel.getValue() == "Average") {
                card.setKnowledgeLevel(2);
            } else if (knowledgeLevel.getValue() == "Good") {
                card.setKnowledgeLevel(3);
            } else if (knowledgeLevel.getValue() == "Very good") {
                card.setKnowledgeLevel(4);
            }
            databaseCard.updateCard(card);
            database.updateDeck(currentDeck);
            System.out.println("Knowledge level after update: " + card.getKnowledgeLevel());
        }
    }

    @FXML
    protected void clickFlipCard() {
        if (currentDeck != null) {
            if (displayTitle.getText() == "Question") {
                updateDisplayArea("  Answer");
            } else if (displayTitle.getText() == "  Answer") {
                updateDisplayArea("Question");
            }
        }
        System.out.println("Flip");
    }

    // TODO : Prendre en compte le cas des Decks vides.
    @FXML
    protected void getNextRandomCard() {
        Random rand = new Random();
        int random = rand.nextInt(currentDeck.getCardList().size());
        while (random == lastIndex[0] || random == lastIndex[1]) {
            random = rand.nextInt(currentDeck.getCardList().size());
        }
        cardIndex = random;
        lastIndex[1] = lastIndex[0];
        lastIndex[0] = random;
    }
    @FXML
    protected void clickNextCard() {
        getNextRandomCard();
        updateDisplayArea("Question");
        System.out.println("Next");
    }
    @FXML
    protected void clickBackCard() {
        if (cardIndex > 0) {
            cardIndex--;
            updateDisplayArea("Question");
        }
        System.out.println("Back");

    }
    @FXML
    public void showChoice() {
        setCardPackLists();
    }

    private ChooseDeckPlayListener listener;

    public void setListener(ChooseDeckPlayListener listener) {
        this.listener = listener;
    }

    public void clickHome() {
        listener.clickHome();
    }


    public void setCardPackLists() {
        decks = database.getAllDecks();
        listDecks.getItems().clear();
        for (Deck deck : decks) {
            listDecks.getItems().add(deck.getName());
        }

        selectDeck.setOnAction(event -> {
            String selectedDeck = listDecks.getSelectionModel().getSelectedItem();
            for (Deck deck : decks){
                if (deck.isDeck(selectedDeck)){
                    currentDeck = deck;
                }
            }
            System.out.println("Selected Deck : "+selectedDeck);
            System.out.println(currentDeck.getCardList());
            cardIndex = 0;
            getNextRandomCard();
            updateDisplayArea("Question");
    });

        /**cardPack.setOnAction(event -> { // click on an item
            cardIndex = 0;
            currentDeck = cardPack.getSelectionModel().getSelectedItem();
            getNextRandomCard();
            updateDisplayArea("Question");
        });*/

    }
    public void updateDisplayArea(String name) {
        if (currentDeck == null) {
            displayTitle.setText("");
            displayTextQA.setText("No deck selected");
        } else if (currentDeck.getCardList().size() == 0) {
            displayTitle.setText("");
            displayTextQA.setText("The deck " + currentDeck.getName() + " is empty");
        } else { // Deck is not empty
            Card card = currentDeck.getCardList().get(cardIndex);
            if (name == "Question") {
                displayTitle.setText("Question");
                displayTextQA.setText(card.getQuestion());
            } else if (name == "  Answer") {
                displayTitle.setText("  Answer");
                displayTextQA.setText(card.getAnswer());
            } else if (name == "Deck finished") {
                displayTitle.setText("");
                displayTextQA.setText("You have been through all the questions of this deck");
            }
        }
    }

    public interface ChooseDeckPlayListener {
        void clickHome();
    }

}

