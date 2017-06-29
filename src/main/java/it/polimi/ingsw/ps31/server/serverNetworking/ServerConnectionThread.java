package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/06/2017.
 */


public class ServerConnectionThread extends Thread {
    private ServerConnectionInterface serverConnectionInterface;
    private boolean closeConnection = false;
    private MatchTable matchTable;
    private NetworkInterface networkInterface;
    private PlayerId playerId;
    protected List<VCVisitable> buffer;

    /* Constructor */
    public ServerConnectionThread(ServerConnectionInterface serverConnectionInterface, MatchTable matchTable) {
        this.buffer = new ArrayList<>();
        this.serverConnectionInterface = serverConnectionInterface;
        this.matchTable = matchTable;
        this.playerId = null;

    }

    public void setPlayerId(PlayerId playerId)
    {
        this.playerId = playerId;
    }

    public void bufferizeMessage(VCVisitable msg) {

        buffer.add(msg);
    }

    @Override
    public void run()
    {
        System.out.println("ServerConnectionThread:run> thread avviato. inizializzo connessione");

        //Istruzioni di instaurazione della connessione
        initializeConnection();

        while (!closeConnection) {
            VCVisitable msgFromClient = null;
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

    public VCVisitable readMessage()
    {
        if( buffer.isEmpty())
            return null;
        return buffer.remove(0);
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

    public VCVisitable readFromClient() throws IOException
    {
        return serverConnectionInterface.readFromClient();
    }

    public void  sendToClient(GenericMessage msg)
    {
        serverConnectionInterface.sendToClient(msg);
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