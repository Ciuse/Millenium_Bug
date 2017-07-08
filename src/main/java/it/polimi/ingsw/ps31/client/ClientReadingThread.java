package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;

import java.io.IOException;

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

        DebugUtility.simpleDebugMessage("Inizio ascolto socket.");
        MVVisitable mvVisitable;
        while( !stopListening )
        {
            try {
                mvVisitable = clientNetworkInterface.readFromServer(false);
                DebugUtility.simpleDebugMessage("Messaggio ricevuto: "+mvVisitable);
                if (mvVisitable != null)
                    clientMessageHistory.newMessage(mvVisitable);
            } catch (IOException e) {
                stopListening = true;
            }
            DebugUtility.simpleDebugMessage("Esco dal ciclo di lettura.");
        }

        // TODO: Istruzioni per la disconnessione
    }

}
