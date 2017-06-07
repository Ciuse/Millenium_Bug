package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.StateModel.StateVisitor;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public interface StateVisitable {
    void acceptState(StateVisitor stateVisitor);
}
