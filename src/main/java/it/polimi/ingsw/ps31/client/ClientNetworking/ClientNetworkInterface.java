package it.polimi.ingsw.ps31.client.ClientNetworking;

import java.io.Serializable;

/**
 * Created by Francesco on 10/06/2017.
 */
public abstract class ClientNetworkInterface {

    public abstract void writeToServer(Serializable object);
    public abstract Object readFromServer();

}
