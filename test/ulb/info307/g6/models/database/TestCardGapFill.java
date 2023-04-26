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


}