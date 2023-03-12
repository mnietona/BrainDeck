package ulb.info307.g6;

import ulb.info307.g6.controllers.CardDaoNitriteImplementation;
import ulb.info307.g6.controllers.DeckDaoNitriteImplementation;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;

public class MainDB {
    static DeckDaoNitriteImplementation ddni = new DeckDaoNitriteImplementation();
    static CardDaoNitriteImplementation cdni = new CardDaoNitriteImplementation();

    static void add_cards() {
        cdni.addCard(new Card("1+1", "2"));
        cdni.addCard(new Card("2+2", "4"));
    }

    static void add_deck() {
        Deck deck = new Deck("toasty deck");
        ddni.addDeck(deck);
        for (Card c : cdni.getAllCards()) {
            deck.addCard(c);
        }
        ddni.updateDeck(deck);
    }

    public static void main(String[] args) {
        //add_cards();
        //add_deck();
        for (Deck d : ddni.getAllDecks()) {
            System.out.println(d.getName());
            for (Card c : d.getCardList()) {
                System.out.println(c.getQuestion());
            }
        }
    }
}
