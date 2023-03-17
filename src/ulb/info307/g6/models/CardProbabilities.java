package ulb.info307.g6.models;

public class CardProbabilities
{
    public double[] cardProbabilities;

    public void initCardProbabilities(int numberOfCards)
    {
        double probability = (double) 1/numberOfCards;
        double[] initialProbabilities = new double[numberOfCards];

        for (int i = 0; i < initialProbabilities.length; i++)
        {
            initialProbabilities[i] = probability;
        }
        this.cardProbabilities = initialProbabilities;
    }

    public double[] getCardsProbabilities()
    {
        return this.cardProbabilities;
    }

    public double getCardProbability(int cardID)
    {
        double cardProbability = 0.0;
        if (cardID >= 0 && cardID < this.cardProbabilities.length - 1)
        {
            cardProbability = this.cardProbabilities[cardID];
        }
        return cardProbability;
    }
    public void resetProbabilities()
    {
        if (this.cardProbabilities.length > 0)
        {
            double initial = (double) 1/cardProbabilities.length;
            for (int i = 0; i < cardProbabilities.length; i++)
            {
                this.cardProbabilities[i] = initial;
            }
        }
    }
}
