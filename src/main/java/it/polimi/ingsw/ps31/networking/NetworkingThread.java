package it.polimi.ingsw.ps31.networking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/06/2017.
*/
public abstract class NetworkingThread extends Thread {
    private List<GenericMessage> buffer;

    /* Constructor */
    public NetworkingThread()
    {
        this.buffer = new ArrayList<>();
    }

    public final void run()
    {
        loop();
    }

    public void bufferizeMessage(GenericMessage msg)
    {
        buffer.add(msg);
    }

    public GenericMessage readMessage()
    {
        if( buffer.isEmpty())
            return null;
        return buffer.remove(0);
    }

    protected abstract void loop();

    public void forward(GenericMessage msgToForward)
    {
        //Il messaggio non è per il networking, dunque lo inserisco nel buffer di messaggi per la view
        bufferizeMessage(msgToForward);
    }

}
