package it.polimi.ingsw.ps31.model.stateModel;

/**
 * Created by Giuseppe on 06/06/2017.
 *
 * Interfaccia del pattern Visitor (degli stati), implementata dagli oggetti visitabili, i quali sanno come accettarsi
 * @see StateType
 */
public interface StateVisitable {
    void acceptState(StateVisitor stateVisitor);
}
