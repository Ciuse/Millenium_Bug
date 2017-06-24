package it.polimi.ingsw.ps31.messages;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;
import it.polimi.ingsw.ps31.networking.ClientNetworkingVisitor;

/**
 * Created by Francesco on 13/06/2017.
 */
public /*todo: abstract*/ class GenericMessage implements NetworkingVisitable {
    private String state = null;
    protected String messageType = "GenericMessage";

    public GenericMessage(){}
    public GenericMessage(String state)
    {
        this.state = state;
    }

    public String update()
    {
        return this.state;
    }

    public void update(ClientNetworkingVisitor networkingVisitor)
    {
        networkingVisitor.visit(this);
    }

    public final String messageTypeToString()
    {
        return this.messageType;
    }
    public String getMessageType()
    {
        return this.messageType;
    }

    @Override
    public void update(ClientNetworkInterface networkInterface) {

    }
}
