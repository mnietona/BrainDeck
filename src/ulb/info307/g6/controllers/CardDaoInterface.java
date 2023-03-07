package ulb.info307.g6.controllers;

import ulb.info307.g6.models.Card;

import java.util.List;

//TODO: this might be in fact, useless as all cards are stored within a deck...
public interface CardDaoInterface {
    public boolean addCard(Card card);
    public boolean deleteCard(Card card);
    public boolean updateCard(Card card);
    public List<Card> getAllCards();
    public Card getCardById(long id);
}
