package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.stateModel.StateType;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public class MVUpdateState extends MVVisitable {
    private String stringToPrint;
    private StateType stateType;

    public MVUpdateState(String stringToPrint, StateType stateType) {
        this.stringToPrint=stringToPrint;
        this.stateType = stateType;
        super.setNotifyAll(true);
        super.setNotifySinglePlayer(null);
    }

    public StateType getStateType() {
        return stateType;
    }

    public String getStringToPrint() {
        return stringToPrint;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
