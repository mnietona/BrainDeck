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
        return false; // this will not be reached, but is necessary for compilation
    }

    public boolean deleteDeck(Deck deck) {
        try {
            con.remove(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // this will not be reached, but is necessary for compilation
    }

    public boolean updateDeck(Deck deck) {
        try {
            con.update(deck);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // this will not be reached, but is necessary for compilation
    }

    public List<Deck> getAllDecks() {
        try {
            return con.find().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // this will not be reached, but is necessary for compilation
    }

    public int getNumberOfDecks() {
        try {
            return con.find().toList().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // this will not be reached, but is necessary for compilation
    }

    public int getTotalNumberOfCards() {
        try {
            int n = 0;
            for (Deck deck : con.find().toList()) {
                n += deck.getSize();
            }
            return n;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // this will not be reached, but is necessary for compilation
    }

    public Deck getDeckById(long id) {
        try {
            return con.find(ObjectFilters.eq("id", id)).firstOrDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // this will not be reached, but is necessary for compilation
    }

    public long getTotalTimeSpent() {
        try {
            long totalTimeSpend = 0;
            for (Deck deck : con.find().toList()) {
                totalTimeSpend += deck.getTimeSpent();
            }
            return totalTimeSpend;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // this will not be reached, but is necessary for compilation
    }
}
