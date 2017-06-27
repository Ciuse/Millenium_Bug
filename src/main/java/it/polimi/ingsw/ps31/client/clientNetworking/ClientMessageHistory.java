package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class ClientMessageHistory extends Observable{
    private List<GenericMessage> history;

    public ClientMessageHistory()
    {
        this.history = new ArrayList<>();
    }

    public void addView(View view) {
        this.addObserver(view);
    }

    public void newMessage(GenericMessage msg)
    {
        notifyObservers(msg);
        history.add(msg);
    }

    public List<GenericMessage> getHistory() {
        return history;
    }

    public String printHistory()
    {
        int i =1;
        String h = "CRONOLOGIA MESSAGGI RICEVUTI\n"+
                   "=============================\n\n";
        for (GenericMessage currentMsg : history)
            h += i +"\t:" + currentMsg.toString() +"\n";

        return h;
    } 
}
