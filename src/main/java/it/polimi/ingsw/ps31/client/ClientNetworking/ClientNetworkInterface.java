package it.polimi.ingsw.ps31.client.ClientNetworking;

import it.polimi.ingsw.ps31.client.view.ViewProva;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
public abstract class ClientNetworkInterface {

    public abstract void sendToServer(String msg);
    public abstract String readFromServer();


}
