package it.polimi.ingsw.ps31.server.serverNetworking;

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

}
