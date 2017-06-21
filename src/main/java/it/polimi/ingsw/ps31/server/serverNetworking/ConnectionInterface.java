package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.networking.ConnectionType;

/**
 * Created by Francesco on 08/06/2017.
*/

public abstract class ConnectionInterface {
    private final ConnectionType connectionType;

    /* Constructor */
    public ConnectionInterface(ConnectionType connectionType)
    {
        this.connectionType = connectionType;
    }

    public abstract String notifyModel();
    public abstract void notifyClient(String msg);

    public abstract String getConnectionInfo();
}
