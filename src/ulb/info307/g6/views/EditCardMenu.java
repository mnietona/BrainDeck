package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.controllers.CardDaoNitriteImplementation;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.views.EditMenu;


import java.util.List;

import java.io.IOException;
import java.util.ArrayList;

public class EditCardMenu {
    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation(); // Initialize the DAO for the database

    public Deck deck = EditMenu.selectedDeck;
    public List<Card> cards; // liste de cartes de Python


    public EditCardMenu() {}

    @FXML
    private TextArea questionInput;

    @FXML
    private TextArea answerInput;


    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonEditCard;

    @FXML
    private ComboBox<Card> pickCard;

    @FXML
    private VBox root;


    @FXML
    protected void clickEditCard() {

        Card selectedItem = deck.getCardList().get(pickCard.getSelectionModel().getSelectedIndex());
        if (selectedItem != null) {
            selectedItem.setQuestion(questionInput.getText());
            selectedItem.setAnswer(answerInput.getText());
            databaseCard.updateCard(selectedItem);
            database.updateDeck(deck);
            updateQuestionAnswer();
            System.out.println("Edit : " + selectedItem.getId());
        }



    }

    @FXML
    private Button buttonAddCard;

    @FXML
    protected void clickCreateCard() {
        Card card = new Card(questionInput.getText(), answerInput.getText());
        deck.addCard(card);
        databaseCard.updateCard(card);
        database.updateDeck(deck);
        updateQuestionAnswer();
        System.out.println("Add : " + card.getId());

    }

    @FXML
    protected void clickChoice() {
        setCardLists();
    }


    @FXML
    protected void clickRemoveCard() {
        Card selectedItem = deck.getCardList().get(pickCard.getSelectionModel().getSelectedIndex());
        deck.removeCard(selectedItem);
        databaseCard.deleteCard(selectedItem);
        database.updateDeck(deck);
        updateQuestionAnswer();
        System.out.println("Remove : " + selectedItem.getId());
        setCardLists();
    }

    public void backButtonAction() {
        accessNewWindow("/ulb/info307/g6/views/EditMenu.fxml");
    }


    public void setCardLists() {

        pickCard.getItems().clear();
        for (Card card : deck.getCardList()) {
            pickCard.getItems().add(card);

        }
        pickCard.setOnAction(event -> { // click on an item
            updateQuestionAnswer();
        });

    }

    public void updateQuestionAnswer() {

        Card selectedItem =  pickCard.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            questionInput.setText(selectedItem.getQuestion());
            answerInput.setText(selectedItem.getAnswer());
        }
        else {
            questionInput.setText("");
            answerInput.setText("");
        }
    }



    public void accessNewWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            //MainMenu newWindowMenu = loader.getController();
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

