package it.polimi.ingsw.ps31.model.board;


import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by Francesco on 12/05/2017.
 */
public class TowerCardSpace extends PhysicalCardBox {
    private final ActionSpace actionSpace;
    private final CardColor color;
    private final Tower tower;

    /* Constructor */
    public TowerCardSpace(CardColor color, ActionSpace actionSpace, Tower tower) {
        super();
        this.color = color;
        this.actionSpace = actionSpace;
        this.tower = tower;
    }

    /* Getters & Setters */
    public CardColor getColor()
    {
        return this.color;
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
            super.setCard(card);
    }



}
