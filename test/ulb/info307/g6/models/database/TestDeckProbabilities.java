package ulb.info307.g6.models.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.DeckProbabilities;

public class TestDeckProbabilities {
    private DeckProbabilities deckProbabilities;

    @BeforeEach
    public void setUp() {
        Deck deck = new Deck("TestDeck");
        deck.addCard(new Card("Question1", "Answer1"));
        deck.addCard(new Card("Question2", "Answer2"));
        deck.addCard(new Card("Question3", "Answer3"));
        deck.addCard(new Card("Question4", "Answer4"));
        deck.addCard(new Card("Question5", "Answer5"));
        deck.addCard(new Card("Question6", "Answer6"));
        deck.addCard(new Card("Question7", "Answer7"));
        deck.addCard(new Card("Question8", "Answer8"));
        deck.addCard(new Card("Question9", "Answer9"));
        deck.addCard(new Card("Question10", "Answer10"));
        deckProbabilities = new DeckProbabilities(deck);
    }

    @Test
    public void testInitDeckProbabilities() {
        deckProbabilities.initDeckProbabilities();
        Card card = deckProbabilities.getCardByIndex(0);
        int numberOfCards = deckProbabilities.getSize();
        assertEquals(1.0/numberOfCards, card.getProbability(), 1e-9);
    }

    @Test
    public void testNormalizeProbability() {
        deckProbabilities.initDeckProbabilities();
        Card card = deckProbabilities.getCardByIndex(0);
        card.setProbability(0.5);
        deckProbabilities.normalizeProbability();
        assertEquals(1.0, deckProbabilities.getSumProbability(), 1e-9);
    }

    @Test
    public void testIsNotNormalized() {
        deckProbabilities.initDeckProbabilities();
        Card card = deckProbabilities.getCardByIndex(0);
        card.setProbability(0.5);
        assertTrue(deckProbabilities.isNotNormalized());
    }

    @Test
    public void testUpdateProbability() {
        deckProbabilities.initDeckProbabilities();
        Card card = deckProbabilities.getCardByIndex(0);
        deckProbabilities.updateProbability(card , 0);
        assertEquals(1.0, deckProbabilities.getSumProbability(), 1e-9);
    }

    @Test
    public void testResetProbability() {
        deckProbabilities.initDeckProbabilities();
        Card card = deckProbabilities.getCardByIndex(0);
        card.setProbability(0.5);
        deckProbabilities.resetProbability();
        assertEquals(1/3.0, card.getProbability(), 1e-9);
    }

    @Test
    public void testEmptyDeckSumProbability() {
        Deck emptyDeck = new Deck("EmptyDeck");
        DeckProbabilities emptyDeckProbabilities = new DeckProbabilities(emptyDeck);
        assertEquals(0, emptyDeckProbabilities.getSumProbability(), 1e-9);
    }

}
