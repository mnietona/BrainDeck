package ulb.info307.g6.models.database;

import ulb.info307.g6.models.Deck;
import java.util.List;

public interface DeckDaoInterface {
    boolean addDeck(Deck deck);
    boolean deleteDeck(Deck deck);
    boolean updateDeck(Deck deck);
    List<Deck> getAllDecks();
    Deck getDeckById(long id);
}
