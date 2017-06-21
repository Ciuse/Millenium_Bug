package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.networking.ConnectionType;

/**
 * Created by Francesco on 21/06/2017.
 */
public class ConnectionMessage extends GenericMessage {
    private ConnectionType connectionType;
    private String username;
    private String password;


    public ConnectionMessage(ConnectionType connectionType, String username, String password)
    {

    }
}
