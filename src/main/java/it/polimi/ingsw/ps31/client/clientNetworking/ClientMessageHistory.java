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
 *
 * Cronologia dei messaggi in arrivo da e in uscita per il server. Si appoggia alla ClientNetworkInterface
 * per la comunicazione in rete e si comporta per la View come se contenesse sia il Model che il Controller,
 * in modo da rendere indipendente l'applicazione dall' architettua client-server.
 * Dovendo simulare sia il Model che il Controller, è sia un Observer che un Observable.
 * La cronologia in uscita è compilata ma non è mai usata; è presente per mantenere traccia dei messaggi
 * e semplificare possibili futuri sviluppi.
 *
 * @see View
 * @see ClientNetworkInterface
 * @see Observer
 * @see Observable
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

    /**
     * Aggiunge la view agli osservatori
     * @param view la View del client
     */
    public void addView(View view) {
        //System.out.println("ClientMessageHistory:addView> Aggiungo la view agli osservatori");
        addObserver(view);
    }

    /**
     * notifica gli osservatori con un messaggio e lo aggiunge alla cronologia dei messaggi in ingresso
     * @param msg
     */
    public void newMessage(MVVisitable msg)
    {
        //System.out.println("ClientMessageHistory:newMessage> Ricevuto nuovo messagio da bufferizzare e notificare");
        this.setChanged();
        notifyObservers(msg);
        //System.out.println("ClientMessageHistory:newMessage> Messaggio notificato. Aggiungo alla cronologia");
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

    /**
     * Simula il comportamento del controller per la View e trasmette il
     * messaggio ricevuto alla ClientNetworkInterface. Aggiunge il messaggio alla cronologia in uscita
     *
     * @param o classe observable che invoca il metodo
     * @param arg oggetto notificato
     */
    @Override
    public void update(Observable o, Object arg) {
        VCVisitable msg = (VCVisitable) arg;
        this.outgoingHistory.add(msg);
        clientNetworkInterface.sendToServer(msg);
    }
}
