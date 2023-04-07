package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.ChooseDeckPlay;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ChooseDeckPlayController implements ChooseDeckPlay.ChooseDeckPlayListener {
    private static final String BACKGROUND_QUESTION = "-fx-background-color: #ADD8E6; -fx-background-radius: 10px";
    private static final String BACKGROUND_ANSWER = "-fx-background-color: #FFA07A; -fx-background-radius: 10px";
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
            chooseDeckPlay.setSliderLabels();
            chooseDeckPlay.deactivateSlider();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void clickHome() {
        updateCardKnowledgeLevel();
        listener.clickHome();
        this.stage.hide();
    }

    public void showChoice() {
        setDeckList();
    }

    public void clickNextCard() {
        if (!currentDeck.isEmpty()) {
            updateCardKnowledgeLevel();
            getNextRandomCard();
            flipIndex = 0;
            updateDisplayArea();
            chooseDeckPlay.deactivateSlider();
            updateSliderPosition();
        }
    }

    public void clickFlipCard() {
        if (currentDeck != null && !currentDeck.isEmpty()) {
            updateCardKnowledgeLevel();
            updateSliderPosition();
            flipIndex = (flipIndex + 1) % (numberOfFlipsAuthorizedForCurrentCard + 1);
            updateDisplayArea();
            if (flipIndex == numberOfFlipsAuthorizedForCurrentCard) {
                chooseDeckPlay.activateSlider();
            } else {
                chooseDeckPlay.deactivateSlider();
            }
        }
    }

    public void updateCardKnowledgeLevel() {
        if (!currentDeck.isEmpty()) {
            Card card = currentDeck.getCardList().get(cardIndex);
            card.setKnowledgeLevel(chooseDeckPlay.getSelectedKnowledgeLvl());
            databaseCard.updateCard(card);
            database.updateDeck(currentDeck);
        }
    }
     private void updateSliderPosition() {
        Card card = currentDeck.getCardList().get(cardIndex);
        chooseDeckPlay.setSliderLvl(card.getKnowledgeLevel());
     }

    private void setDeckList() {
        decks = database.getAllDecks();
        chooseDeckPlay.deckList.getItems().clear();
        for (Deck deck : decks) {
            chooseDeckPlay.deckList.getItems().add(deck);
        }
        chooseDeckPlay.deckList.setOnMouseClicked(event -> {
            chooseDeckPlay.deactivateSlider();
            currentDeck = chooseDeckPlay.deckList.getSelectionModel().getSelectedItem();
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
            chooseDeckPlay.displayTextQA.setText("No deck selected");
        } else {
            Card card = currentDeck.getCardList().get(cardIndex);
            updateSliderPosition();
            if (CardGapFill.isCardGapFilType(card)) {
                // We transform the card into its extended type cardGapFill if necessary
                // (to know if the card is of the type cardGapFill, we check whether its question contains the "gap" marker "_")
                card = new CardGapFill(card.getQuestion(),card.getAnswer());
            }
            numberOfFlipsAuthorizedForCurrentCard = card.getNumberOfFlips();
            if (questionIsDisplayed()) {
                chooseDeckPlay.cardBackground.setStyle(BACKGROUND_QUESTION);
                chooseDeckPlay.displayTextQA.setText(card.getQuestion());
            } else  {
                chooseDeckPlay.cardBackground.setStyle(BACKGROUND_ANSWER);
                chooseDeckPlay.displayTextQA.setText(card.getNthFlippedAnswer(flipIndex));
            }
        }
    }

    public interface Listener {
        void clickHome();
    }
}

