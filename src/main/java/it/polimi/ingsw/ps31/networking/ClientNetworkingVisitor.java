package it.polimi.ingsw.ps31.networking;

import it.polimi.ingsw.ps31.client.Client;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;

/**
 * Created by Francesco on 23/06/2017.
 */
public class ClientNetworkingVisitor {
    private final ClientNetworkInterface clientNetworkInterface;

    public ClientNetworkingVisitor(ClientNetworkInterface clientNetworkInterface)
    {
        this.clientNetworkInterface = clientNetworkInterface;
    }

    public void visit(NetworkingVisitable message)
    {
        message.update(this.clientNetworkInterface);
    }
}
