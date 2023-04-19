package ulb.info307.g6.models.database;

import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import ulb.info307.g6.models.Deck;
import java.util.List;

public class DeckDaoNitriteImplementation {
    ObjectRepository<Deck> con = DatabaseConnection.getConnection().getRepository(Deck.class);
    public boolean addDeck(Deck deck) {
        try {
            con.insert(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDeck(Deck deck) {
        try {
            con.remove(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDeck(Deck deck) {
        try {
            con.update(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Deck> getAllDecks() {
        try {
            return con.find().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNumberOfDecks() {
        try {
            return con.find().toList().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Deck getDeckById(long id) {
        try {
            return con.find(ObjectFilters.eq("id", id)).firstOrDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
