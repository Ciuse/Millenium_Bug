package it.polimi.ingsw.ps31.Board;


import it.polimi.ingsw.ps31.Card.DevelopmentCard;
import it.polimi.ingsw.ps31.Constants.CardColor;

/**
 * Created by Francesco on 12/05/2017.
 */
public class TowerCardSpace extends PhysicalCardBox {
    private final ActionSpace actionSpace;
    private final CardColor color;
    private final Tower tower;
    private DevelopmentCard card;

    /* Constructor */
    public TowerCardSpace(CardColor color, ActionSpace actionSpace, Tower tower) {
        this.color = color;
        this.actionSpace = actionSpace;
        this.card = null;
        this.tower = tower;
    }

    /* Getters & Setters */
    public CardColor getColor()
    {
        return this.color;
    }
    public DevelopmentCard getCard ()
    {
        return this.card;
    }
    public ActionSpace getActionSpace()
    {
        return actionSpace;
    }
    public Tower getTower()
    {
        return this.tower;
    }

    public void setCard(DevelopmentCard card)
    {

        if ( card == null )
        {
            //TODO: gestire (eccezione?)
        } else
        if (card.getCardColor() != this.color)
        {
            //TODO: gestire (eccezione?)
        } else
            this.card = card;
    }

    /* Class Methods */
    public DevelopmentCard takeCard()
    {
        DevelopmentCard takenCard = this.card;
        this.card = null;
        return takenCard;
    }

}
