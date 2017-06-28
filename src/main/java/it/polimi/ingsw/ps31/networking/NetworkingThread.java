package it.polimi.ingsw.ps31.networking;

import it.polimi.ingsw.ps31.messages.GenericMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/06/2017.
*/
public abstract class NetworkingThread extends Thread {


    public final void run()
    {
        loop();
    }
/*
    public GenericMessage readMessage()
    {
        if( buffer.isEmpty())
            return null;
        return buffer.remove(0);
    }
*/
    protected abstract void loop();


}
