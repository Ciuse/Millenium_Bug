package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.ClientNetworking.ClientNetworkInterface;

/**
 * Created by Francesco on 11/06/2017.
 */
public class NetworkingThread extends Thread {
    private ClientNetworkInterface clientNetworkInterface;
    private ViewThread viewThread;

    /* Constructor */
    public NetworkingThread(ClientNetworkInterface clientNetworkInterface)
    {
        this.clientNetworkInterface = clientNetworkInterface;
    }

    /* run() method */
    public void run()
    {
        clientNetworkInterface.switchOn();

        while (!clientNetworkInterface.isSwitchOff())
        {
            try
            {
                sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        clientNetworkInterface.close();
    }

    public void setViewThread(ViewThread viewThread)
    {
        this.viewThread = viewThread;
    }

    public ClientNetworkInterface getClientNetworkInterface()
    {
        return this.clientNetworkInterface;
    }


}
