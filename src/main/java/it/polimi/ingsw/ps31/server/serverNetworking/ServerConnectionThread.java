package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/06/2017.
 */


public class ServerConnectionThread extends NetworkingThread {
    private ServerConnectionInterface serverConnectionInterface;
    private boolean closeConnection = false;
    private ServerNetworkingVisitor serverNetworkingVisitor;

    /* Constructor */
    public ServerConnectionThread(ServerConnectionInterface serverConnectionInterface) {
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverNetworkingVisitor = new ServerNetworkingVisitor(this);

    }

    @Override
    public void loop()
    {
        //Istruzioni di instaurazione della connessione
        initializeConnection();

        while (!closeConnection) {
            GenericMessage msgFromClient = serverConnectionInterface.readFromClient();
            if (msgFromClient != null)
                bufferizeMessage(msgFromClient);
        }
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
}