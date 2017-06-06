package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class PhysicalCardBox implements PhysicalSpaceBehavior
{
    private StateCardBox stateCardBox;
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

    public StateCardBox GetStateCardBox(){
        if(this.card!=null) {
            StateCardBox stateCardBox = new StateCardBox(this.card.getName(), this.card.getCardId(), this.card.getCardColor());
            return stateCardBox;
        }
        return null;
    }

}
