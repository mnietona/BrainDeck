package ulb.info307.g6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dizitart.no2.objects.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *The Deck class represents a collection of cards. It contains fields for the deck's ID, name, and a list of cards.
 * It provides methods for adding and removing cards,
 * retrieving cards by index, and calculating the knowledge level and time spent on the deck.
 */

public class Deck implements Serializable {
    @Id
    private final long id;
    private String name;
    @JsonProperty("cardList")
    protected List<Card> cardList;

    public Deck() {
        this.id = new Random().nextLong();
        this.cardList = new ArrayList<>();
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

    public int getKnowledgeLevel() {
        int knowledgeLevel = 0;
        if (cardList.size() == 0) {
            return 0;
        }
        for (Card card : cardList) {
            knowledgeLevel += card.getKnowledgeLevel();
        }
        knowledgeLevel = knowledgeLevel / cardList.size();
        return knowledgeLevel;
    }

    @JsonIgnore
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

    @JsonIgnore
    public long getTimeSpent() {
        long timeSpent = 0;
        for (Card card : cardList) {
            timeSpent += card.getTimeSpent();
        }
        return timeSpent;
    }
}
