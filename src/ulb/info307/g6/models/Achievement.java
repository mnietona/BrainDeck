package ulb.info307.g6.models;

public class Achievement {
    public interface NumberProvider {
        double getNumber();
    }
    private final String name;
    private final NumberProvider numberProvider;
    private final double targetNumber;
    private Achievement nextTierAchievement;
    private Achievement previousTierAchievement;
    public Achievement(String name, double targetNumber, NumberProvider numberProvider) {
        this.name = "     " + name;
        this.targetNumber = targetNumber;
        this.numberProvider = numberProvider;
        this.nextTierAchievement = null;
    }

    public int getAchievementTier() {
        int achievementTier = 0;
        if (this.isAchieved()) {
            achievementTier++;
        }
        Achievement previousAchievement = previousTierAchievement;
        while (previousAchievement != null) {
            if (previousAchievement.isAchieved()) {
                achievementTier++;
            }
            previousAchievement = previousAchievement.getPreviousTierAchievement();
        }
        return achievementTier;
    }

    public void setNextTierAchievement(Achievement a) {
        this.nextTierAchievement = a;
    }

    public void setPreviousTierAchievement(Achievement a) {
        this.previousTierAchievement = a;
        a.setNextTierAchievement(this);
    }

    public Achievement getPreviousTierAchievement() {
        return this.previousTierAchievement;
    }

    public Achievement getNextTierAchievement() {
        return this.nextTierAchievement;
    }

    public boolean hasNextAchievement() {
        return this.nextTierAchievement != null;
    }

    public boolean hasPreviousAchievement() {
        return this.previousTierAchievement != null;
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
