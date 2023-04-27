package ulb.info307.g6.models;

public class Achievement {
    private final String description;

    public Achievement(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

