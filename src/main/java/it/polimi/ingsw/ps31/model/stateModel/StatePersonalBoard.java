package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 *
 * Stato che rappresenta la Personal Board del giocatore
 *
 * @see StateType
 * @see MVUpdateState
 * @see it.polimi.ingsw.ps31.model.player.PersonalBoard
 */
public class StatePersonalBoard extends StateType {
    private final PlayerId playerId;
    private final List<StateCardBox> stateCardBoxes;

    public StatePersonalBoard(PlayerId playerId, List<StateCardBox> stateCardBoxes) {
        this.playerId = playerId;
        this.stateCardBoxes = stateCardBoxes;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<StateCardBox> getStateCardBoxes() {
        return stateCardBoxes;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
