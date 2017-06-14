package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateViewFamilyMember {
    private final PlayerId playerId;
    private DiceColor diceColor;
    private int diceValue;
    private int additionalValue;
    private int actionSpaceId;

    public StateViewFamilyMember(PlayerId playerId) {
        this.playerId = playerId;
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

    public void updateState(StateFamilyMember stateFamilyMember){
        if(stateFamilyMember.getDiceColor()!=null){
            this.diceColor=stateFamilyMember.getDiceColor();
            this.additionalValue = stateFamilyMember.getAdditionalValue();
            this.actionSpaceId = stateFamilyMember.getActionSpaceId();
            this.diceValue = stateFamilyMember.getDiceValue();
        }
    }
}
