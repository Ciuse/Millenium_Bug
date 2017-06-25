package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.ModelProva;

/**
 * Created by Francesco on 08/06/2017.
 */
//Classe con il compito di interfacciare le varie conessioni ad una partita con l'oggetto match corrispondente
public class NetworkInterface {
//    private List<ServerConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;
    private Match match;
    private ModelProva modelProva;
    private GameLogic gameLogic;

    /* Constructor */
    public NetworkInterface(Match match, GameLogic gameLogic){
        this.match = match;
        this.playerTable = new PlayerTable();
        this.gameLogic = gameLogic;

//        readFromClient(null, "NetworkInterface instantiated");
    }

    /* Class Methods */
    public void addConnection(ServerConnectionInterface connection)
    {
        this.playerTable.addPlayer(connection);
    }

    public int getConnectionListSize()
    {
        return this.playerTable.size();
    }

//    public GenericMessage readFromClient(PlayerId playerId)
//    {
//
//        ServerConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);
//
//        GenericMessage msgToReturn = connection.readFromClient();
//
//        modelProva.setState(msgToReturn.update(), playerId);
//        return msgToReturn;
//    }

    public void sendToClient(GenericMessage msg, PlayerId playerId)
    {
        ServerConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);
        connection.sendToClient(msg);
    }

    public void sendToAll(GenericMessage msg)
    {
        for(ServerConnectionInterface currentConnection : playerTable.getAllConnections())
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
        return playerTable.playerIdToConncetion(playerId) != null;
    }

    public void sendViewToPlayer (View view, PlayerId playerId)
    {
        ServerConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);
        connection.sendView(view);
    }
}
