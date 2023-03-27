package ulb.info307.g6.models;
import java.util.ArrayList;
import java.util.Random;

public class CardProbabilities implements Probabilities {
    public double[] cardProbabilities = {};
    public ArrayList<Integer> returnedCardsIndex = new ArrayList<>();
    public ArrayList<Integer> cardsIndexCount = new ArrayList<>();
    public int cardsToSkip = 0;
    @Override
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

    @Override
    public double[] getCardsProbabilities()
    {
        return this.cardProbabilities;
    }

    @Override
    public double getCardProbability(int cardID)
    {
        double cardProbability = 0.0;
        if (cardID >= 0 && cardID < this.cardProbabilities.length - 1)
        {
            cardProbability = this.cardProbabilities[cardID];
        }
        return cardProbability;
    }
    @Override
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

    @Override
    public void normalizeProbabilities()
    {
        double total_sum = 0;
        for (double i : this.cardProbabilities)
        {
            total_sum += i;
        }

        for (int i = 0; i < this.cardProbabilities.length; i++)
        {
            this.cardProbabilities[i] = this.cardProbabilities[i]/total_sum;
        }
    }
    @Override
    public double getNewProbabilityValue(int knowledge) {
        double newWeight = 0;
        double totalCards = this.cardProbabilities.length;
        switch (knowledge) {
            case 0:
                newWeight = 1.9; // Very bad
            case 1:
                newWeight = 1.6;
            case 2:
                newWeight = 1.4;
            case 3:
                newWeight = 1.2;
            case 4:
                newWeight = 1; // Very good
        }
        return newWeight;
    }

    @Override
    public void updateCardProbability(int cardID, int knowledge)
    {
        double newCardProbability = getNewProbabilityValue(knowledge);
        this.cardProbabilities[cardID] = this.cardProbabilities[cardID]*newCardProbability;
        normalizeProbabilities();
    }

    @Override
    public void setCardProbabilities(double[] probabilities)
    {
        this.cardProbabilities = probabilities;
    }
    @Override
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
        normalizeProbabilities();
    }


    public void setCardsToSkip(int quantity)
    {
        this.cardsToSkip = quantity;
    }

    @Override
    public int getRandomCardId()
    {
        return 0;
    }

    @Override
    public void showAllProba()
    { // utiliser dans l'event du bouton pour voir si les valeurs changent.
        for (double x : this.cardProbabilities)
        {
            System.out.println(x);
        }
    }



}
