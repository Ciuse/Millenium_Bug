package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Card.Card;
import it.polimi.ingsw.ps31.Card.DevelopmentCard;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class PhysicalCardBox implements PhysicalSpaceBehavior
{

    protected DevelopmentCard card;

    /* Constructor */
    public PhysicalCardBox()
    {
        this.card = null;
    }

    /* Getters & Setters */
    public void setCard(DevelopmentCard card)
    {
        this.card = card;
    }

    public DevelopmentCard getCard()
    {
        return this.card;
    }
}
