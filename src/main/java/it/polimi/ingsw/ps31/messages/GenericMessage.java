package it.polimi.ingsw.ps31.messages;

import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkingVisitor;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

/**
 * Created by Francesco on 13/06/2017.
 */
public interface GenericMessage {

    public void update (NetworkingThread networkingThread);
}
