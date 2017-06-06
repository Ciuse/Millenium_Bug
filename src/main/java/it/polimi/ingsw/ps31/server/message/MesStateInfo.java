package it.polimi.ingsw.ps31.server.message;

import it.polimi.ingsw.ps31.model.StateModel.StateInfo;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public class MesStateInfo implements Visitable {
    StateInfo stateInfo;

    public MesStateInfo(StateInfo stateInfo) {
        this.stateInfo = stateInfo;
    }

    public StateInfo getStateInfo() {
        return stateInfo;
    }

    @Override
    public void accept(MesVisitor mesVisitor) {
        mesVisitor.visit(this);
    }
}
