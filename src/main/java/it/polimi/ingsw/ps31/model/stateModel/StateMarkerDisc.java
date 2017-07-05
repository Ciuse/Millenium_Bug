package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 12/06/2017.
 *
 * Stato che rappresenta lo stato di un marker disc
 *
 * @see StateType
 * @see MVUpdateState
 * @see it.polimi.ingsw.ps31.model.board.MarkerDisc
 */
public class StateMarkerDisc extends StateType {
    private final PlayerId playerId;
    private String stringResourceType = null;
    private final int value;

    public StateMarkerDisc(PlayerId playerId, String stringResourceType, int value) {
        this.playerId = playerId;
        this.stringResourceType = stringResourceType;
        this.value = value;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getStringResourceType() {
        return stringResourceType;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
