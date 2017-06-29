package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class ClientMessageHistory extends Observable{
    private List<MVVisitable> history;

    public ClientMessageHistory()
    {
        this.history = new ArrayList<>();
    }

    public void addView(View view) {
        System.out.println("ClientMessageHistory:addView> Aggiungo la view agli osservatori");
        this.addObserver(view);
    }

    public void newMessage(MVVisitable msg)
    {
        System.out.println("ClientMessageHistory:newMessage> Ricevuto nuovo messagio da bufferizzare e notificare");
        notifyObservers(msg);
        history.add(msg);
    }

    public boolean isEmpty()
    {
        return history.isEmpty();
    }

    public List<MVVisitable> getHistory() {
        return history;
    }

    public String printHistory()
    {
        int i =1;
        String h = "CRONOLOGIA MESSAGGI RICEVUTI\n"+
                   "=============================\n\n";
        for (MVVisitable currentMsg : history)
            h += i +"\t:" + currentMsg.toString() +"\n";

        return h;
    } 
}
