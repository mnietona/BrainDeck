package ulb.info307.g6.models;

import org.dizitart.no2.objects.Id;

import java.util.Date; // YYYY-MM-DD

public class Statistics {
    @Id
    private final long id = 1;
    //private final int dayStreak;
    private Date lastDay;  // Day stored in database
    // TODO: avoir une comparaison de date fonctionnelle et stockable dans la db

    public Statistics() {
        // Constructeur par défaut
    }

    public void updateLastDay(){
        lastDay = new Date();
    }

    public Date getStringLastDay() {
        return lastDay;
    }
}
