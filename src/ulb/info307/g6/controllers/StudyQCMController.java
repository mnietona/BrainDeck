package ulb.info307.g6.controllers;

import javafx.stage.Stage;
import ulb.info307.g6.models.*;
import ulb.info307.g6.views.StudyQCM;
import java.time.Instant;

public class StudyQCMController extends ControllerWithDeckList implements StudyQCM.StudyQCMListener {
    private final StudyQCM studyQCMView;
    private final int[] lastIndex = new int[3];
    private int flipIndex = 0;
    private int numberOfFlipsAuthorizedForCurrentCard = 1;
    private int cardIndex = 0;
    private DeckProbabilities currentDeck = null;
    private Instant cardSelectionTimeStart;

    public StudyQCMController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Study.fxml", "Study your decks");
        studyQCMView = (StudyQCM) view;
        studyQCMView.setSliderLabels();
        studyQCMView.activateSlider(false);
        studyQCMView.activateActionButtons(false);
    }

    public void updateCardKnowledgeLevel() {
        if (!currentDeck.isEmpty()) {
            Card card = currentDeck.getCardByIndex(cardIndex);
            int knowledgeLvl = studyQCMView.getSelectedKnowledgeLvl();
            card.setKnowledgeLevel(knowledgeLvl); // We update the knowledge level of the card
            currentDeck.updateProbability(card, knowledgeLvl);
            deckDatabase.updateDeck(currentDeck);
        }
    }

    private void updateSliderPosition() {
        double levelDefault = 2.0;
        studyQCMView.setSliderLvl(levelDefault);
    }

    private void getNextRandomCard() {
        updateTimeSpent();
        int nextCardIndex = 0;
        if (currentDeck.getSize() == 2 || currentDeck.getSize() == 3) {
            nextCardIndex = (cardIndex + 1) % currentDeck.getSize();
        } else if (currentDeck.getSize() > 3) {
            nextCardIndex = currentDeck.getRandomCardIndexExcluding(lastIndex);
        }
        cardIndex = nextCardIndex;
        lastIndex[2] = lastIndex[1];
        lastIndex[1] = lastIndex[0];
        lastIndex[0] = nextCardIndex;
        cardSelectionTimeStart = Instant.now();
    }

    private boolean questionIsDisplayed() {
        // We have not yet flipped the card, so the question is displayed
        return flipIndex == 0;
    }

    public void updateDisplayArea() {
        if (currentDeck != null) {
            Card card = currentDeck.getCardByIndex(cardIndex);
            updateSliderPosition();
            if (CardGapFill.isCardGapFilType(card)) {
                // We transform the card into its extended type cardGapFill if necessary
                // (to know if the card is of the type cardGapFill, we check whether its question contains the "gap" marker "_")
                card = new CardGapFill(card.getQuestion(),card.getAnswer());
            }
            numberOfFlipsAuthorizedForCurrentCard = card.getMaxNumberOfFlips();
            studyQCMView.flipCard(questionIsDisplayed(), card.getQuestion(), card.getNthFlippedAnswer(flipIndex));
        }
    }

    @Override
    public void clickHome() {
        if (currentDeck != null) {
            updateCardKnowledgeLevel();
            updateTimeSpent();
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
            studyQCMView.activateSlider(false);
            updateSliderPosition();
        }
        currentDeck.printProbability();
    }

    @Override
    public void clickFlipCard() {
        if (currentDeck != null && !currentDeck.isEmpty()) {
            updateSliderPosition();
            updateCardKnowledgeLevel();
            // Increment flip index and limit it to the authorized number of flips
            flipIndex = (flipIndex + 1) % (numberOfFlipsAuthorizedForCurrentCard + 1);
            updateDisplayArea();
            studyQCMView.activateSlider(flipIndex == numberOfFlipsAuthorizedForCurrentCard);
        }
    }
    @Override
    public void deckSelected() {
        studyQCMView.activateSlider(false);
        currentDeck = new DeckProbabilities(studyQCMView.getSelectedDeck());
        studyQCMView.activateActionButtons(!currentDeck.isEmpty());
        cardIndex = 0;
        flipIndex = 0;

        if (!currentDeck.isEmpty()) {
            cardSelectionTimeStart = Instant.now(); // start the timer
            currentDeck.initDeckProbabilities();
            deckDatabase.updateDeck(currentDeck);
            getNextRandomCard();
            updateDisplayArea();
        } else {
            studyQCMView.showEmptyDeck();
        }
    }

    @Override
    public void clickWebViewUpLeft() {

    }

    @Override
    public void clickWebViewUpRight() {

    }

    @Override
    public void clickWebViewDownLeft() {

    }

    @Override
    public void clickWebViewDownRight() {

    }

    private void updateTimeSpent() {
        if (currentDeck != null && !currentDeck.isEmpty()) {
            Instant cardSelectionTimeEnd = Instant.now();
            currentDeck.getCardByIndex(cardIndex).addTimeSpent(cardSelectionTimeStart, cardSelectionTimeEnd);
            currentDeck.getCardByIndex(cardIndex).increaseNumberOfAppearances();
            deckDatabase.updateDeck(currentDeck);
        }
    }
}
