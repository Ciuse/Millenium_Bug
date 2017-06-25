package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.client.ClientNetworkingThread;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;
import it.polimi.ingsw.ps31.networking.NetworkingVisitor;

/**
 * Created by Francesco on 23/06/2017.
 */
public class ClientNetworkingVisitor extends NetworkingVisitor{
    private final ClientNetworkingThread clientNetworkingThread;

    /* Constructor */
    public ClientNetworkingVisitor(ClientNetworkingThread clientNetworkingThread)
    {
        this.clientNetworkingThread = clientNetworkingThread;
    }

    public void visit(GenericMessage message)
    {
        message.update(this.clientNetworkingThread);
    }
}
