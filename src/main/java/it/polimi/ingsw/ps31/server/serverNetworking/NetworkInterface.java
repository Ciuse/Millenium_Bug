package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.ModelProva;

/**
 * Created by Francesco on 08/06/2017.
 */
//Classe con il compito di interfacciare le varie conessioni ad una partita con l'oggetto match corrispondente
public class NetworkInterface {
//    private List<ConnectionInterface> connectionInterfaces = new ArrayList<>();
    private PlayerTable playerTable;
    private Match match;
    private ModelProva modelProva;

    /* Constructor */
    public NetworkInterface(Match match){
        this.match = match;
        this.playerTable = new PlayerTable();

//        notifyModel(null, "NetworkInterface instantiated");
    }

    /* Class Methods */
    public void addConnection(ConnectionInterface connection)
    {
        this.playerTable.addPlayer(connection);
    }

    public int getConnectionListSize()
    {
        return this.playerTable.size();
    }

    public String readFromClient(PlayerId playerId)
    {

        ConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);

        String msgToReturn = connection.notifyModel();

        modelProva.setState(msgToReturn, playerId);
        return msgToReturn;
    }

    public void sendToClient(String msg, PlayerId playerId)
    {
        ConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);
        connection.notifyClient(msg);
    }

    public void sendToClient(GenericMessage msg, PlayerId playerId)
    {
        //Trasformo l'oggetto in json
        //Creo gson
        Gson gson = new Gson();

        //Serializzo l'oggetto
        String strMsg = gson.toJson(msg);

        ConnectionInterface connection = this.playerTable.playerIdToConncetion(playerId);
        connection.notifyClient(strMsg);
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

}
