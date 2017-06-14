package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.StateModel.StateInfo;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public class MVStateInfo implements MVVisitable {
    private StateInfo stateInfo;

    public MVStateInfo(StateInfo stateInfo) {
        this.stateInfo = stateInfo;
    }

    public StateInfo getStateInfo() {
        return stateInfo;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
