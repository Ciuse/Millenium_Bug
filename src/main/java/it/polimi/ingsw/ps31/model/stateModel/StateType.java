package it.polimi.ingsw.ps31.model.stateModel;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;

/**
 * Created by giulia on 06/06/2017.
 *
 * Classe generica di uno Stato del Model.
 * Implementa un patter Visitor in questo modo ogni stato quando è accettato all'interno di un messaggio sa come visitarsi
 *
 * Gli stati servono per rappresentare il model in maniera semplice utilizzando SOLO oggetti primitivi come interi, stringhe
 * enumeratori o altri stati del Model che a loro volta saranno composti da sole variabili elementari.
 * In questo modo è possibile inviare in maniera semplice informazioni alla View sullo stato del model utilizzando solo oggetti
 * pensati per il Networking, e rendendo il lavoro di parsing in stringhe per le socket del networking molto più semplice e leggero.

 * @see MVStateInfoVisitor
 * @see MVUpdateState
 * @see StateVisitable
 *
 */
public abstract class StateType implements StateVisitable {

    @Override
    public abstract void acceptState(StateVisitor stateVisitor);
}
