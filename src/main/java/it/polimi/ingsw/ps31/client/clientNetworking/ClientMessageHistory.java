package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class ClientMessageHistory extends Observable implements Observer{
    private List<MVVisitable> inboundHistory;
    private List<VCVisitable> outgoingHistory;
    private ClientNetworkInterface clientNetworkInterface;

    public ClientMessageHistory(ClientNetworkInterface networkInterface)
    {
        this.inboundHistory = new ArrayList<>();
        this.outgoingHistory = new ArrayList<>();
        this.clientNetworkInterface = networkInterface;
    }

    public void addView(View view) {
        System.out.println("ClientMessageHistory:addView> Aggiungo la view agli osservatori");
        addObserver(view);
    }

    public void newMessage(MVVisitable msg)
    {
        System.out.println("ClientMessageHistory:newMessage> Ricevuto nuovo messagio da bufferizzare e notificare");
        this.setChanged();
        notifyObservers(msg);
        System.out.println("ClientMessageHistory:newMessage> Messaggio notificato. Aggiungo alla cronologia");
        inboundHistory.add(msg);
    }

    public boolean isEmpty()
    {
        return inboundHistory.isEmpty();
    }

    public List<MVVisitable> getInboundHistory() {
        return inboundHistory;
    }

    public String printInboundHistory()
    {
        int i =1;
        String h = "CRONOLOGIA MESSAGGI RICEVUTI\n"+
                   "=============================\n\n";
        for (MVVisitable currentMsg : inboundHistory)
            h += i +"\t:" + currentMsg.toString() +"\n";

        return h;
    }

    @Override
    public void update(Observable o, Object arg) {
        VCVisitable msg = (VCVisitable) arg;
        this.outgoingHistory.add(msg);
        clientNetworkInterface.sendToServer(msg);
    }
}
