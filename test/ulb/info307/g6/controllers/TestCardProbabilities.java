package ulb.info307.g6.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.CardProbabilities;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCardProbabilities {

    static CardProbabilities cardProbabilities;
    static int numberOfCards;
    @BeforeAll
    static void initCardProbabilities()
    {
        numberOfCards = 5;
        cardProbabilities = new CardProbabilities();
        cardProbabilities.initCardProbabilities(numberOfCards);
    }

    @Test
    void getCardProbability() {
        assertEquals(cardProbabilities.getCardProbability(0), cardProbabilities.cardProbabilities[0]);
        assertEquals(cardProbabilities.getCardProbability(numberOfCards/2), cardProbabilities.cardProbabilities[numberOfCards/2]);
        assertEquals(cardProbabilities.getCardProbability(numberOfCards-1), cardProbabilities.cardProbabilities[numberOfCards-1]);
    }

}