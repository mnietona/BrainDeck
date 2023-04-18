package ulb.info307.g6.models.database;

import ulb.info307.g6.models.Card;
import java.util.List;

/**
 * Since cards are stored within a deck, this interface might be redundant.
 */
public interface CardDaoInterface {  // TODO Removed if unused or use interface instead of class in controllers
    boolean addCard(Card card);
    boolean deleteCard(Card card);
    boolean updateCard(Card card);
    List<Card> getAllCards();
    Card getCardById(long id);
}
