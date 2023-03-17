package ulb.info307.g6.models;
import java.util.Random;

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

    public double getNewProbabilityValue(int knowledge) {
        double newWeight = 0;
        double totalCards = this.cardProbabilities.length;
        switch (knowledge) {
            case 0:
                newWeight = 1.9; // Very bad ,ex: 5cartes donc 1.9/5 = 0.38 (la probabilité augmentée...)
            case 1:
                newWeight = 1.6;
            case 2:
                newWeight = 1.4;
            case 3:
                newWeight = 1.2;
            case 4:
                newWeight = 1; // Very good ,ex: 5cartes donc 1/5 = 0.2 (la probabilité de base...)
        }
        return newWeight/totalCards;
    }

    public void updateCardProbability(int cardID,int knowledge)
    {
        double newCardProbability = getNewProbabilityValue(knowledge);
        this.cardProbabilities[cardID] = newCardProbability;
        normalizeProbabilities(cardID, newCardProbability);
    }

    public void setCardProbabilities(double[] probabilities)
    {
        this.cardProbabilities = probabilities;
    }
    public void addNewCard()
    {
        int cardsCount = this.cardProbabilities.length;
        double initialCardProbability = (double) 1/(cardsCount+1);
        double[] newProbabilities = new double[cardsCount+1];
        for (int i = 0; i < cardsCount; i++)
        {
            newProbabilities[i] = this.cardProbabilities[i];
        }
        newProbabilities[cardsCount+1] = initialCardProbability;
        this.cardProbabilities = newProbabilities;
        normalizeProbabilities(this.cardProbabilities.length, initialCardProbability);
    }


    public void normalizeProbabilities(int cardID, double newCardProbability)
    {

    }

    public int getRandomCardId()
    {
        return 0;
    }

    public void showAllProba()
    { // utiliser dans l'event du bouton pour voir si les valeurs changent.
        for (double x : this.cardProbabilities)
        {
            System.out.println(x);
        }
    }



}
