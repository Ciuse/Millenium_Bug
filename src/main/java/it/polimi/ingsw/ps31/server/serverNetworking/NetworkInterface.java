package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.ModelProva;

import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
class DisconnectedPlayer{
    private final PlayerId playerId;
    private final String username;
    private final String password;

    public DisconnectedPlayer(PlayerId playerId, String username, String password)
    {
        this.playerId = playerId;
        this.username = username;
        this.password = password;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

//Classe con il compito di interfacciare le varie conessioni ad una partita con l'oggetto match corrispondente
public class NetworkInterface {
//    private List<ServerConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;
    private Match match;
    private ModelProva modelProva;
    private GameLogic gameLogic;
    private MatchTable matchTable;

    /* Constructor */
    public NetworkInterface(Match match, MatchTable matchTable, GameLogic gameLogic){
        this.match = match;
        this.playerTable = new PlayerTable();
        this.gameLogic = gameLogic;
        this.matchTable = matchTable;

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

    public void sendToClient(MVVisitable msg, PlayerId playerId)
    {

        //System.out.println("NetworkInterface : sendtoClient()> player: "+playerId+"; msg: "+msg.toString());
        PlayerCommunicationInterface connection = this.playerTable.playerIdToConnection(playerId);
        connection.send(msg);
    }

    public void sendToClient(NetworkingMessage msg, PlayerId playerId)
    {
        PlayerCommunicationInterface connection = this.playerTable.playerIdToConnection(playerId);
        System.out.println("NetworkInterface : sendtoClient()> player: "+playerId+"; msg: "+msg.toString());
        connection.send(msg);
    }

    public void sendToAll(MVVisitable msg)
    {
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    public void sendToAll(NetworkingMessage msg)
    {
        for(PlayerCommunicationInterface currentConnection : playerTable.getAllConnections())
            currentConnection.send(msg);
    }

    public void setModelProva(ModelProva modelProva)
    {
        this.modelProva = modelProva;
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
}
