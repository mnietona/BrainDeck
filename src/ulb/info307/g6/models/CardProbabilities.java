package ulb.info307.g6.models;

public class CardProbabilities {

    public static void initCardProbabilities(Deck deck) {
        if (!isNormalized(deck)) { // If the sum of the probabilities is not 1, we normalize them sum != 1
            int numberOfCards = deck.getSize();
            double probability = (double) 1 / numberOfCards;
            for (Card card : deck.getCardList()) {
                card.setProbability(probability);
            }
        }
    }

    public static void normalizeProbability(Deck deck) {
        double sum = getSumProbability(deck);
        if (isNotOne(sum)) {
            for (Card card : deck.getCardList()) {
                card.setProbability(card.getProbability() / sum);
            }
        }
    }

    public static void updateProbability(Deck deck, Card card, int knowledgeLvl) {
        double newProba = card.getProbability() * getWeight(knowledgeLvl);
        card.setProbability(newProba);
        normalizeProbability(deck);
    }

    private static double getSumProbability(Deck deck) {
        double sum = 0;
        for (Card card : deck.getCardList()) {
            sum += card.getProbability();
        }
        return sum;
    }

    private static boolean isNotOne(double value) {
        return Math.abs(value-1) > 1e-9;
    }

    public static boolean isNormalized(Deck deck) {
        return !isNotOne(getSumProbability(deck));
    }

    public static double getWeight(int knowledge) {
        return switch (knowledge) {
            case 0 -> 1.5; // Very bad
            case 1 -> 1.25;
            case 2 -> 1;
            case 3 -> 0.75;
            case 4 -> 0.5; // Very good
            default -> 1;
        };
    }

    public static void resetProbability(Deck deck) {
        int numberOfCards = deck.getSize();
        double probability = (double) 1 / numberOfCards;
        for (Card card : deck.getCardList()) {
            card.setProbability(probability);
        }
    }

    public static void printProbability(Deck deck) {
        System.out.println("Printing probability of deck " + deck.getName());
        for (Card card : deck.getCardList()) {
            System.out.println("Probability of card " + card.toString() + " : " + card.getProbability());
        }
    }

    public static int getRandomCardIndex(Deck deck) {
        double random = Math.random(); // Génère un nombre aléatoire entre 0 et 1
        double cumulativeProbability = 0.0;

        int i = 0;
        for (Card card : deck.getCardList()) {
            cumulativeProbability += card.getProbability();

            if (random <= cumulativeProbability) {
                return i;
            }
            i++;
        }
        return i;
    }

    public static int getRandomCardIndexExcluding(Deck deck, int [] indexToExclude) {
        int i = getRandomCardIndex(deck);
        while (contains(indexToExclude, i)) {
            i = getRandomCardIndex(deck);
        }
        return i;
    }

    private static boolean contains(int [] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }
}
