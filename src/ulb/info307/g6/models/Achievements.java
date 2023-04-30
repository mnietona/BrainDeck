package ulb.info307.g6.models;

public class Achievements {
    private final String description;
    private final int dayStreak;

    public Achievements(String description) {
        this.description = description;
        this.dayStreak = 0;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }
}

