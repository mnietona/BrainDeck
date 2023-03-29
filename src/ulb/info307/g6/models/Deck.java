package ulb.info307.g6.models;

import org.dizitart.no2.objects.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements Serializable {
    @Id
    private long id;
    private String name;
    private List<Card> cardList;

    public Deck() {

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

    public List<Card> getCardList() {
        return cardList;
    }
    public int getSize() {return getCardList().size();}
    public boolean isEmpty() {return getSize() == 0;}

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
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

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {return id;}

    public String toString() {return getName();}

    public boolean isDeck(String name){
        if(name == this.name) return true;
        else return false;
    }

}
