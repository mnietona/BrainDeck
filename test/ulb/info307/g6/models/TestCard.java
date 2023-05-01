package ulb.info307.g6.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCard {
    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card("Question?", "Answer.");
    }

    @Test
    public void testGetQuestion() {
        assertEquals("Question?", card.getQuestion());
    }

    @Test
    public void testSetQuestion() {
        card.setQuestion("New question?");
        assertEquals("New question?", card.getQuestion());
    }

    @Test
    public void testGetAnswer() {
        assertEquals("Answer.", card.getAnswer());
    }

    @Test
    public void testGetNthFlippedAnswer() {
        assertEquals("Answer.", card.getNthFlippedAnswer(0));
    }

    @Test
    public void testSetAnswer() {
        card.setAnswer("New answer.");
        assertEquals("New answer.", card.getAnswer());
    }

    @Test
    public void testGetProbability() {
        assertEquals(1.0, card.getProbability());
    }

    @Test
    public void testSetProbability() {
        card.setProbability(2.0);
        assertEquals(2.0, card.getProbability());
    }

    @Test
    public void testSetKnowledgeLevel() {
        card.setKnowledgeLevel(3);
        assertEquals(3, card.getKnowledgeLevel());
    }

    @Test
    public void testIsValid() {
        assertTrue(card.isValid());

        Card invalidCard = new Card("", "");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testGetMaxNumberOfFlips() {
        assertEquals(1, card.getMaxNumberOfFlips());
    }

    @Test
    public void testAddTimeSpent() {
        Instant start = Instant.now();
        Instant end = start.plusSeconds(5);
        card.addTimeSpent(start, end);
        assertEquals(5, card.getTimeSpent());
    }

    @Test
    public void testIncreaseNumberOfAppearances() {
        card.increaseNumberOfAppearances();
        assertEquals(1, card.getNumberOfAppearances());
    }


}