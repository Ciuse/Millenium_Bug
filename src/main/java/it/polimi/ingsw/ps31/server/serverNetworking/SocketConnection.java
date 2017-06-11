package it.polimi.ingsw.ps31.server.serverNetworking;

import java.net.Socket;

/**
 * Created by Francesco on 09/06/2017.
 */
public class SocketConnection extends ConnectionInterface {
    private Socket socket;

    /* Constructor */
    public SocketConnection(Socket socket) {
        super(ConnectionType.SOCKET);
        this.socket = socket;
    }
}
