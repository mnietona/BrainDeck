package ulb.info307.g6.models.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardGapFill;

import static org.junit.jupiter.api.Assertions.*;

public class TestCardGapFill {
    private CardGapFill cardGapFill;

    @BeforeEach
    public void setUp() {
        cardGapFill = new CardGapFill("I have _ apples and _ oranges", "3, 5");
    }

    @Test
    public void testIsCardGapFillType() {
        Card regularCard = new Card("Question", "Answer");
        assertFalse(CardGapFill.isCardGapFilType(regularCard));
        assertTrue(CardGapFill.isCardGapFilType(cardGapFill));
    }

    @Test
    public void testIsValid() {
        assertTrue(cardGapFill.isValid());
        CardGapFill invalidCardGapFill = new CardGapFill("I have _ apples", "3, 5");
        assertFalse(invalidCardGapFill.isValid());
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestion = "I have _ apples and _ oranges ";
        assertEquals(expectedQuestion, cardGapFill.getQuestion());
    }

    @Test
    public void testGetMaxNumberOfFlips() {
        assertEquals(2, cardGapFill.getMaxNumberOfFlips());
    }

    @Test
    public void testGetNthFlippedAnswer() {
        String expectedFirstFlip = "I have 3 apples and _ oranges ";
        assertEquals(expectedFirstFlip, cardGapFill.getNthFlippedAnswer(1));

        String expectedSecondFlip = "I have 3 apples and 5 oranges ";
        assertEquals(expectedSecondFlip, cardGapFill.getNthFlippedAnswer(2));

    }

    @Test
    public void testGetNthFlippedAnswerWithIncorrectNumberOfFlips() {
        String expectedIncorrectNumberOfFlipsMessage = "INCORRECT NUMBER OF FLIPS";
        assertEquals(expectedIncorrectNumberOfFlipsMessage, cardGapFill.getNthFlippedAnswer(3));
    }

    @Test
    public void testGetQuestionWithMultipleSpaces() {
        CardGapFill cardWithMultipleSpaces = new CardGapFill("I have _  apples and _  oranges", "3, 5");
        String expectedQuestion = "I have _  apples and _  oranges ";
        assertEquals(expectedQuestion, cardWithMultipleSpaces.getQuestion());
    }


}