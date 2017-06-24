package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class PhysicalCardBox extends ModelChoices {
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

    public DevelopmentCard takeCard()
    {
        DevelopmentCard takenCard = this.card;
        this.card = null;
        return takenCard;
    }

    /* Class Methods */
    public DevelopmentCard getCard()
    {
        return this.card;
    }


}
