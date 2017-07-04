package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.Match;

import javax.swing.text.Style;
import java.io.IOException;
import java.util.List;

/**
 * Created by Francesco on 30/06/2017.
 */
public class PlayerCommunicationInterface {
    private ServerConnectionInterface serverConnectionInterface;
    private ServerInputBuffer serverInputBuffer;
    private boolean newMessages;
    private boolean disconnected;
    private ServerListeningThread listeningThread;
    private PlayerId playerId;
    private ConnectionMessage connectionMessage;
    private PlayerTable playerTable;
    private MatchTable matchTable;
    private List<GenericMessage> pendingMessages;

    /* Constructor */
    public PlayerCommunicationInterface(ServerConnectionInterface serverConnectionInterface, MatchTable matchTable)
    {
        //Inizializzo attributi di base
        this.newMessages = false;
        this.disconnected = false;

        //Creo l'infrastruttura di comunicazione con la rete
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = new ServerInputBuffer(this);
        listeningThread = new ServerListeningThread(serverConnectionInterface, serverInputBuffer);

        this.matchTable = matchTable;
    }

    public void switchOn()
    {
        listeningThread.start();
    }

    public void setPlayerTable(PlayerTable playerTable)
    {
        this.playerTable = playerTable;
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
        System.out.println("PlayerCommunicationInterface:send> msg = "+msg+"; disconnected="+disconnected);
        if( msg != null )
        {
            if( disconnected )
                pendingMessages.add(msg);
            else
                serverConnectionInterface.sendToClient(msg);
        }
    }


    public void closeConnection() {
        this.disconnected = true;
        matchTable.disconnectClient(this);
    }

    public boolean reopenClosedConnection(ServerConnectionInterface newConnection)
    {
        //Controllo che il connectionMessage vecchio e quello nuovo contengano stessi id e password
        ConnectionMessage newCM = newConnection.getConnectionMessage();
        if( ! newCM.getUsername().equals(connectionMessage.getUsername()) ||
            ! newCM.getPassword().equals(connectionMessage.getUsername()))
            return false;

        this.serverConnectionInterface = newConnection;
        ServerListeningThread listeningThread = new ServerListeningThread(serverConnectionInterface, serverInputBuffer);

        return true;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public void setConnectionMessage(ConnectionMessage connectionMessage)
    {
        if( connectionMessage == null )
            return; //todo: significa che il client (stronzo) si è disconnesso nel momento peggiore
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

    public boolean isDisconnected()
    {
        return disconnected;
    }
    @Override
    public String toString()
    {
        String res = "USERNAME = " + this.connectionMessage.getUsername() + "; " +
                     "PASSWORD = " + this.connectionMessage.getPassword() + "; " +
                     "Porta = "    + serverConnectionInterface.getConnectionInfo();

        return res;
    }
}
