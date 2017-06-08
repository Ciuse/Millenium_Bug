package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerAction extends StateInfo {
    private PlayerId playerId;
    List<Action> actionList;

    public StatePlayerAction(PlayerId playerId,List<Action> actionList) {
        this.playerId = playerId;
        this.actionList = actionList;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
