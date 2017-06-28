package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.player.ProductionList;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.ModelProva;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private List<DisconnectedPlayer> disconnectedPlayers;

    /* Constructor */
    public NetworkInterface(Match match, MatchTable matchTable, GameLogic gameLogic){
        this.match = match;
        this.playerTable = new PlayerTable();
        this.gameLogic = gameLogic;
        this.matchTable = matchTable;
        this.disconnectedPlayers = new ArrayList<>();

//        readFromClient(null, "NetworkInterface instantiated");
    }

    /* Class Methods */
    public void addConnection(ServerConnectionThread connection)
    {
        this.playerTable.addPlayer(connection);
        connection.setNetworkInterface(this);
    }

    public List<PlayerId> getPlayerIdList()
    {
        return playerTable.getPlayerIdList();
    }

    public int getConnectionListSize()
    {
        return this.playerTable.size();
    }

    public GenericMessage readFromClient(PlayerId playerId)
    {

        ServerConnectionThread connection = this.playerTable.playerIdToConnection(playerId);

        GenericMessage msgToReturn = null;
        try {
            msgToReturn = connection.readFromClient();
        } catch (IOException e) {
            //matchTable.disconnectClient(connection, match);
            e.printStackTrace();//todo disconnetti client
        }

//        modelProva.setState(msgToReturn.update(), playerId);

        return msgToReturn;
    }

    public void sendToClient(GenericMessage msg, PlayerId playerId)
    {
        System.out.println("NetworkInterface : sendtoClient()> player: "+playerId+"; msg: "+msg.toString());
        ServerConnectionThread connection = this.playerTable.playerIdToConnection(playerId);
        connection.sendToClient(msg);
    }

    public void sendToAll(MVVisitable msg)
    {
        for(ServerConnectionThread currentConnection : playerTable.getAllConnections())
            currentConnection.sendToClient(msg);
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


    public void disconnectPlayer(PlayerId playerId)
    {
        ServerConnectionThread serverConnectionInterface = playerTable.playerIdToConnection(playerId);
        String username = serverConnectionInterface.
                getConnectionMessage().
                getUsername();
        String password = serverConnectionInterface.getConnectionMessage().getPassword();

        disconnectedPlayers.add(new DisconnectedPlayer(playerId, username, password));
        playerTable.disconnectPlayer(playerId);
    }

    public boolean reconnectPlayer(ServerConnectionThread connection, PlayerId playerId)
    {
        if ( disconnectedPlayers.isEmpty() )
            return false;

        String username = connection.getConnectionMessage().getUsername();
        String password = connection.getConnectionMessage().getPassword();

        int i = 0;
        boolean exit = false;
        while ( i<disconnectedPlayers.size() && !exit )
            if ( disconnectedPlayers.get(i).getUsername().equals(username) &&
                 disconnectedPlayers.get(i).getPassword().equals(password) )
            {
                disconnectedPlayers.remove(i);
                exit = true;
                playerTable.reconnectPlayer(connection, playerId);
            }


        return exit;

    }

    public Match getMatch()
    {
        return match;
    }

    public PlayerId connectionToPlayerId(ServerConnectionThread serverConnectionThread)
    {
        return playerTable.connectionToPlayerId(serverConnectionThread);
    }
}
