package ulb.info307.g6.models;
import ulb.info307.g6.models.database.CardDaoNitriteImplementation;
import ulb.info307.g6.models.database.DeckDaoNitriteImplementation;

public class CardProbabilities{

    public void initCardProbabilities(Deck deck, CardDaoNitriteImplementation databaseCard, DeckDaoNitriteImplementation database) {
        int numberOfCards = deck.getSize();
        double probability = (double) 1 / numberOfCards;
        double sum = 0;

        for (Card card : deck.getCardList())
            sum += card.getKnowledgeLevel();

        if (Math.abs(sum - 1) > 1e-9) { // If the sum of the probabilities is not 1, we normalize them sum != 1
            for (Card card : deck.getCardList()) {
                card.setKnowledgeLevel(probability);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
    }

    public void updateProbability(Deck deck,CardDaoNitriteImplementation databaseCard, DeckDaoNitriteImplementation database) {
        double sum = 0;
        for (Card card : deck.getCardList()) {
            sum += card.getKnowledgeLevel();
        }
        if (Math.abs(sum - 1) > 1e-9) {
            for (Card card : deck.getCardList()) {
                double normalizedProbability = card.getKnowledgeLevel() / sum;
                card.setKnowledgeLevel(normalizedProbability);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
    }

    public double getNewProbabilityValue(int knowledge) {
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
            card.setKnowledgeLevel(probability);
            databaseCard.updateCard(card);
        }
        database.updateDeck(deck);
    }

}
