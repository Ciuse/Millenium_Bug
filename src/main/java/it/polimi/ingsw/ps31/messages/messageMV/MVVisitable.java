package it.polimi.ingsw.ps31.messages.messageMV;


import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 01/06/2017.
 *
 * Classe astratta del pattern Visitor (dei messaggi),per definire la struttura dei messaggi e a chi sono
 * indirizzati e del sapere come accettarsi
 *
 */
public abstract class MVVisitable extends GenericMessage{
    private PlayerId notifySinglePlayer=null;
    private boolean notifyAll =false;

    public MVVisitable() {
    }

    public abstract void accept(MVVisitor mvVisitor);

    public void setNotifySinglePlayer(PlayerId notifySinglePlayer) {
        this.notifySinglePlayer = notifySinglePlayer;
    }

    public void setNotifyAll(boolean notifyAll) {
        this.notifyAll = notifyAll;
    }

    public PlayerId getNotifySinglePlayer() {
        return notifySinglePlayer;
    }

    public boolean isNotifyAll() {
        return notifyAll;
    }


    public final ConcreteEnvelope wrap()
    {
        return new ConcreteEnvelope(this);
    }

}
