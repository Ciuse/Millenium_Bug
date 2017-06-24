package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;

/**
 * Created by Francesco on 11/06/2017.
 */
public class NetworkingThread extends Thread {
    private ClientNetworkInterface clientNetworkInterface;
    private ViewThread viewThread;
    private boolean closeClientNetworkingInterface = false;

    /* Constructor */
    public NetworkingThread(ClientNetworkInterface clientNetworkInterface)
    {
        this.clientNetworkInterface = clientNetworkInterface;
    }

    /* run() method */
    public void run()
    {
        while(!closeClientNetworkingInterface)
        {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setViewThread(ViewThread viewThread)
    {
        this.viewThread = viewThread;
    }

    public ClientNetworkInterface getClientNetworkInterface()
    {
        return this.clientNetworkInterface;
    }

    public void setCloseClientNetworkingInterface (boolean haveToClose)
    {
        this.closeClientNetworkingInterface = haveToClose;
    }
//
//    public View askServerForView(ConnectionMessage connectionMessage)
//    {
//        return clientNetworkInterface.firstConnectionProcedure(connectionMessage);
//    }
    public View getViewFromServer()
    {
        return null;
    }
}
