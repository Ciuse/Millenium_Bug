package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 30/06/2017.
 *
 * Buffer contenente i messaggi in arrivo dal client. Ad ogni lettura restituisce ed elimina sempre il
 * messaggio ricevuto meno di recente
 * @see ServerListeningThread
 * @see PlayerCommunicationInterface
 * @see ServerConnectionInterface
 */
public class ServerInputBuffer {
    /** Buffer dei messaggi di tipo VCVisitable*/
    private List<VCVisitable> vcVisitableBuffer;

    /** Riferimento alla playerCommunicationInterface */
    private PlayerCommunicationInterface playerCommunicationInterface;

    /** Lock del buffer */
    private final Object bufferLock = new Object();

    /* Constructor */
    public ServerInputBuffer(PlayerCommunicationInterface playerCommunicationInterface)
    {
        this.playerCommunicationInterface = playerCommunicationInterface;
        this.vcVisitableBuffer = new ArrayList<>();
    }

    /* Writing method */
    /**
     * Bufferizza un messagio di tipo VCVisitable solo se non è null. Altrimenti lo ingora.
     * @param msg messaggio da bufferizzare
     */
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
    /**
     * Restituisce, rimuovendolo dal buffer, il messaggio bufferizzato meno di recente
     * @return messaggio meno recente nel buffer
     */
    public VCVisitable readVC()
    {
        //System.out.println("ServerInputBuffer:readVC> Leggo da buffer. Stato="+vcVisitableBuffer.isEmpty());

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

    public void disconnect()
    {
        playerCommunicationInterface.closeConnection();
    }

}