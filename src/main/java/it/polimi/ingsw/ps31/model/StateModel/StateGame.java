package it.polimi.ingsw.ps31.model.StateModel;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateGame extends StateInfo {

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
