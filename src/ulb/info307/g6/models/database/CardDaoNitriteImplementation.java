package ulb.info307.g6.models.database;

import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import ulb.info307.g6.models.Card;
import java.util.List;

public class CardDaoNitriteImplementation implements CardDaoInterface {
    ObjectRepository<Card> con = DatabaseConnection.getConnection().getRepository(Card.class);

    @Override
    public boolean addCard(Card card) {
        try {
            con.insert(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCard(Card card) {
        try {
            con.remove(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCard(Card card) {
        try {
            con.update(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Card> getAllCards() {
        try {
            return con.find().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Card getCardById(long id) {
        try {
            return con.find(ObjectFilters.eq("id", id)).firstOrDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
