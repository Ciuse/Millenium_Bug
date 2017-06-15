package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.networking.ConnectionState;
import it.polimi.ingsw.ps31.networking.ConnectionType;

/**
 * Created by Francesco on 08/06/2017.
*/

public abstract class ConnectionInterface {
    private final ConnectionType connectionType;
    private final ConnectionState connectionState;

    /* Constructor */
    public ConnectionInterface(ConnectionType connectionType)
    {
        this.connectionType = connectionType;
        this.connectionState = ConnectionState.CONNECTED;
    }

    public abstract String notifyModel();
    public abstract void notifyClient(String msg);

    public abstract String getConnectionInfo();
    public abstract void close();
}
