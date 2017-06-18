package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.model.GenericModel;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.networking.ConnectionState;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.networking.MexProva;

/**
 * Created by Francesco on 08/06/2017.
 */
//Classe con il compito di interfacciare le varie connessioni ad una partita con l'oggetto match corrispondente
public class ServerNetworkInterface {
//    private List<ServerConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;
    private Match match;
    private GenericModel modelProva;

    /* Constructor */
    public ServerNetworkInterface(Match match){
        this.match = match;
        this.playerTable = new PlayerTable();

//        notifyModel(null, "ServerNetworkInterface instantiated");
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

    public MexProva readFromClient(PlayerId playerId)
    {

        ServerConnectionInterface connection = this.playerTable.playerIdToConnection(playerId);

        MexProva msgToReturn = connection.notifyModel();
        System.out.println("Server> Messaggio ricevuto: '"+msgToReturn+"'.");

        if(msgToReturn != null && ( msgToReturn.visit().equals("close") || msgToReturn.visit().equals("SuddenlyClosedConnection")))
        {
            System.out.println("\nserverNI : readFromClient()> exiting...\n");

            msgToReturn = new MexProva("closedConnection");
            sendToClient(new MexProva("closeAck"), playerId);
        }

        return msgToReturn;
    }

    public void sendToClient(MexProva msg, PlayerId playerId)
    {
        ServerConnectionInterface connection = this.playerTable.playerIdToConnection(playerId);
        connection.notifyClient(msg);
    }

    public void setModelProva(GenericModel modelProva)
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

    public void closeAll()
    {
        playerTable.disconnectAll();
    }

    public void setPlayerAfk(PlayerId playerId)
    {
        playerTable.playerIdToConnection(playerId).setConnectionState(ConnectionState.AFK);
    }

    public void setPlayerDisconnected(PlayerId playerId)
    {
        playerTable.playerIdToConnection(playerId).setConnectionState(ConnectionState.DISCONNECTED);
    }

    public void setPlayerConnected(PlayerId playerId)
    {
        playerTable.playerIdToConnection(playerId).setConnectionState(ConnectionState.CONNECTED);
    }

}
