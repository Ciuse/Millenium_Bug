package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkingVisitor;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 11/06/2017.
 */

public class ClientNetworkingThread extends NetworkingThread {
    private ClientNetworkInterface clientNetworkInterface;
    private ViewThread viewThread;
    private boolean closeClientNetworkingInterface = false;
    protected final ClientNetworkingVisitor clientNetworkingVisitor;
    protected View view = null;


    /* Constructor */
    public ClientNetworkingThread(ClientNetworkInterface clientNetworkInterface)
    {
        this.clientNetworkInterface = clientNetworkInterface;
        this.clientNetworkingVisitor = new ClientNetworkingVisitor(this);
    }

    @Override
    public void loop()
    {
        while(!closeClientNetworkingInterface)
        {
            GenericMessage msgFromServer = clientNetworkInterface.readFromServer();
            if( msgFromServer != null )
                bufferizeMessage(msgFromServer);
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
//    public boolean isViewReceived()
//    {
//        return clientNetworkInterface.isViewReceived();
//    }
//
//    public View moveViewFromNetworkInterface()
//    {
//       return clientNetworkInterface.dumpView();
//    }
//
    public GenericMessage nextMessage()
    {
        return super.readMessage();
    }

    //Tecnicamente inutile, si usa solo all'inizio per i messaggi di connessione con il server
    public void visitMessage(NetworkingMessage msg)
    {
        clientNetworkingVisitor.visit(msg);
    }

    public View extrapolateViewFromMessage(GenericMessage msg)
    {
        clientNetworkingVisitor.visit(msg);
        return this.view;
    }

    /* Visitor actions */
    public void setView(View view)
    {
        this.view = view;
    }


}
