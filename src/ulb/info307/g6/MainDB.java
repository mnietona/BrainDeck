package ulb.info307.g6;

import ulb.info307.g6.controllers.CardDaoNitriteImplementation;
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

public class MainDB {

    public static void main(String[] args) {
        DeckDaoNitriteImplementation ddni = new DeckDaoNitriteImplementation();
        CardDaoNitriteImplementation cdni = new CardDaoNitriteImplementation();
        for (Card c : cdni.getAllCards()) {
            System.out.println(c.getQuestion());
        }
        for (Deck d : ddni.getAllDecks()) {
            System.out.println(d.getName());
        }
    }
}
