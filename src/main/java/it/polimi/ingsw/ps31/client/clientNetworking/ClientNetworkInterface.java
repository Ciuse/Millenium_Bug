package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.ClientReadingThread;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.JsonNetworking;

import java.io.IOException;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
public abstract class ClientNetworkInterface {
    protected boolean viewReceived = false;
    protected final ConnectionMessage connectionMessage;
    private ClientReadingThread clientReadingThread;
    private ClientMessageHistory clientMessageHistory;

    /* Constructor */
    public ClientNetworkInterface(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
    }

    public void setClientMessageHistory(ClientMessageHistory clientMessageHistory)
    {
        this.clientMessageHistory = clientMessageHistory;
    }

    public void startReading()
    {
        this.clientReadingThread = new ClientReadingThread(this, clientMessageHistory);
        clientReadingThread.start();
    }

    //invia un messaggio in un oggetto, serializzandolo
    public final void sendToServer(NetworkingMessage msg){
        writeOnNetwork(serialize(msg));
    }

    //invia un messaggio di tipo VCVisitable
    public final void sendToServer(VCVisitable msg){
        writeOnNetwork(serialize(msg));
    }

    //legge un messaggio proveninete dal server, lo deserializza e lo restituisce
    public final MVVisitable readFromServer(boolean returnIfNull) throws IOException {
        return deserialize(readFromNetwork(returnIfNull));
    }

    public final ViewMessage readViewMessageFromServer() throws IOException {
        return deserializeVM(readFromNetwork(false));
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
        if ( msg == null )
            return null;

        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo e ritorno la busta
        return gson.fromJson(msg, ConcreteEnvelope.class);
    }

    private MVVisitable deserialize(String msg){
        if( msg == null )
            return null;

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

    public void setClientReadingThread(ClientReadingThread clientReadingThread)
    {
        this.clientReadingThread = clientReadingThread;
    }

    /* Abstract Methods */
    protected abstract void writeOnNetwork(String msgStr);
    protected abstract String readFromNetwork(boolean returnIfNull) throws IOException;
}