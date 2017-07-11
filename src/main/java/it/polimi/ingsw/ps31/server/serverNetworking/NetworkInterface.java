package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 *
 * Classi che gestiscono le connessioni ad una singola partita con la classe Match corrispondente.
 * Rappresenta per la virtual view l'interfaccia di comunicazione verso il client e la view vera.
 * Mantiene anche traccia di tutti i messaggi inviati dall'inizio della partita, in modo da poter
 * aggiornare una view dopo una riconnessione allo stato attuale della partita.
 *
 * @see Match
 * @see PlayerCommunicationInterface
 * @see PlayerTable
 */

/**
 * Classe che rappresenta un messaggio inviato. Contiene il messaggio e il playerId del destinatario in formato int.
 */
class SentMessageEntry {
    /** PlayerId del destinatario */
    private final int receiverId;

    /** Messaggio inviato */
    private final GenericMessage message;

    /* Constructor */
    public SentMessageEntry(int receiverId, GenericMessage message) {
        this.receiverId = receiverId;
        this.message = message;
    }

    /* Getters */
    public int getReceiverId() {
        return receiverId;
    }

    public GenericMessage getMessage() {
        return message;
    }
}

/**
 * Classe con il compito di interfacciare il match con le varie conessioni alla partita.
 * Il metodo di invio è invocato dalla virtual view e ogni nuovo messaggio in ingresso
 * viene notificato alla virtual view tramite la classe Match.
 */
public class NetworkInterface {
//    private List<ServerConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;

    /** riferimento alla classe Match della partita */
    private Match match;

    /** Riferimento alla classe GameLogic, che gestisce le meccaniche di gioco */
    private GameLogic gameLogic;

    /** Riferimento alla match table. Viene usato per segnalare le disconnessioni dei client*/
    private MatchTable matchTable;

    /** Conologia dei messaggi inviati */
    private List<SentMessageEntry> messageHistory;

    /* Constructor */
    public NetworkInterface(Match match, MatchTable matchTable, GameLogic gameLogic){
        this.match = match;
        this.playerTable = new PlayerTable();
        this.gameLogic = gameLogic;
        this.matchTable = matchTable;
        this.messageHistory = new ArrayList<>();
//        readFromClient(null, "NetworkInterface instantiated");
    }

    /* Class Methods */

    /**
     * Aggiunge una connessione alla partita
     * @param connection classe di comunicazione con il client
     */
    public void addConnection(PlayerCommunicationInterface connection)
    {
        this.playerTable.addPlayer(connection);
    }

    public List<PlayerId> getPlayerIdList()
    {
        return playerTable.getPlayerIdList();
    }

    public int getConnectionListSize()
    {
        return this.playerTable.size();
    }

    /**
     * Legge un messaggio di tipo VCVisitable dal client. Se il client è diconnesso o se non ci sono nuovi messaggi
     * nella coda di attesa ritorna null. Altimenti ritorna il messaggio meno recente in attesa nel buffer
     *
     * @param playerId id del player da cui leggere
     * @return messaggio letto, null se non ce ne sono o se il player è disconnesso.
     */
    public VCVisitable readFromClient(PlayerId playerId)
    {

        PlayerCommunicationInterface communicationInterface = this.playerTable.playerIdToConnection(playerId);

        if( communicationInterface.isDisconnected() )
            return null;

        VCVisitable msgToReturn = null;
        if( communicationInterface.newMessages() )
            msgToReturn = communicationInterface.nextMessage();

        return msgToReturn;
    }

    /**
     * Metodo privato che esegue l'invio fisico di un messaggio al client.
     * @param playerId  id del player destinatario
     * @param msg messaggio da inviare
     */
    private void sendReallyToClient(PlayerId playerId, GenericMessage msg)
    {
        PlayerCommunicationInterface connection = playerTable.playerIdToConnection(playerId);
        connection.send(msg);
    }

    /**
     * Invia un messaggio MVVisitable al client salvandolo nella history
     * @param msg il messaggio da inviare
     * @param playerId id del player destinatario
     */
    public void sendToClient(MVVisitable msg, PlayerId playerId)
    {
        messageHistory.add(new SentMessageEntry(playerId.toInt(), msg));
        sendReallyToClient(playerId, msg);
    }

    /**
     * Invia un messaggio NetworkingMessage al client salvandolo nella history
     * @param msg il messaggio da inviare
     * @param playerId id del player destinatario
     */
    public void sendToClient(NetworkingMessage msg, PlayerId playerId)
    {
        DebugUtility.simpleDebugMessage("Invocato per invio messaggio");

        messageHistory.add(new SentMessageEntry(playerId.toInt(), msg));
        sendReallyToClient(playerId, msg);

    }


    /**
     * Invia un messaggio di tipo MVVisitable a tutti i player collegati, salvandolo nella cronologia
     * @param msg messaggio da inviare
     */
    public void sendToAll(MVVisitable msg)
    {
        messageHistory.add(new SentMessageEntry(0, msg));
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    /**
     * Invia un messaggio di tipo NetworkingMEssage a tutti i player collegati, salvandolo nella cronologia
     * @param msg messaggio da inviare
     */
    public void sendToAll(NetworkingMessage msg)
    {
        messageHistory.add(new SentMessageEntry(0, msg));
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    /**
     * Invia un messaggio di tipo GenericMessage senza salvarlo nella cronologia. Ciò può essere utile quando un
     * client si è appena riconnesso e deve ricevere tutta la cronologia senza che i messaggi vengano salvati
     * nella cronologia stessa ad ogni invio.
     * @param message messaggio da inviare
     * @param player id del player destinatario
     */
    private void resendGenericToClient(GenericMessage message, PlayerId player)
    {
        String porta = playerTable.playerIdToConnection(player).getConnectionInfo();
        DebugUtility.simpleDebugMessage("invio messaggio " +message+" sulla porta "+porta);
        sendReallyToClient(player, message);
    }

    /**
     * Invia l'intera cronologia di messaggi al player indicato. Alcnui tipi di messaggio non contegono aggiornamenti
     * di stato e non vengono inviati.
     * @param playerId id del player a cui inviare la cronologia
     */
    public void sendHistory(PlayerId playerId)
    {
        //Non invio nè le richieste AskChoice, nè i messaggi StringToPrint, nè i messaggi con playerId != receiverId != 0
        int intPlayerId = playerId.toInt();


        /* Inizio debug history*/
        System.out.println("NetworkInterface:sendHistory> Spedisco gli aggiornamenti della view");
        String history = "==============[LISTA MESSAGGI DA INVIARE]==============\n";
        int j = 0;
        for(SentMessageEntry currentEntry : messageHistory){
            if( (currentEntry.getReceiverId() ==  intPlayerId || currentEntry.getReceiverId() == 0) &&
                    currentEntry.getMessage().isViewUpdate()) {
                history += currentEntry.getMessage() + "\n";
                j++;
            }
        }
        history += "TOTALE MESSAGGI: "+j;
        System.out.println(history);
        /* Fine debug history */


        //Scorro la cronologia e invio i messaggi di aggiornamento
        for(SentMessageEntry currentEntry : messageHistory)
        {
            if( (currentEntry.getReceiverId() ==  intPlayerId || currentEntry.getReceiverId() == 0) &&
                    currentEntry.getMessage().isViewUpdate()) {
                resendGenericToClient(currentEntry.getMessage(), playerId);
            }
        }
    }

    public void printPlayerTable()
    {
        playerTable.printTable(this.match);
    }

    public boolean playerConnected(PlayerId playerId)
    {
        return playerTable.playerIdToConnection(playerId) != null;
    }

    public Match getMatch()
    {
        return match;
    }

    /**
     * Data una CommunicationInterface, restituisce l'id del player reggiungibile tramite essa
     * @param playerCommunicationInterface interfaccia di comunicazione di cui si vuole conoscere il playerId
     * @return playerId del player raggiungibile tramite l'interfaccia
     */
    public PlayerId connectionToPlayerId(PlayerCommunicationInterface playerCommunicationInterface)
    {
        return playerTable.connectionToPlayerId(playerCommunicationInterface);
    }

    /**
     * Dato un PlayerId, restituisce la CommunicationInterface da usare per raggiungerlo
     * @param playerId playerId  di cui si vuole conoscere l'interfaccia di comunicazione
     * @return interfaccia per raggiungere il player
     */
    public PlayerCommunicationInterface playerIdToConnection(PlayerId playerId)
    {
        return playerTable.playerIdToConnection(playerId);
    }

    public void setMatchStarted()
    {
        matchTable.notifyMatchStarted(match);
    }

    /**
     * segnala alla playerTable la disconnessione di un client
     * @param playerId player disconnesso
     */
    public void notifyPlayerDisconnection(PlayerId playerId)
    {
        playerTable.disconnectPlayer(playerId);
    }

    /**
     * Segnala alla playerTable la riconnessione di un player
     * @param communicationInterface nuova CommunicationInterface per raggiungere l'utente
     * @param playerId playerId del player che sis ta riconnesttendo
     */
    public void notifyPlayerReconnection(PlayerCommunicationInterface communicationInterface, PlayerId playerId)
    {
        playerTable.reconnectPlayer(communicationInterface, playerId);
    }

    public boolean isDisconncted(PlayerId playerId)
    {
        return playerTable.isDisconnected(playerId);
    }
}
