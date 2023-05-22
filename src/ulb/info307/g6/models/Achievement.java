package ulb.info307.g6.models;

public class Achievement {
    public interface NumberProvider {
        double getNumber();
    }
    private String name;
    private NumberProvider numberProvider;
    private double targetNumber;
    public Achievement(String name, double targetNumber, NumberProvider numberProvider) {
        this.name = name;
        this.targetNumber = targetNumber;
        this.numberProvider = numberProvider;
    }

    public double getProgress() {
        return numberProvider.getNumber() / targetNumber;
    }

    public boolean isAchieved() {
        return getProgress() >= 1.0;
    }

    public String getName() {
        return name;
    }
}
