package it.polimi.ingsw.ps31.messages.messageMV;


import it.polimi.ingsw.ps31.client.ClientNetworkingThread;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

/**
 * Created by giulia on 01/06/2017.
 */
public abstract class MVVisitable implements GenericMessage {
    PlayerId notifySinglePlayer=null;
    boolean notifyAll =false;

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

    @Override
    public void update(NetworkingThread networkingThread) {
        networkingThread.forward(this);
    }
}
