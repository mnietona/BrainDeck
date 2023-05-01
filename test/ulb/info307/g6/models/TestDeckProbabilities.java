package ulb.info307.g6.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeckProbabilities {
    private DeckProbabilities deckProbabilities;

    private int numberOfCards;

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
        numberOfCards = deckProbabilities.getSize();
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
        assertEquals(1.0/numberOfCards, card.getProbability(), 1e-9);
    }

    @Test
    public void testEmptyDeckSumProbability() {
        Deck emptyDeck = new Deck("EmptyDeck");
        DeckProbabilities emptyDeckProbabilities = new DeckProbabilities(emptyDeck);
        assertEquals(0, emptyDeckProbabilities.getSumProbability(), 1e-9);
    }

    @Test
    public void testGetRandomCardIndex() {
        deckProbabilities.initDeckProbabilities();
        int randomIndex = deckProbabilities.getRandomCardIndex();
        assertTrue(randomIndex >= 0 && randomIndex < deckProbabilities.getSize());
    }

    @Test
    public void testGetRandomCardIndexExcluding() {
        deckProbabilities.initDeckProbabilities();
        int[] exclude = {0, 2, 4, 6, 8};
        int randomIndex = deckProbabilities.getRandomCardIndexExcluding(exclude);

        boolean isExcluded = false;
        for (int excludedIndex : exclude) {
            if (randomIndex == excludedIndex) {
                isExcluded = true;
                break;
            }
        }
        assertFalse(isExcluded);
    }

    @Test
    public void testGetRandomCardIndexWithNormalizedProbabilities() {
        deckProbabilities.initDeckProbabilities();
        deckProbabilities.resetProbability();
        int[] count = new int[deckProbabilities.getSize()];
        int numberOfSamples = 10000;

        for (int i = 0; i < numberOfSamples; i++) {
            int index = deckProbabilities.getRandomCardIndex();
            count[index]++;
        }

        for (int i = 0; i < count.length; i++) {
            double probability = (double) count[i] / numberOfSamples;
            assertEquals(deckProbabilities.getCardByIndex(i).getProbability(), probability, 0.05);
        }
    }

    @Test
    public void testGetRandomCardIndexWithDifferentProbabilities() {
        deckProbabilities.initDeckProbabilities();
        deckProbabilities.updateProbability(deckProbabilities.getCardByIndex(0), 0); // Poids 1.5
        deckProbabilities.updateProbability(deckProbabilities.getCardByIndex(1), 4); // Poids 0.5
        int[] count = new int[deckProbabilities.getSize()];
        int numberOfSamples = 10000;

        for (int i = 0; i < numberOfSamples; i++) {
            int index = deckProbabilities.getRandomCardIndex();
            count[index]++;
        }

        for (int i = 0; i < count.length; i++) {
            double probability = (double) count[i] / numberOfSamples;
            assertEquals(deckProbabilities.getCardByIndex(i).getProbability(), probability, 0.05);
        }
    }

}
