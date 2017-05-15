package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Card.Card;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class PhysicalCardBox implements PhysicalSpaceBehavior
{

    private Card card;

    /* Constructor */
    public PhysicalCardBox()
    {
        this.card = null;
    }

    /* Getters & Setters */
    public void setCard(Card card)
    {
        this.card = card;
    }

    public Card getCard()
    {
        return this.card;
    }
}
