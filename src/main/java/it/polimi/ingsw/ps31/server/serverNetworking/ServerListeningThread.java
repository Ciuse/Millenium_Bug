package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.io.IOException;

/**
 * Created by Francesco on 25/06/2017.
 */

//Thread con il compito di rimanere in attesa di messaggi sulla socket
public class ServerListeningThread extends Thread {
    private ServerConnectionInterface serverConnectionInterface;
    private ServerInputBuffer serverInputBuffer;
    private boolean closeConnection;

    public ServerListeningThread ( ServerConnectionInterface serverConnectionInterface, ServerInputBuffer serverInputBuffer)
    {
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = serverInputBuffer;
        this.closeConnection = false;
    }

    //public void start(){}
    public void run()
    {
        DebugUtility.simpleUserMessage("Inizio ascolto del client");
        while (!closeConnection)
        {
            VCVisitable msgFromNetwork;
            try {
                msgFromNetwork = serverConnectionInterface.readFromClient();

                serverInputBuffer.bufferizeMessage(msgFromNetwork);
            } catch (IOException e)
            {
                DebugUtility.simpleUserMessage(/*"ServerListeningThread:run> */"Client "+serverConnectionInterface.getConnectionInfo()+" disconnesso");
                closeConnection = true; //Esce dal ciclo e disconnette il client
            }

        }
        //matchTable.disconnectClient(serverConnectionInterface, networkInterface.getMatch(), this.playerId);
        serverConnectionInterface.disconnect();
        serverInputBuffer.disconnect();

    }
}