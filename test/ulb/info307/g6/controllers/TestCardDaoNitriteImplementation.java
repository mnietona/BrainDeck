package ulb.info307.g6.controllers;

import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Card;

import static org.junit.jupiter.api.Assertions.*;

class TestCardDaoNitriteImplementation {

    static CardDaoNitriteImplementation cdni = new CardDaoNitriteImplementation();

    @Test
    void addCard() {
        Card test_card = new Card("1+1", "2");
        assert(cdni.addCard(test_card));
    }

    @Test
    void deleteCard() {
    }

    @Test
    void getAllCards() {
    }

    @Test
    void getCardById() {
    }
}