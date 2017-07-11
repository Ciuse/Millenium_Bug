package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 12/06/2017.
 *
 * Generico messaggio che parte dalla view e diretto al controller il quale contiene l'id della view di cui è partito
 * e implementa il pattern visitor per poter sapere quale metodo invocare sul controller in base al tipo specifico del
 * messaggio
 *
 * @see it.polimi.ingsw.ps31.controller.Controller
 */
public abstract class VCVisitable extends GenericMessage{
    private PlayerId viewId;

    public abstract void accept(VCVisitor vcVisitor);

    public VCVisitable(PlayerId viewId) {
        this.viewId = viewId;
    }

    public PlayerId getViewId() {
        return viewId;
    }

    public final ConcreteEnvelope wrap()
    {
        return new ConcreteEnvelope(this);
    }
}
