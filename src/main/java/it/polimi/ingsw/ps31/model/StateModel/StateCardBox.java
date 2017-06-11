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
    private PlayerId playerId;
    private final String name;
    private final int cardId;
    private final CardColor cardColor;
    private int value=-1;
    private PointResource extraValue;
    private int towerFloor=-1;



    public StateCardBox(String name, int cardId, CardColor cardColor) {
        this.name = name;
        this.cardId = cardId;
        this.cardColor = cardColor;
    }

    public StateCardBox(String name, int cardId, CardColor cardColor, int value, PointResource extraValue) {
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


    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
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

    public PointResource getExtraValue() {
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
