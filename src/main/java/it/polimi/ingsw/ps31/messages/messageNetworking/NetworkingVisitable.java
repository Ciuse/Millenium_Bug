package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.networking.ClientNetworkingVisitor;

/**
 * Created by Francesco on 23/06/2017.
 */
public interface NetworkingVisitable {

    void update(ClientNetworkInterface networkInterface);
}
