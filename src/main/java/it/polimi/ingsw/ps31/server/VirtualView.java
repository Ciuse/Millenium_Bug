package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
/**
 * Created by Giuseppe on 22/06/2017.
 * <p>
 * Classe che implementa la logica "Observable/observer" e funga da View vera per il model e il controller
 * ma in realtà serve per raccogliere gli update provenienti dal model e inoltrarli via networking al network handler(ClientMessageHistory) del client
 * e per raccogliere i notify proveniente dal networking handler(ClientMessageHistory) del client e inoltrarli al controller
 * Inoltre memorizza l' ultimo messaggio che è stato mandato via networking così che il controller può richiederne l'invio se necessario
 *
 * @see NetworkInterface
 * @see Controller
 * @see Model
 * @see ClientMessageHistory
 */
public class VirtualView extends Observable implements Observer {
    private final NetworkInterface networkInterface;
    private MVVisitable lastMessageSent;

    public VirtualView(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public void addController(Controller controller) {
        this.addObserver(controller);
    }


    public void notifyController(VCVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }

    @Override
    public void update(Observable o, Object arg) {
        MVVisitable message = (MVVisitable) arg;
        if (message.getClass().equals(MVAskChoice.class)) {
            setLastMessageSent(message);
        }

        if (message.isNotifyAll()) { //se il messaggio riguarda tutti lo inoltro a tutti i client
            networkInterface.sendToAll(message);

        } else {  //se il messaggio notifica solo un controller lo mando solo a lui
            networkInterface.sendToClient(message, message.getNotifySinglePlayer());
        }
    }

    public void reSendLastMessage(String string) {
        if (lastMessageSent.isNotifyAll()) { //se il messaggio riguarda tutti lo inoltro a tutti i client
            networkInterface.sendToAll(new MVStringToPrint(null, true, string));
            networkInterface.sendToAll(lastMessageSent);
        } else {  //se il messaggio notifica solo un controller lo mando solo a lui
            networkInterface.sendToClient(new MVStringToPrint(lastMessageSent.getNotifySinglePlayer(), false, string), lastMessageSent.getNotifySinglePlayer());
            networkInterface.sendToClient(lastMessageSent, lastMessageSent.getNotifySinglePlayer());
        }
    }

    public void reSendLastMessageToSpecificView(String string, PlayerId viewId) {
        networkInterface.sendToClient(new MVStringToPrint(viewId, false, string), viewId);
        networkInterface.sendToClient(lastMessageSent, viewId);

    }

    public void setLastMessageSent(MVVisitable lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }
}
