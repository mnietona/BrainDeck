package ulb.info307.g6.models;
import java.util.ArrayList;
import java.util.Random;

public class CardProbabilities {
    public double[] cardProbabilities = {};
    public ArrayList<Integer> returnedCardsIndex = new ArrayList<>();
    public ArrayList<Integer> cardsIndexCount = new ArrayList<>();
    public int cardsToSkip = 0;


    static DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation(); // Initialize the DAO for the database
    static CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();

    public void initCardProbabilities(Deck deck) {
        if (!isNormalized(deck)) { // If the sum of the probabilities is not 1, we normalize them sum != 1
            int numberOfCards = deck.getSize();
            double probability = (double) 1 / numberOfCards;
            for (Card card : deck.getCardList()) {
                card.setProbability(probability);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
    }



    public double[] getCardsProbabilities()
    {
        return this.cardProbabilities;
    }


    public double getCardProbability(int cardID)
    {
        double cardProbability = 0.0;
        if (cardID >= 0 && cardID <= this.cardProbabilities.length - 1)
        {
            cardProbability = this.cardProbabilities[cardID];
        }
        return cardProbability;
    }

    public void resetProbability(Deck deck) {
        int numberOfCards = deck.getSize();
        double probability = (double) 1 / numberOfCards;
        for (Card card : deck.getCardList()) {
            card.setProbability(probability);
            databaseCard.updateCard(card);
        }
        database.updateDeck(deck);
    }


    private double getSumProbability(Deck deck) {
        double sum = 0;
        for (Card card : deck.getCardList()) {
            sum += card.getProbability();
        }
        return sum;
    }

    public void normalizeProbability(Deck deck) {
        double sum = getSumProbability(deck);
        if (isNotOne(sum)) {
            for (Card card : deck.getCardList()) {
                card.setProbability(card.getProbability() / sum);
                databaseCard.updateCard(card);
            }
            database.updateDeck(deck);
        }
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


    public void updateProbability(Deck deck, Card card, int knowledgeLvl) {
        double newProba = card.getProbability() * getWeight(knowledgeLvl);
        card.setProbability(newProba);
        databaseCard.updateCard(card);
        database.updateDeck(deck);
        normalizeProbability(deck);
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
        newProbabilities[cardsCount] = initialCardProbability;
        this.cardProbabilities = newProbabilities;
        normalizeProbabilities();
    }

    public void setCardsToSkip(int quantity)
    {
        this.cardsToSkip = quantity;
    }

    public boolean cardNotUsed(int z)
    {
        for(int i: this.returnedCardsIndex)
        {
            if (i == z) { return false; }
        }

        this.returnedCardsIndex.add(z);
        return true;
    }

    public void decrementOtherCardsCount()
    {
        if (this.cardsIndexCount.size() > 0)
        {
            for(int i = 0; i < this.cardsIndexCount.size(); i++)
            {
                int newIndexCount = this.cardsIndexCount.get(i)-1;
                this.cardsIndexCount.set(i,newIndexCount);
                if(this.cardsIndexCount.get(i) == 0)
                {
                    this.cardsIndexCount.remove(i);
                    this.returnedCardsIndex.remove(i);
                }
            }
        }

    }

    public double sumNotUsedProbabilities()
    {
        ArrayList<Double> tempProbabilities = new ArrayList<>();
        double total_probability = 0.0;

        for (double i: this.cardProbabilities)
        {
            tempProbabilities.add(i);
        }
        for (int j : this.returnedCardsIndex) {
            tempProbabilities.remove(tempProbabilities.get(j));
        }

        for (double probability : tempProbabilities) {
            total_probability += probability;
        }

        return total_probability;
    }


    public int getRandomCardIndex()
    {
        if (this.cardProbabilities.length > 0)
        {
            double cumProbability = 0.0;
            Random randomVal = new Random();
            double randomDouble = randomVal.nextDouble();
            for (int i = 0; i < this.cardProbabilities.length; i++)
            {
                cumProbability += this.cardProbabilities[i];
                if (randomDouble <= cumProbability && cardNotUsed(i))
                {
                    decrementOtherCardsCount();
                    this.cardsIndexCount.add(this.cardsToSkip);
                    return i;
                }
            }
            cumProbability = 0.0;
            for (int j = 0; j < this.cardProbabilities.length; j++)
            {
                if(!this.returnedCardsIndex.contains(j))
                {
                    cumProbability += this.cardProbabilities[j];
                    if (randomDouble*sumNotUsedProbabilities() <= cumProbability && cardNotUsed(j))
                    {
                        decrementOtherCardsCount();
                        this.cardsIndexCount.add(this.cardsToSkip);
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    public void showAllProba()
    { // utiliser dans l'event du bouton pour voir si les valeurs changent.
        for (double x : this.cardProbabilities)
        {
            System.out.println(x);
        }
    }



}
