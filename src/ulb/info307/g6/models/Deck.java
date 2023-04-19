package ulb.info307.g6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dizitart.no2.objects.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements Serializable {
    @Id
    private final long id;
    private String name;
    private List<Card> cardList;

    public Deck() {
        this.id = new Random().nextLong();
    }

    public Deck(Deck deck) {
        this.id = deck.id;
        this.name = deck.name;
        this.cardList = new ArrayList<>(deck.cardList);
    }
    public Deck(String name) {
        this.id = new Random().nextLong();
        this.cardList = new ArrayList<>();
        this.name = name;
    }

    public Deck(String name, List<Card> cardList) {
        this.id = new Random().nextLong();
        this.name = name;
        this.cardList = cardList;
    }

    public List<Card> getCardList() {  // TODO don't modify list from outside of Deck, encapsulate !
        return cardList;  // TODO alternative : Collections.unmodifiableList(cardList) to prevent modification and leave access
    }

    public Card getCardByIndex(int index) {
        try {
            return cardList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @JsonIgnore
    public int getSize() {
        return getCardList().size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }

    public void removeCard(Card card) {
        this.cardList.remove(card);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return getName();
    }
}
