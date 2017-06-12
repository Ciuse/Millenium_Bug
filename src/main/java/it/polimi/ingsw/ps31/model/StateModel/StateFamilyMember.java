package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateFamilyMember extends StateInfo {
    private final PlayerId playerId;
    private final int diceValue;
    private final int additionalValue;
    private final DiceColor diceColor;
    private final int actionSpaceId;

    public StateFamilyMember(PlayerId playerId, int diceValue, int additionalValue, DiceColor diceColor, int actionSpaceId) {
        this.playerId = playerId;
        this.diceValue = diceValue;
        this.additionalValue = additionalValue;
        this.diceColor = diceColor;
        this.actionSpaceId = actionSpaceId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public int getAdditionalValue() {
        return additionalValue;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }

    public int getActionSpaceId() {
        return actionSpaceId;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
