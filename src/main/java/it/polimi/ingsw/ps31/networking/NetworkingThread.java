package it.polimi.ingsw.ps31.networking;

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
        if( inboundBuffer.isEmpty())
            return null;
        return inboundBuffer.remove(0);
    }
*/
    protected abstract void loop();


}
