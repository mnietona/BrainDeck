package ulb.info307.g6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dizitart.no2.objects.Id;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Deck implements Serializable {
    @Id
    private final long id;
    private String name;
    protected List<Card> cardList;

    private long timeSpent; // Ajoutez ce champ pour stocker le temps passé

    public Deck() {
        this.id = new Random().nextLong();
        this.cardList = new ArrayList<>();
    }

    public Deck(Deck deck) {
        this.id = deck.id;
        this.name = deck.name;
        this.cardList = new ArrayList<>(deck.cardList);
        this.timeSpent = deck.timeSpent;
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

    public Iterator<Card> getCardIterator() {
        return cardList.iterator();
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
        return cardList.size();
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

    // Ajoutez les getters et setters pour le temps passé
    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long newTimeSpent) {
        this.timeSpent = newTimeSpent;
    }


}
