package it.polimi.ingsw.ps31.model.StateModel;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public interface StateVisitable {
    void acceptState(StateVisitor stateVisitor);
}
