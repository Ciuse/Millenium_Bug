package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.view.ViewProva;

/**
 * Created by Francesco on 11/06/2017.
 */
public class ViewThread extends Thread {
    private final ViewProva view;
    private NetworkingThread networkingThread;

//    private final

    /* Constructor */
    public ViewThread()
    {
        this.view = new ViewProva();
    }

    /* run() method */
    public void run()
    {
        //todo: mettere qui il codice di funzionalmento della view (generica! non TerminalView!)
        view.switchOn();
    }

    public void setNetworking(NetworkingThread networkingThread)
    {
        this.networkingThread = networkingThread;
        this.view.setNetworkInterface(networkingThread.getClientNetworkInterface());
    }
}
