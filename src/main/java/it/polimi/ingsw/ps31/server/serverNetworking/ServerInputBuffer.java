package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 30/06/2017.
 */
public class ServerInputBuffer {
    private List<VCVisitable> vcVisitableBuffer;
    private PlayerCommunicationInterface playerCommunicationInterface;

    /* Constructor */
    public ServerInputBuffer(PlayerCommunicationInterface playerCommunicationInterface)
    {
        this.playerCommunicationInterface = playerCommunicationInterface;
        this.vcVisitableBuffer = new ArrayList<>();
    }

    /* Writing method */
    public void bufferizeMessage(VCVisitable msg)
    {
        if (msg != null ) {
            this.vcVisitableBuffer.add(msg);
            playerCommunicationInterface.notifyNewMessages();
        }
    }

    /* Reading method */
    public VCVisitable readVC()
    {
        if ( !this.vcVisitableBuffer.isEmpty() )
            return vcVisitableBuffer.remove(0);
        return null;
    }

    public boolean isEmpty()
    {
        return vcVisitableBuffer.isEmpty();
    }

}
