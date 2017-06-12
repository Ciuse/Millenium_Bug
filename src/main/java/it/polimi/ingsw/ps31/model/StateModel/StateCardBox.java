package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateCardBox extends StateInfo {
    private final String name;
    private final int cardId;
    private final CardColor cardColor;
    private PlayerId playerId=null;
    private int value=-1;
    private int extraValue =-1;
    private int towerFloor=-1;

    public StateCardBox(PlayerId playerId,String name, int cardId, CardColor cardColor, int value, int extraValue) {
        this.playerId=playerId;
        this.name = name;
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.value = value;
        this.extraValue = extraValue;
    }

    public StateCardBox(String name,int cardId,CardColor cardColor,int towerFloor){
        this.name = name;
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.towerFloor = towerFloor;
    }


    public String getName() {
        return name;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public int getCardId() {
        return cardId;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public int getValue() {
        return value;
    }

    public int getExtraValue() {
        return extraValue;
    }

    public int getTowerFloor() {
        return towerFloor;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
