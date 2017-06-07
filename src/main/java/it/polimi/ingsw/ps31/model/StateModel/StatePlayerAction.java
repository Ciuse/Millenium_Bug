package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.actions.Action;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerAction extends StateInfo {
    List<Action> actionList;

    public StatePlayerAction(List<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
