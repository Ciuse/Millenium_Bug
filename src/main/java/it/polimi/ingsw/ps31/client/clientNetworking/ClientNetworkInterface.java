package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.soap.Envelope;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.DeliverableMessageType;
import it.polimi.ingsw.ps31.networking.JsonNetworking;
import org.omg.CORBA.StringHolder;

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

        //Imbusto il messaggio
        ConcreteEnvelope envelope = genericMessage.wrap();

        //Serializzo la busta
        String strEnv = gson.toJson(envelope);

        return strEnv;
    }

    private ConcreteEnvelope deserializeEnvelope(String msg)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo e ritorno la busta
        return gson.fromJson(msg, ConcreteEnvelope.class);
    }

    private MVVisitable deserialize(String msg){
        return deserializeEnvelope(msg).getMvVisitable();
    }

    private ViewMessage deserializeVM(String msg)
    {
        return deserializeEnvelope(msg).getViewMessage();

    }

    public void sendConnectionMessage()
    {
        sendToServer(this.connectionMessage);
    }

    /* Abstract Methods */
    protected abstract void writeOnNetwork(String msgStr);
    protected abstract String readFromNetwork();
}