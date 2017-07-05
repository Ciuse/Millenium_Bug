package it.polimi.ingsw.ps31.messages.messageMV;


import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 01/06/2017.
 */
public abstract class MVVisitable extends GenericMessage{
    PlayerId notifySinglePlayer=null;
    boolean notifyAll =false;

    public MVVisitable() {
//        super(DeliverableMessageType.MV_VISITABLE);
    }

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

    public abstract void accept(MVVisitor mvVisitor);

    public final ConcreteEnvelope wrap()
    {
        return new ConcreteEnvelope(this);
    }

}
