package ulb.info307.g6.models.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    private List<Card> cardList;
    private final String deckName = "Test Deck";

    @BeforeEach
    void setUp() {
        cardList = new ArrayList<>();
        cardList.add(new Card("Question 1", "Answer 1"));
        cardList.add(new Card("Question 2", "Answer 2"));
        cardList.add(new Card("Question 3", "Answer 3"));

        deck = new Deck(deckName, cardList);
    }

    @Test
    void getKnowledgeLevel() {
        int expectedKnowledgeLevel = 0;
        for (Card card : cardList) {
            expectedKnowledgeLevel += card.getKnowledgeLevel();
        }
        expectedKnowledgeLevel /= cardList.size();

        assertEquals(expectedKnowledgeLevel, deck.getKnowledgeLevel());
    }

    @Test
    void getCardByIndex() {
        Card card = deck.getCardByIndex(1);
        assertEquals("Question 2", card.getQuestion());
        assertEquals("Answer 2", card.getAnswer());
    }

    @Test
    void getSize() {
        assertEquals(3, deck.getSize());
    }

    @Test
    void isEmpty() {
        assertFalse(deck.isEmpty());
        Deck emptyDeck = new Deck("Empty Deck");
        assertTrue(emptyDeck.isEmpty());
    }

    @Test
    void addCard() {
        Card newCard = new Card("Question 4", "Answer 4");
        deck.addCard(newCard);
        assertEquals(4, deck.getSize());
        assertEquals(newCard, deck.getCardByIndex(3));
    }

    @Test
    void removeCard() {
        Card cardToRemove = deck.getCardByIndex(1);
        deck.removeCard(cardToRemove);
        assertEquals(2, deck.getSize());
        assertNotEquals(cardToRemove, deck.getCardByIndex(1));
    }

    @Test
    void getName() {
        assertEquals(deckName, deck.getName());
    }

    @Test
    void getTimeSpent() {
        long expectedTimeSpent = 0;
        for (Card card : cardList) {
            expectedTimeSpent += card.getTimeSpent();
        }
        assertEquals(expectedTimeSpent, deck.getTimeSpent());
    }

    @Test
    void getCardIterator() {
        Iterator<Card> cardIterator = deck.getCardIterator();
        assertNotNull(cardIterator);
        List<Card> cardListFromIterator = new ArrayList<>();
        cardIterator.forEachRemaining(cardListFromIterator::add);
        assertEquals(cardList, cardListFromIterator);
    }

    @Test
    void getId() {
        Deck anotherDeck = new Deck("Another Deck");
        assertNotEquals(deck.getId(), anotherDeck.getId());
    }

    @Test
    void deckCopyConstructor() {
        Deck copiedDeck = new Deck(deck);
        assertEquals(deck.getId(), copiedDeck.getId());
        assertEquals(deck.getName(), copiedDeck.getName());
        assertEquals(deck.getSize(), copiedDeck.getSize());
        assertEquals(deck.getTimeSpent(), copiedDeck.getTimeSpent());
        assertEquals(deck.getKnowledgeLevel(), copiedDeck.getKnowledgeLevel());

        for (int i = 0; i < deck.getSize(); i++) {
            assertEquals(deck.getCardByIndex(i), copiedDeck.getCardByIndex(i));
        }
    }

}
