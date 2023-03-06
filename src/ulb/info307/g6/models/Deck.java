package ulb.info307.g6.models;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class Deck implements Serializable {
    @Id
    private NitriteId deck_id;
    private String deck_name;
    public Deck() {

    }
    public Deck(String deck_name) {
        this.deck_name = deck_name;
    }

    public String getDeck_name() {
        return deck_name;
    }

    public void setDeck_name(String deck_name) {
        this.deck_name = deck_name;
    }

    public NitriteId getDeck_id() {return deck_id;}

}
