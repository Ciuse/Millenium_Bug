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

    private final Object bufferLock = new Object();

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
            synchronized (bufferLock)
            {
                this.vcVisitableBuffer.add(msg);
            }
            playerCommunicationInterface.notifyNewMessages();
        }
    }

    /* Reading method */
    public VCVisitable readVC()
    {
        System.out.println("ServerInputBuffer:readVC> Leggo da buffer. Stato="+vcVisitableBuffer.isEmpty());

        VCVisitable ret = null;
        if ( !this.vcVisitableBuffer.isEmpty() )
            synchronized (bufferLock)
            {
                ret = vcVisitableBuffer.remove(0);
            }
        return ret;
    }

    public boolean isEmpty()
    {
        return vcVisitableBuffer.isEmpty();
    }

}
