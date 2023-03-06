package ulb.info307.g6.models;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class Deck implements Serializable {
    @Id
    private NitriteId id;
    private String name;
    public Deck() {

    }
    public Deck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NitriteId getId() {return id;}

}
