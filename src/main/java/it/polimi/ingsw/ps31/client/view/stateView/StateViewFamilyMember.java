package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.FamilyMember;

import java.util.List;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateViewFamilyMember {
    private PlayerId playerId;
    private DiceColor diceColor;
    private int diceValue;
    private int additionalValue;
    private ActionSpace actionSpace;

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

    public ActionSpace getActionSpace() {
        return actionSpace;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void updateStateFamily(StateFamilyMember stateFamilyMember){
        if(stateFamilyMember.getDiceColor()!=null){
            this.diceColor=stateFamilyMember.getDiceColor();
            this.additionalValue = stateFamilyMember.getAdditionalValue();
            this.actionSpace = stateFamilyMember.getActionSpace();
            this.diceValue = stateFamilyMember.getDiceValue();
        }
    }
}
