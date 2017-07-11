package it.polimi.ingsw.ps31.messages;

import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

/**
 * Created by Francesco on 29/06/2017.
 *
 * Classe che permette di incapsulare un messaggio astratto genericMessage in un oggetto concreto
 * per consentire la serializzazione del messaggio prima dell'invio (gson non serializza direttamente classi astratte).
 *
 * Può contenere un MVVisitable, un VCVisitable, un ViewMessage e un ConnectionMessage. In genere ne contiene solo
 * uno alla volta. Fornisce metodi di get per recuperare i messaggi contenuti.
 */
public class ConcreteEnvelope {
    private MVVisitable mvVisitable = null;
    private VCVisitable vcVisitable = null;
    private ViewMessage viewMessage = null;
    private ConnectionMessage connectionMessage = null;

    /* Constructors */
    public ConcreteEnvelope(MVVisitable mvVisitable)
    {
        this.mvVisitable = mvVisitable;
    }

    public ConcreteEnvelope(VCVisitable vcVisitable)
    {
        this.vcVisitable = vcVisitable;
    }

    public ConcreteEnvelope(ViewMessage viewMessage)
    {
        this.viewMessage = viewMessage;
    }

    public ConcreteEnvelope (ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
    }

    /* Getters */
    public MVVisitable getMvVisitable()
    {
        return mvVisitable;
    }

    public VCVisitable getVcVisitable()
    {
        return vcVisitable;
    }

    public ViewMessage getViewMessage()
    {
        return viewMessage;
    }

    public ConnectionMessage getConnectionMessage()
    {
        return connectionMessage;
    }
}
