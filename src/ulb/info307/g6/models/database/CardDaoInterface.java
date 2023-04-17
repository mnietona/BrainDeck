package ulb.info307.g6.models.database;

import ulb.info307.g6.models.Card;
import java.util.List;

/**
 * Since cards are stored within a deck, this interface might be redundant.
 */
public interface CardDaoInterface {
    public boolean addCard(Card card);
    public boolean deleteCard(Card card);
    public boolean updateCard(Card card);
    public List<Card> getAllCards();
    public Card getCardById(long id);
}
