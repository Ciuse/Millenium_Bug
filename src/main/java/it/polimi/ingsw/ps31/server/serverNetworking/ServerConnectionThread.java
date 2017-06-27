package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.networking.NetworkingThread;
import it.polimi.ingsw.ps31.server.Server;
import it.polimi.ingsw.ps31.server.ServerNetworkInterface;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/06/2017.
 */


public class ServerConnectionThread extends NetworkingThread {
    private ServerConnectionInterface serverConnectionInterface;
    private boolean closeConnection = false;
    private ServerNetworkingVisitor serverNetworkingVisitor;
    private MatchTable matchTable;
    private NetworkInterface networkInterface;
    private PlayerId playerId;

    /* Constructor */
    public ServerConnectionThread(ServerConnectionInterface serverConnectionInterface, MatchTable matchTable) {
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverNetworkingVisitor = new ServerNetworkingVisitor(this);
        this.matchTable = matchTable;
        this.playerId = null;

    }

    public void setPlayerId(PlayerId playerId)
    {
        this.playerId = playerId;
    }

    @Override
    public void loop()
    {
        //Istruzioni di instaurazione della connessione
        initializeConnection();

        while (!closeConnection) {
            GenericMessage msgFromClient = null;
            try {
                msgFromClient = serverConnectionInterface.readFromClient();
            } catch (SocketException se)
            {
                matchTable.disconnectClient(serverConnectionInterface, networkInterface.getMatch(), this.playerId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (msgFromClient != null)
                bufferizeMessage(msgFromClient);
        }


        /*while ( listenNetworkInterfaces )
        {
            for ( PlayerId playerId : networkInterface.getPlayerIdList() )
                networkInterface.readFromClient(playerId);

            try {
                sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    protected final void initializeConnection() {
        //Invio al server una stringa per comunicare che la connessione è avvenuta con successo
        serverConnectionInterface.writeOnNetwork("Connection ok");
        serverConnectionInterface.waitForConnectionMessage();
    }

    public void closeConnection() {
        this.closeConnection = true;
    }

    public boolean receivedCM()
    {
        return ( serverConnectionInterface.receivedCM());
    }

    public ConnectionMessage getConnectionMessage()
    {
        return serverConnectionInterface.getConnectionMessage();
    }

    public GenericMessage readFromClient() throws IOException
    {
        return serverConnectionInterface.readFromClient();
    }

    public void  sendToClient(GenericMessage msg)
    {
        serverConnectionInterface.sendToClient(msg);
    }

    public void sendView(View view)
    {
        serverConnectionInterface.sendView(view);
    }

    public void setNetworkInterface(NetworkInterface networkInterface)
    {
        this.networkInterface = networkInterface;
    }

    public String toString()
    {
        String res = "USERNAME = " + this.serverConnectionInterface.getConnectionMessage().getUsername() + "; " +
                     "PASSWORD = " + this.serverConnectionInterface.getConnectionMessage().getPassword();

        return res;
    }
}