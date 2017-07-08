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
 */
class SentMessageEntry {
    private final int receiverId;
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

//Classe con il compito di interfacciare le varie conessioni ad una partita con l'oggetto match corrispondente
public class NetworkInterface {
//    private List<ServerConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;
    private Match match;
    private GameLogic gameLogic;
    private MatchTable matchTable;
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

    private void sendReallyToClient(PlayerId playerId, GenericMessage msg)
    {
        PlayerCommunicationInterface connection = playerTable.playerIdToConnection(playerId);
        connection.send(msg);
    }

    public void sendToClient(MVVisitable msg, PlayerId playerId)
    {
        messageHistory.add(new SentMessageEntry(playerId.toInt(), msg));
        sendReallyToClient(playerId, msg);
    }

    public void sendToClient(NetworkingMessage msg, PlayerId playerId)
    {
        DebugUtility.simpleDebugMessage("Invocato per invio messaggio");

        messageHistory.add(new SentMessageEntry(playerId.toInt(), msg));
        sendReallyToClient(playerId, msg);

    }

    public void sendHistoryToClient(GenericMessage msg, PlayerId playerId)
    {
        sendReallyToClient(playerId, msg);
    }

    public void sendToAll(MVVisitable msg)
    {
        messageHistory.add(new SentMessageEntry(0, msg));
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    public void sendToAll(NetworkingMessage msg)
    {
        messageHistory.add(new SentMessageEntry(0, msg));
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    private void resendGenericToClient(GenericMessage message, PlayerId player)
    {
        String porta = playerTable.playerIdToConnection(player).getConnectionInfo();
        DebugUtility.simpleDebugMessage("invio messaggio " +message+" sulla porta "+porta);
        sendHistoryToClient(message, player);
    }

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

    public PlayerId connectionToPlayerId(PlayerCommunicationInterface playerCommunicationInterface)
    {
        return playerTable.connectionToPlayerId(playerCommunicationInterface);
    }

    public PlayerCommunicationInterface playerIdToConnection(PlayerId playerId)
    {
        return playerTable.playerIdToConnection(playerId);
    }

    public void setMatchStarted()
    {
        matchTable.notifyMatchStarted(match);
    }
    
    public void notifyPlayerDisconnection(PlayerId playerId)
    {
        playerTable.disconnectPlayer(playerId);
    }

    public void notifyPlayerReconnection(PlayerCommunicationInterface communicationInterface, PlayerId playerId)
    {
        playerTable.reconnectPlayer(communicationInterface, playerId);
    }

    public boolean isDisconncted(PlayerId playerId)
    {
        return playerTable.isDisconnected(playerId);
    }
}
