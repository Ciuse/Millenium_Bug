package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.ClientNetworkingThread;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkingVisitor;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

/**
 * Created by Francesco on 23/06/2017.
 */
public abstract class NetworkingVisitable implements GenericMessage {

    public abstract void update(NetworkingThread clientNetworkingThread);
}
