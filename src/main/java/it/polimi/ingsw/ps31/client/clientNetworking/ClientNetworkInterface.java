package it.polimi.ingsw.ps31.client.clientNetworking;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
public abstract class ClientNetworkInterface {

    public abstract void sendToServer(String msg);
    public abstract String readFromServer();
    public abstract String serialize(Object obj);
    public abstract Object deserialize(String msg);

}
