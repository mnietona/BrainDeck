package ulb.info307.g6;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import ulb.info307.g6.model.Deck;

public class Main {

    public static void gen_deck() {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("db.db")
                .openOrCreate();
        ObjectRepository<Deck> collection = db.getRepository(Deck.class);
        Deck p = new Deck("mdrlol");
        collection.insert(p);
        Deck p1 = new Deck("lolimodr");
        collection.insert(p1);
    }

    public static void main(String[] args) {
        //gen_deck();
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("db.db")
                .openOrCreate();

        ObjectRepository<Deck> collection = db.getRepository(Deck.class);

       for (Deck p : collection.find()) {
           System.out.println(p.getDeck_name());
       }
    }
}
