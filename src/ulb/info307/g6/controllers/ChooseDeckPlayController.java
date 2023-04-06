package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.ChooseDeckPlay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ChooseDeckPlayController implements ChooseDeckPlay.ChooseDeckPlayListener {
    private final Stage stage;
    private final Listener listener;

    private DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();
    private CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    private List<Deck> decks;

    private int[] lastIndex = new int[3];
    private int flipIndex = 0;
    private int numberOfFlipsAuthorizedForCurrentCard = 1;
    private int cardIndex = 0;
    private Deck currentDeck = null;

    private ChooseDeckPlay chooseDeckPlay;
    private enum Level {VERY_BAD, BAD, AVERAGE, GOOD, VERY_GOOD}

    public ChooseDeckPlayController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        chooseDeckPlay = new ChooseDeckPlay();
        chooseDeckPlay.setListener(this);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
            loader.load();
            chooseDeckPlay = loader.getController();
            chooseDeckPlay.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root, 600, 408));
            this.stage.setTitle("Study your decks");
            this.stage.show();
            showChoice();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void clickHome() {
        listener.clickHome();
        this.stage.hide();
    }

    public void showChoice() {
        setCardPackLists();
    }

    public void clickNextCard() {
        if (!currentDeck.isEmpty()) {
            getNextRandomCard();
            flipIndex = 0;
            updateDisplayArea();
        }
    }

    public void clickFlipCard() {
        if (currentDeck != null && !currentDeck.isEmpty()) {
            flipIndex = (flipIndex + 1) % (numberOfFlipsAuthorizedForCurrentCard + 1);
            updateDisplayArea();
        }
    }

    public void clickKnowledgeLevel() {
        if (currentDeck != null) {
            setChoice();
        }
    }

    public void updateKnowledgeLevel(Level level) {
        if (!currentDeck.isEmpty()) {
            Card card = currentDeck.getCardList().get(cardIndex);
            switch (level) {
                case VERY_BAD -> card.setKnowledgeLevel(0);
                case BAD -> card.setKnowledgeLevel(1);
                case AVERAGE -> card.setKnowledgeLevel(2);
                case GOOD -> card.setKnowledgeLevel(3);
                case VERY_GOOD -> card.setKnowledgeLevel(4);
            }
            databaseCard.updateCard(card);
            database.updateDeck(currentDeck);
        }
    }

    private void setCardPackLists() {
        decks = database.getAllDecks();
        chooseDeckPlay.listDecks.getItems().clear();
        for (Deck deck : decks) {
            chooseDeckPlay.listDecks.getItems().add(deck);
        }

        chooseDeckPlay.listDecks.setOnMouseClicked(event -> {

            currentDeck = chooseDeckPlay.listDecks.getSelectionModel().getSelectedItem();
            cardIndex = 0;
            flipIndex = 0;

            if (!currentDeck.isEmpty()) {
                getNextRandomCard();
                updateDisplayArea();
            } else {
                chooseDeckPlay.displayTextQA.setText("The deck " + currentDeck.getName() + " is empty");
            }
        });
    }

    private void getNextRandomCard() {
        int nextCardIndex = 0;
        if (currentDeck.getSize() == 2 || currentDeck.getSize() == 3) {
            nextCardIndex = (cardIndex + 1)%currentDeck.getSize();
        } else if (currentDeck.getSize() > 3) {
            Random rand = new Random();
            int random = rand.nextInt(currentDeck.getSize());
            while (random == lastIndex[0] || random == lastIndex[1] || random == lastIndex[2]) {
                random = rand.nextInt(currentDeck.getSize());
            }
            nextCardIndex = random;
        }
        cardIndex = nextCardIndex;
        lastIndex[2] = lastIndex[1];
        lastIndex[1] = lastIndex[0];
        lastIndex[0] = nextCardIndex;
    }

    private boolean questionIsDisplayed() {
        // We have not yet flipped the card, so the question is displayed
        return flipIndex == 0;
    }

    public void updateDisplayArea() {
        if (currentDeck == null) {
            //chooseDeckPlay.displayTitle.setText("");
            chooseDeckPlay.displayTextQA.setText("No deck selected");
        } else {
            Card card = currentDeck.getCardList().get(cardIndex);
            if (CardGapFill.isCardGapFilType(card)) {
                // We transform the card into its extended type cardGapFill if necessary
                // (to know if the card is of the type cardGapFill, we check whether its question contains the "gap" marker "_")
                card = new CardGapFill(card.getQuestion(),card.getAnswer());
            }
            numberOfFlipsAuthorizedForCurrentCard = card.getNumberOfFlips();
            if (questionIsDisplayed()) {
                chooseDeckPlay.cardBackground.setStyle("-fx-background-color: #ADD8E6; -fx-background-radius: 10px;");
                chooseDeckPlay.displayTextQA.setText(card.getQuestion());
            } else  {
                chooseDeckPlay.cardBackground.setStyle("-fx-background-color: #FFA07A; -fx-background-radius: 10px;");
                chooseDeckPlay.displayTextQA.setText(card.getNthFlippedAnswer(flipIndex));
            }
        }
    }

    public void setChoice() {
        chooseDeckPlay.knowledgeLevel.getItems().clear();
        ObservableList<String> options = FXCollections.observableArrayList("Very bad", "Bad", "Average", "Good", "Very good");
        for (String option : options) {
            chooseDeckPlay.knowledgeLevel.getItems().add(option);
        }
        chooseDeckPlay.knowledgeLevel.setOnAction(event -> {
            String stringLevel = chooseDeckPlay.knowledgeLevel.getValue();
            switch(stringLevel) {
                case("Very bad") -> updateKnowledgeLevel(Level.VERY_BAD);
                case("Bad") -> updateKnowledgeLevel(Level.BAD);
                case("Average") -> updateKnowledgeLevel(Level.AVERAGE);
                case("Good") -> updateKnowledgeLevel(Level.GOOD);
                case("Very good") -> updateKnowledgeLevel(Level.VERY_GOOD);
            }
        });
    }

    public interface Listener {
        void clickHome();
    }
}

