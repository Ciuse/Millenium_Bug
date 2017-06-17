package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerAction extends StateType {
    private final PlayerId playerId;
    private final List<String> stringPlayerAction;

    public StatePlayerAction(PlayerId playerId,List<String> stringPlayerAction) {
        this.playerId = playerId;
        this.stringPlayerAction = stringPlayerAction;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<String> getStringPlayerAction() {
        return stringPlayerAction;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
