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
    void getCardProbability()
    {
        assertEquals(cardProbabilities.getCardProbability(0), cardProbabilities.cardProbabilities[0]);
        assertEquals(cardProbabilities.getCardProbability(numberOfCards/2), cardProbabilities.cardProbabilities[numberOfCards/2]);
        assertEquals(cardProbabilities.getCardProbability(numberOfCards-1), cardProbabilities.cardProbabilities[numberOfCards-1]);
    }

    @Test
    void totalProbabilityEqualsOne()
    {
        ArrayList<Double> tempProbabilities = new ArrayList<>();
        cardProbabilities.updateCardProbability(0, 2);
        cardProbabilities.updateCardProbability(1, 0);
        cardProbabilities.updateCardProbability(2, 4);
        cardProbabilities.updateCardProbability(3, 1);
        cardProbabilities.updateCardProbability(4, 3);

        double total_probability = 0.0;
        for (double i: cardProbabilities.cardProbabilities) { tempProbabilities.add(i); }
        for (double probability : tempProbabilities) { total_probability += probability; }
        assertEquals(total_probability, 1);
    }

    @Test
    void resetProbabilities() {
        cardProbabilities.updateCardProbability(0, 0);
        cardProbabilities.resetProbabilities();
        for (int i = 0; i < numberOfCards; i++)
        {
            assertEquals(cardProbabilities.getCardProbability(i), (double) 1/numberOfCards);
        }
    }
}