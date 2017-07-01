package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Francesco on 11/06/2017.
 */
//Thread creato con il compito di ascoltare eventuali messaggi da server
public class ClientReadingThread extends Thread {
    private ClientNetworkInterface clientNetworkInterface;
    private ClientMessageHistory clientMessageHistory;
    private boolean stopListening = false;


    /* Constructor */
    public ClientReadingThread(ClientNetworkInterface clientNetworkInterface, ClientMessageHistory clientMessageHistory) {
                this.clientNetworkInterface = clientNetworkInterface;
                this.clientMessageHistory = clientMessageHistory;
    }

    @Override
    public void run()
    {
        System.out.println("ClientReadingThread:run> Inizio ascolto socket.");
        MVVisitable mvVisitable;
        while( !stopListening )
        {
            mvVisitable = clientNetworkInterface.readFromServer(false);
            if (mvVisitable != null)
                clientMessageHistory.newMessage(mvVisitable);
        }
    }

}
