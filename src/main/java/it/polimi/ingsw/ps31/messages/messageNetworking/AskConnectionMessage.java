package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;

/**
 * Created by Francesco on 23/06/2017.
 */
public class AskConnectionMessage extends NetworkingMessage {

    @Override
    public void update(ClientNetworkInterface clientNetworkInterface) {
        clientNetworkInterface.sendConnectionMessage();
    }
}
