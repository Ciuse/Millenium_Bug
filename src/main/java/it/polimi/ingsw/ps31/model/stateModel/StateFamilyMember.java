package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 06/06/2017.
 * Stato che rappresenta il generico Family Member di un giocatore
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState
 * @see it.polimi.ingsw.ps31.model.player.FamilyMember
 *
 */
public class StateFamilyMember extends StateType {
    private final PlayerId playerId;
    private final PlayerColor playerColor;
    private final int diceValue;
    private final int additionalValue;
    private final DiceColor diceColor;
    private final int actionSpaceId;

    public StateFamilyMember(PlayerId playerId, PlayerColor playerColor, int diceValue, int additionalValue, DiceColor diceColor, int actionSpaceId) {
        this.playerId = playerId;
        this.playerColor = playerColor;
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

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
