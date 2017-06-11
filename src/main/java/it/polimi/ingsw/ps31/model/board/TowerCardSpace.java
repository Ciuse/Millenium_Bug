package it.polimi.ingsw.ps31.model.board;


import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by Francesco on 12/05/2017.
 */
public class TowerCardSpace extends PhysicalCardBox {
    private final ActionSpace actionSpace;
    private final CardColor towerColor;
    private final Tower tower;
    private final int towerFloor;

    /* Constructor */
    public TowerCardSpace(CardColor towerColor, ActionSpace actionSpace, Tower tower, int towerFloor) {
        super();
        this.towerColor = towerColor;
        this.actionSpace = actionSpace;
        this.tower = tower;
        this.towerFloor = towerFloor;
    }

    /* Getters & Setters */
    public CardColor getTowerColor()
    {
        return this.towerColor;
    }
    public ActionSpace getActionSpace()
    {
        return actionSpace;
    }
    public Tower getTower()
    {
        return this.tower;
    }

    public int getTowerFloor() {
        return towerFloor;
    }

    public void setCard(DevelopmentCard card)
    {

        if ( card == null )
        {
            //TODO: gestire (eccezione?)
        } else
        if (card.getCardColor() != this.towerColor)
        {
            //TODO: gestire (eccezione?)
        } else
            super.setCard(card);
    }

    public StateCardBox getStateTowerCardBox(){
        StateCardBox stateTowerCardBox = new StateCardBox(super.getCard().getName(),super.getCard().getCardId(),towerColor,this.towerFloor);
        return stateTowerCardBox;
    }

}
