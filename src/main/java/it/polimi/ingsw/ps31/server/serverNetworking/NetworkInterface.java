package it.polimi.ingsw.ps31.server.serverNetworking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
//Classe con il compito di interfacciare le varie conessioni ad una partita con l'oggetto match corrispondente
public class NetworkInterface {
    private List<ConnectionInterface> connectionInterfaces = new ArrayList<>();

    /* Constructor */
    public NetworkInterface(){

    }

    /* Class Methods */
    public void addConnection(ConnectionInterface connection)
    {
        this.connectionInterfaces.add(connection);
    }

    public int getConnectionListSize()
    {
        return this.connectionInterfaces.size();
    }

}
