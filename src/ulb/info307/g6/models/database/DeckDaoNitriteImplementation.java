package ulb.info307.g6.models.database;

import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import ulb.info307.g6.models.Deck;

import java.util.List;

public class DeckDaoNitriteImplementation implements DeckDaoInterface {
    static ObjectRepository<Deck> con = DatabaseConnection.getConnection().getRepository(Deck.class);

    @Override
    public boolean addDeck(Deck deck) {
        try {
            con.insert(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDeck(Deck deck) {
        try {
            con.remove(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDeck(Deck deck) {
        try {
            con.update(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Deck> getAllDecks() {
        try {
            return con.find().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Deck getDeckById(long id) {
        try {
            return con.find(ObjectFilters.eq("id", id)).firstOrDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
