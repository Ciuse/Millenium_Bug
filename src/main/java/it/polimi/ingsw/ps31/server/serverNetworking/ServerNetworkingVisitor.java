package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingVisitable;
import it.polimi.ingsw.ps31.networking.NetworkingVisitor;

/**
 * Created by Francesco on 25/06/2017.
 */
public class ServerNetworkingVisitor extends NetworkingVisitor{
    private ServerConnectionThread serverConnectionThread;

    public ServerNetworkingVisitor(ServerConnectionThread serverConnectionThread)
    {
        this.serverConnectionThread = serverConnectionThread;
    }

    public void visit(NetworkingVisitable message)
    {
        message.update(this.serverConnectionThread);
    }
}
