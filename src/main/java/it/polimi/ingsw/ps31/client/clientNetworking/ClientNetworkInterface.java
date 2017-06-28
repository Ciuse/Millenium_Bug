package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.JsonNetworking;
import javafx.scene.input.GestureEvent;

import static java.lang.Thread.sleep;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
public abstract class ClientNetworkInterface {
    protected boolean viewReceived = false;
    protected final ConnectionMessage connectionMessage;

    /* Constructor */
    public ClientNetworkInterface(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
        //this.view = firstConnectionProcedure(this.connectionMessage);
    }

    //invia un messaggio in un oggetto, serializzandolo
    public final void sendToServer(ConnectionMessage msg){
        writeOnNetwork(serialize(msg));
    }

    //invia un messaggio di tipo VCVisitable
    public final void sendToServer(VCVisitable msg){
        writeOnNetwork(serialize(msg));
    }

    //legge un messaggio proveninete dal server, lo deserializza e lo restituisce
    public final MVVisitable readFromServer(){
        return deserialize(readFromNetwork());
    }

    public final ViewMessage readViewMessageFromServer()
    {
        return deserializeVM(readFromNetwork());
    }

    private String serialize(GenericMessage genericMessage)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Serializzo l'oggetto
        String strObj = gson.toJson(genericMessage);

        return strObj;
    }

    private MVVisitable deserialize(String msg){
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo l'oggetto
        MVVisitable strObj = gson.fromJson(msg, MVVisitable.class);

        return strObj;
    }

    private ViewMessage deserializeVM(String msg)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo l'oggetto
        ViewMessage strObj = gson.fromJson(msg, ViewMessage.class);

        return strObj;

    }

    public void sendConnectionMessage()
    {
        sendToServer(this.connectionMessage);
    }

    /* Abstract Methods */
    protected abstract void writeOnNetwork(String msgStr);
    protected abstract String readFromNetwork();
}