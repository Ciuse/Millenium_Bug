package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.messages.messageMV.MVMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 22/06/2017.
 */
public class VirtualView extends Observable implements Observer {
    private final NetworkInterface networkInterface;
    private MVVisitable lastMessageSent;

    public VirtualView(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }


    public void notifyController(VCVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }

    @Override
    public void update(Observable o, Object arg) {
        MVMessageVisitor MVMessageVisitor = new MVMessageVisitor();
        MVVisitable message = (MVVisitable) arg;
        setLastMessageSent(message);

        if (message.isNotifyAll()) { //se il messaggio riguarda tutti lo inoltro a tutti i client
//            networkInterface.sendToAll(message);

        } else {  //se il messaggio notifica solo un controller lo mando solo a lui
            networkInterface.sendToClient(message, message.getNotifySinglePlayer());
        }
    }

    public void reSendLastMessage(String string){
        if (lastMessageSent.isNotifyAll()) { //se il messaggio riguarda tutti lo inoltro a tutti i client
//            networkInterface.sentToAll(new MVStringToPrint(null,true,string));
//            networkInterface.sendToAll(lastMessageSent);
        }
        else {  //se il messaggio notifica solo un controller lo mando solo a lui
        networkInterface.sendToClient(new MVStringToPrint(lastMessageSent.getNotifySinglePlayer(),false,string),lastMessageSent.getNotifySinglePlayer());
        networkInterface.sendToClient(lastMessageSent, lastMessageSent.getNotifySinglePlayer());
        }
    }

    public void setLastMessageSent(MVVisitable lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }
}
