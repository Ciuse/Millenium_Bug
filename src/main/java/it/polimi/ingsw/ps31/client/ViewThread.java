package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.view.View;

/**
 * Created by Francesco on 11/06/2017.
 */
public class ViewThread extends Thread {
    private final View view;
    private ClientNetworkingThread clientNetworkingThread;


//    private final

    /* Constructor */
    public ViewThread(View view)
    {
        this.view = view;
    }

    /* run() method */
    public void run()
    {
        //todo: mettere qui il codice di funzionalmento della view (generica! non TerminalView!)
        //view.switchOn();
    }

    public void setNetworking(ClientNetworkingThread clientNetworkingThread)
    {
        this.clientNetworkingThread = clientNetworkingThread;
        //this.view.setNetworkInterface(clientNetworkingThread.getClientNetworkInterface());
    }
}
