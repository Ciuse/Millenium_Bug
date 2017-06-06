package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.server.message.StateVisitor;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerAction extends StateInfo {
    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
