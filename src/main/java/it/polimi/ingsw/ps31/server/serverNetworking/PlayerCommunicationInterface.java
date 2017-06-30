package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.io.IOException;

/**
 * Created by Francesco on 30/06/2017.
 */
public class PlayerCommunicationInterface {
    private ServerConnectionInterface serverConnectionInterface;
    private ServerInputBuffer serverInputBuffer;
    private boolean newMessages;

    private PlayerId playerId;
    private ConnectionMessage connectionMessage;

    /* Constructor */
    public PlayerCommunicationInterface(ServerConnectionInterface serverConnectionInterface)
    {
        //Inizializzo attributi di base
        this.newMessages = false;

        //Creo l'infrastruttura di comunicaizone con la rete
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = new ServerInputBuffer(this);
        ServerListeningThread listeningThread = new ServerListeningThread(serverConnectionInterface, serverInputBuffer);

        //Inizializzo la connessione al server
        initializeConnection();

        //Inizio ascolto
        listeningThread.start();
    }

    protected final void initializeConnection() {
        //Invio al client una stringa per comunicare che la connessione è avvenuta con successo
        serverConnectionInterface.writeOnNetwork("Connection ok");
        setConnectionMessage(serverConnectionInterface.waitForConnectionMessage());
    }

    public void notifyNewMessages()
    {
        this.newMessages = true;
    }

    public boolean newMessages()
    {
        return this.newMessages;
    }

    public VCVisitable nextMessage()
    {
        if ( !newMessages )
            return null;

        VCVisitable message = serverInputBuffer.readVC();
        if ( serverInputBuffer.isEmpty() )
            this.newMessages = false;

        return message;
    }

    public void send(GenericMessage msg)
    {
        if( msg != null )
            serverConnectionInterface.sendToClient(msg);
    }


    public void closeConnection() {
//        this.clo
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    private void setConnectionMessage(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
    }

    public boolean receivedCM()
    {
        return (this.connectionMessage != null);
    }

    public ConnectionMessage getConnectionMessage()
    {
        return this.connectionMessage;
    }
}
