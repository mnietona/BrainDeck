package ulb.info307.g6.controllers;

import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Card;

import static org.junit.jupiter.api.Assertions.*;

class TestCardDaoNitriteImplementation {

    static Card test_card;
    static CardDaoNitriteImplementation cdni;

    @BeforeAll
    static void initTestCard() {
        test_card = new Card("1+1", "2");
        cdni = new CardDaoNitriteImplementation();
    }

    @Test
    void addCard() {
        assertTrue(cdni.addCard(test_card));
        assertFalse(cdni.addCard(test_card)); // no duplicated id field
        //technically the lines below aren't part of the unit test, but we need to get the info back somehow
        //that our card has been added to the db with the correct values.
        //and remove the card afterwards
        assertEquals(cdni.getCardById(test_card.getId()).getQuestion(), test_card.getQuestion());
        assertEquals(cdni.getCardById(test_card.getId()).getAnswer(), test_card.getAnswer());
        assertTrue(cdni.deleteCard(test_card));
    }

    @Test
    void deleteCard() {
        assertTrue(cdni.addCard(test_card)); //not part of the unit test, but we need to add a card before deleting it
        assertTrue(cdni.deleteCard(test_card));
    }

    @Test
    void getAllCards() {
        Card test_card2 = new Card("2+2", "4");
        assertTrue(cdni.addCard(test_card));
        assertTrue(cdni.addCard(test_card2));
        int amountOfCards = cdni.getAllCards().size();
        assertTrue(amountOfCards >= 2);
        assertTrue(cdni.deleteCard(test_card2));
        assertEquals(amountOfCards-1, cdni.getAllCards().size());
        assertTrue(cdni.deleteCard(test_card));
    }

    @Test
    void getCardById() {
        assertTrue(cdni.addCard(test_card));
        assertTrue(cdni.deleteCard(test_card));
        assertNull(cdni.getCardById(test_card.getId()));
    }

    @Test
    void updateCard() {
        assertTrue(cdni.addCard(test_card));
        test_card.setAnswer("yes");
        assertTrue(cdni.updateCard(test_card));
        assertEquals(cdni.getCardById(test_card.getId()).getAnswer(), "yes");
        test_card.setAnswer("2");
        assertTrue(cdni.deleteCard(test_card));
    }
}