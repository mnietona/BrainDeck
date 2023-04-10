package ulb.info307.g6.models.database;

import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

import java.util.List;

public interface DeckDaoInterface {
    public boolean addDeck(Deck deck);
    public boolean deleteDeck(Deck deck);
    public boolean updateDeck(Deck deck);
    public List<Deck> getAllDecks();
    public Deck getDeckById(long id);
}
