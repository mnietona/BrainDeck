package ulb.info307.g6.models;

public interface Probabilities {
    void initCardProbabilities(int numberOfCards);

    double[] getCardsProbabilities();

    double getCardProbability(int cardID);

    void resetProbabilities();

    void normalizeProbabilities();

    double getNewProbabilityValue(int knowledge);

    void updateCardProbability(int cardID, int knowledge);

    void setCardProbabilities(double[] probabilities);

    void addNewCard();

    int getRandomCardId();

    void showAllProba();
}
