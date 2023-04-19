package ulb.info307.g6.models;

public class DeckProbabilities extends Deck {

    public DeckProbabilities(Deck deck) {
        super(deck);
    }

    public void initDeckProbabilities() {
        if (isNotNormalized()) {  // If the sum of the probabilities is not 1, we normalize them sum != 1
            int numberOfCards = getSize();
            double probability = (double) 1 / numberOfCards;
            for (Card card : getCardList()) {
                card.setProbability(probability);
            }
        }
    }

    public void normalizeProbability() {
        double sum = getSumProbability();
        if (isNotOne(sum)) {
            for (Card card : getCardList()) {
                card.setProbability(card.getProbability() / sum);
            }
        }
    }

    public void updateProbability(Card card, int knowledgeLvl) {
        double newProba = card.getProbability() * getWeight(knowledgeLvl);
        card.setProbability(newProba);
        normalizeProbability();
    }

    private double getSumProbability() {
        double sum = 0;
        for (Card card : getCardList()) {
            sum += card.getProbability();
        }
        return sum;
    }

    private boolean isNotOne(double value) {
        return Math.abs(value-1) > 1e-9;
    }

    public boolean isNotNormalized() {
        return isNotOne(getSumProbability());
    }

    public double getWeight(int knowledge) {
        return switch (knowledge) {
            case 0 -> 1.5; // Very bad
            case 1 -> 1.25;
            case 3 -> 0.75;
            case 4 -> 0.5; // Very good
            default -> 1;  // Case 2
        };
    }

    public void resetProbability() {
        int numberOfCards = getSize();
        double probability = (double) 1 / numberOfCards;
        for (Card card : getCardList()) {
            card.setProbability(probability);
        }
    }

    public void printProbability() {
        System.out.println("Printing probability of deck " + getName());
        for (Card card : getCardList()) {
            System.out.println("Probability of card " + card.toString() + " : " + card.getProbability());
        }
    }

    public int getRandomCardIndex() {
        double random = Math.random(); // Generates a random number between 0 and 1
        double cumulativeProbability = 0.0;

        int i = 0;
        for (Card card : getCardList()) {
            cumulativeProbability += card.getProbability();
            if (random <= cumulativeProbability) {
                return i;
            }
            i++;
        }
        return i;
    }

    public int getRandomCardIndexExcluding(int[] indexToExclude) {
        int i = getRandomCardIndex();
        while (contains(indexToExclude, i)) {
            i = getRandomCardIndex();
        }
        return i;
    }

    private boolean contains(int [] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }
}