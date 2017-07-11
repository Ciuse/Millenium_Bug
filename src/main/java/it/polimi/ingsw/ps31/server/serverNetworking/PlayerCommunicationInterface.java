package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Francesco on 30/06/2017.
 * Interfaccia di comunicazione verso il singolo client. Rappresenta, per la networkInterface, il gateway di
 * comunicazione verso il client che si vuole raggiungere. Incapsula la connessione fisica e espone metodi di
 * I/O dei messaggi e di gestione delle disconnessioni. Contiene un buffer per i messaggi ricevuti, in modo
 * da lasciare libera la connessione fisica per futuri messaggi in arrivo dalla rete.
 *
 * @see NetworkInterface
 * @see ServerConnectionInterface
 * @see ServerListeningThread
 * @see ServerInputBuffer
 */
public class PlayerCommunicationInterface {
    /** Connessione fisica alla rete*/
    private ServerConnectionInterface serverConnectionInterface;

    /** Buffer per i messaggi in ingresso */
    private ServerInputBuffer serverInputBuffer;

    /** booleano che indica se ci sono nuovi messagg non letti */
    private boolean newMessages;

    /** booleano che indica se il client collegato è disconnesso*/
    private boolean disconnected;

    /** Thread di continua lettura dei messagi in ingresso sulla connessione */
    private ServerListeningThread listeningThread;

    private PlayerId playerId;

    /** ConnectionMessage del client collegato
     * @see ConnectionMessage
     */
    private ConnectionMessage connectionMessage;

    /** Riferimento alla matchTable. Usato per gestire le disconnessioni */
    private MatchTable matchTable;

//    /** Lista dei messaggi in uscita ma non inviati perchè il client era disconnesso.
//     * Verranno inviati alla riconnessione */
//    private List<GenericMessage> pendingMessages;

    /* Constructor */
    public PlayerCommunicationInterface(ServerConnectionInterface serverConnectionInterface, MatchTable matchTable)
    {
        //Inizializzo attributi di base
        this.newMessages = false;
        this.disconnected = false;
//        this.pendingMessages = new ArrayList<>();

        //Creo l'infrastruttura di comunicazione con la rete
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = new ServerInputBuffer(this);
        listeningThread = new ServerListeningThread(serverConnectionInterface, serverInputBuffer);

        this.matchTable = matchTable;
    }

    /** Inizia l'ascolto della connessione fisica*/
    public void switchOn()
    {
        listeningThread.start();
    }

//    public void setPlayerTable(PlayerTable playerTable)
//    {
//        PlayerTable playerTable1 = playerTable;
//    }

    public void notifyNewMessages()
    {
        this.newMessages = true;
    }

    public boolean newMessages()
    {
        return this.newMessages;
    }

    /**
     * @return il messaggio meno recente presente nel buffer, rimuovendolo
     * */
    public VCVisitable nextMessage()
    {
        if ( !newMessages )
            return null;

        VCVisitable message = serverInputBuffer.readVC();
        if ( serverInputBuffer.isEmpty() )
            this.newMessages = false;

        return message;
    }

    /**
     * Invia un GenericMessage al client.
     * @param msg il messaggio da inviare
     */
    public void send(GenericMessage msg)
    {
        if( msg != null )
        {
            if( disconnected ) {
                //pendingMessages.add(msg);
            }
            else
                serverConnectionInterface.sendToClient(msg);
        }
    }

    /**
     * Disconnette il client
     */
    public void closeConnection() {
        this.disconnected = true;
        matchTable.disconnectClient(this);
    }

    /**
     * Procedura di riconnessione del player. in questo caso this indica la nuova PlayerCommunicationInterface
     * @param oldConnection vecchia PlayerCommunicationInterface
     */
    public void reopenClosedConnection(PlayerCommunicationInterface oldConnection)
    {
//        //copio nella nuova communicationInterface(this) i pending messages di quella vecchia (oldConnection)
//        setPendingMessages(oldConnection.getPendingMessages());
//
//        //invio al client i pending messages appena copiati
//        for(GenericMessage currentMessage : pendingMessages)
//        {
//            serverConnectionInterface.sendToClient(currentMessage);
//        }

        switchOn();
    }

//    private List<GenericMessage> getPendingMessages()
//    {
//        return this.pendingMessages;
//    }
//
//    private void setPendingMessages(List<GenericMessage> oldList)
//    {
//        this.pendingMessages = new ArrayList<>(oldList);
//    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public void setConnectionMessage(ConnectionMessage connectionMessage)
    {
        if( connectionMessage == null )
            return; //significa che il client si è disconnesso nel momento peggiore
        this.connectionMessage = connectionMessage;
    }

//    public boolean receivedCM()
//    {
//        return (this.connectionMessage != null);
//    }

    public ConnectionMessage getConnectionMessage()
    {
        return this.connectionMessage;
    }

    public boolean isDisconnected()
    {
        return disconnected;
    }

    @Override
    public String toString()
    {
        String res = "USERNAME = " + this.connectionMessage.getUsername() + "; " +
                     "PASSWORD = " + this.connectionMessage.getPassword() + "; " +
                     "Porta = "    + serverConnectionInterface.getConnectionInfo();

        return res;
    }

    public String getConnectionInfo()
    {
        return serverConnectionInterface.getConnectionInfo();
    }
}
