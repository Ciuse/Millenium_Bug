package it.polimi.ingsw.ps31.model.board;


import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;

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
    public CardColor getTowerColor() {
        return this.towerColor;
    }

    public ActionSpace getActionSpace() {
        return actionSpace;
    }

    public Tower getTower() {
        return this.tower;
    }

    public int getTowerFloor() {
        return towerFloor;
    }

    public void setCard(DevelopmentCard card) {

        super.setCard(card);
        tower.getModel().notifyViews(new MVUpdateState("Aggiornato stato del tower card box", getStateTowerCardBox()));

    }

    @Override
    public DevelopmentCard takeCard() {
        DevelopmentCard takenCard=super.takeCard();
        tower.getModel().notifyViews(new MVUpdateState("Aggiornato stato del tower card box",getStateTowerCardBox()));

        return takenCard;
    }

    public StateCardBox getStateTowerCardBox() {
        if (super.getCard() != null) {
            StateCardBox stateTowerCardBox = new StateCardBox(super.getCard().getName(), super.getCard().getCardId(), towerColor, this.towerFloor);
            return stateTowerCardBox;
        }
        StateCardBox stateCardBox = new StateCardBox(null, 0, this.towerColor,this.towerFloor);
        return stateCardBox;
    }



}
