package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.database.CardDaoNitriteImplementation;
import ulb.info307.g6.views.Study;
import java.util.Random;

public class StudyController extends ControllerWithDeckList implements Study.StudyListener {
    private Study studyView;
    private CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    private int[] lastIndex = new int[3];
    private int flipIndex = 0;
    private int numberOfFlipsAuthorizedForCurrentCard = 1;
    private int cardIndex = 0;
    private Deck currentDeck = null;

    public StudyController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Study.fxml", "Study your decks");
        studyView = (Study) view;

        studyView.setSliderLabels();
        studyView.activateSlider(false);
        studyView.activateActionButtons(false);
    }

    public void updateCardKnowledgeLevel() {
        if (!currentDeck.isEmpty()) {
            Card card = currentDeck.getCardList().get(cardIndex);
            card.setKnowledgeLevel(studyView.getSelectedKnowledgeLvl());
            databaseCard.updateCard(card);
            database.updateDeck(currentDeck);
        }
    }

    private void updateSliderPosition() {
        Card card = currentDeck.getCardList().get(cardIndex);
        studyView.setSliderLvl(card.getKnowledgeLevel());
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
        if (currentDeck != null) {
            Card card = currentDeck.getCardList().get(cardIndex);
            updateSliderPosition();
            if (CardGapFill.isCardGapFilType(card)) {
                // We transform the card into its extended type cardGapFill if necessary
                // (to know if the card is of the type cardGapFill, we check whether its question contains the "gap" marker "_")
                card = new CardGapFill(card.getQuestion(),card.getAnswer());
            }
            numberOfFlipsAuthorizedForCurrentCard = card.getNumberOfFlips();
            studyView.flipCard(questionIsDisplayed(), card.getQuestion(), card.getNthFlippedAnswer(flipIndex));
        }
    }

    @Override
    public void clickHome() {
        if (currentDeck != null) {
            updateCardKnowledgeLevel();
        }
        new WelcomeController(stage);
    }

    @Override
    public void clickNextCard() {
        if (!currentDeck.isEmpty()) {
            updateCardKnowledgeLevel();
            getNextRandomCard();
            flipIndex = 0;
            updateDisplayArea();
            studyView.activateSlider(false);
            updateSliderPosition();
        }
    }

    @Override
    public void clickFlipCard() {
        if (currentDeck != null && !currentDeck.isEmpty()) {
            updateCardKnowledgeLevel();
            updateSliderPosition();
            flipIndex = (flipIndex + 1) % (numberOfFlipsAuthorizedForCurrentCard + 1);
            updateDisplayArea();
            studyView.activateSlider(flipIndex == numberOfFlipsAuthorizedForCurrentCard);
        }
    }

    @Override
    public void deckSelected() {
        studyView.activateSlider(false);
        currentDeck = studyView.getSelectedDeck();
        studyView.activateActionButtons(!currentDeck.isEmpty());
        cardIndex = 0;
        flipIndex = 0;

        if (!currentDeck.isEmpty()) {
            getNextRandomCard();
            updateDisplayArea();
        } else {
            studyView.showEmptyDeck("The deck " + currentDeck.getName() + " is empty");
        }
    }
}
