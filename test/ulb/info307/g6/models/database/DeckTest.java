package ulb.info307.g6.models.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

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

}
