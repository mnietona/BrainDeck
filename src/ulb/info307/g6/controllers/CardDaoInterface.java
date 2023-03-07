package ulb.info307.g6.controllers;

import ulb.info307.g6.models.Card;

import java.util.List;

public interface CardDaoInterface {
    public boolean addCard(Card card);
    public boolean deleteCard(Card card);
    public boolean updateCard(Card card);
    public List<Card> getAllCards();
    public Card getCardById(long id);
}
