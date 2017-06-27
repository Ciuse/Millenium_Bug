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
    private boolean closeClientNetworkingInterface = false;
    protected View view = null;


    /* Constructor */
    public ClientNetworkingThread(ClientNetworkInterface clientNetworkInterface)
    {
        this.clientNetworkInterface = clientNetworkInterface;


        System.out.println("In attesa di altri giocatori. Un po' di pazienza...");

        //Rimango in attesa della view dal server
        GenericMessage msgFromServer;
        do {

            try
            {
                sleep(400);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            msgFromServer = nextMessage();
        }while ( msgFromServer == null );

        System.out.println("VIEW RICEVUTA!!!");
    }

    @Override
    public void loop()
    {
        while(!closeClientNetworkingInterface)
        {
            GenericMessage msgFromServer = clientNetworkInterface.readFromServer();
            if( msgFromServer != null )

            try { sleep(700); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void bufferizeMessage(GenericMessage msg) {
        super.buffer.add(msg);

    }

    public ClientNetworkInterface getClientNetworkInterface()
    {
        return this.clientNetworkInterface;
    }

    public void setCloseClientNetworkingInterface (boolean haveToClose)
    {
        this.closeClientNetworkingInterface = haveToClose;
    }

    public GenericMessage nextMessage()
    {
        return super.readMessage();
    }



    /* Visitor actions */
    public void setView(View view)
    {
        this.view = view;
    }


}
