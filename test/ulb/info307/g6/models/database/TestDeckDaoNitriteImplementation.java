package ulb.info307.g6.models.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.models.Card;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestDeckDaoNitriteImplementation {
    static Deck test_deck;
    static DeckDaoNitriteImplementation ddni;

    @BeforeAll
    static void initTestDeck() {
        List<Card> test_cards = new ArrayList<>();
        test_cards.add(new Card("1+1", "2"));
        test_cards.add(new Card("2+2", "4"));
        test_deck = new Deck("test_deck", test_cards);
        ddni = new DeckDaoNitriteImplementation();
    }

    @Test
    void addDeck() {
        assertTrue(ddni.addDeck(test_deck));
        assertFalse(ddni.addDeck(test_deck));
        assertEquals(ddni.getDeckById(test_deck.getId()).getName(), test_deck.getName());
        assertTrue(ddni.deleteDeck(test_deck));
    }

    @Test
    void deleteDeck() {
        assertTrue(ddni.addDeck(test_deck));
        assertTrue(ddni.deleteDeck(test_deck));
        assertFalse(ddni.deleteDeck(null));
    }

    @Test
    void updateDeck() {
        assertTrue(ddni.addDeck(test_deck));
        assertEquals(ddni.getDeckById(test_deck.getId()).getCardList().size(), test_deck.getCardList().size());
        Card test_update_card = new Card("3+2", "5");
        test_deck.addCard(test_update_card);
        assertTrue(ddni.updateDeck(test_deck));
        assertEquals(ddni.getDeckById(test_deck.getId()).getCardList().size(), test_deck.getCardList().size());
        test_deck.removeCard(test_update_card);
        assertTrue(ddni.deleteDeck(test_deck));
    }

    @Test
    void getAllDecks() {
        Deck test_deck_2 = new Deck("test_deck_2", new ArrayList<>());
        test_deck_2.addCard(new Card("8+8", "16"));
        assertTrue(ddni.addDeck(test_deck));
        assertTrue(ddni.addDeck(test_deck_2));
        int amountOfDecks = ddni.getAllDecks().size();
        assertTrue(amountOfDecks >= 2);
        assertTrue(ddni.deleteDeck(test_deck_2));
        assertEquals(ddni.getAllDecks().size(), amountOfDecks-1);
        assertTrue(ddni.deleteDeck(test_deck));
    }

    @Test
    void getDeckById() {
        assertTrue(ddni.addDeck(test_deck));
        assertTrue(ddni.deleteDeck(test_deck));
        assertNull(ddni.getDeckById(test_deck.getId()));
    }
}