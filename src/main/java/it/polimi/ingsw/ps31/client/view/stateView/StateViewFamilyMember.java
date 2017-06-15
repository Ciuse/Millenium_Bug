package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateViewFamilyMember {
    private final PlayerId playerId;
    private final PlayerColor playerColor;
    private DiceColor diceColor;
    private int diceValue;
    private int additionalValue;
    private int actionSpaceId;

    public StateViewFamilyMember(PlayerId playerId, PlayerColor playerColor) {
        this.playerId = playerId;
        this.playerColor = playerColor;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public int getAdditionalValue() {
        return additionalValue;
    }

    public int getActionSpaceId() {
        return actionSpaceId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void updateState(StateFamilyMember stateFamilyMember){
        if(stateFamilyMember.getDiceColor()!=null){
            this.diceColor=stateFamilyMember.getDiceColor();
            this.additionalValue = stateFamilyMember.getAdditionalValue();
            this.actionSpaceId = stateFamilyMember.getActionSpaceId();
            this.diceValue = stateFamilyMember.getDiceValue();
        }
    }
}
