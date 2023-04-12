package ulb.info307.g6.models;
import ulb.info307.g6.models.database.CardDaoNitriteImplementation;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;

public class CardProbabilities{

    public void initCardProbabilities(Deck deck, CardDaoNitriteImplementation databaseCard, DeckDaoNitriteImplementation database) {
        int numberOfCards = deck.getSize();
        double probability = (double) 1 / numberOfCards;
        double sum = 0;

        for (Card card : deck.getCardList())
            sum += card.getProbability();

        if (Math.abs(sum - 1) > 1e-9) { // If the sum of the probabilities is not 1, we normalize them sum != 1
            for (Card card : deck.getCardList()) {
                card.setProbability(probability);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
    }

    public void normalizeProbability(Deck deck, CardDaoNitriteImplementation databaseCard, DeckDaoNitriteImplementation database) {
        double sum = 0;
        for (Card card : deck.getCardList()) {
            sum += card.getProbability();
        }
        if (!isAlmostOne(sum)) {
            for (Card card : deck.getCardList()) {
                double normalizedProbability = card.getProbability() / sum;
                card.setProbability(normalizedProbability);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
    }

    public boolean isAlmostOne(double value) {
        return Math.abs(value-1) < 1e-9;
    }

    public double getWeight(int knowledge) {
        return switch (knowledge) {
            case 0 -> 1.5; // Very bad
            case 1 -> 1.25;
            case 2 -> 1;
            case 3 -> 0.75;
            case 4 -> 0.5; // Very good
            default -> 1;
        };
    }

    // TODO : pour bouton Reset poids
    public void resetProbability(Deck deck,CardDaoNitriteImplementation databaseCard, DeckDaoNitriteImplementation database) {
        int numberOfCards = deck.getSize();
        double probability = (double) 1 / numberOfCards;
        for (Card card : deck.getCardList()) {
            card.setProbability(probability);
            databaseCard.updateCard(card);
        }
        database.updateDeck(deck);
    }

}
