package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.ConnectionType;
import it.polimi.ingsw.ps31.networking.JsonNetworking;

import java.io.IOException;

/**
 * Created by Francesco on 08/06/2017.
*/

public abstract class ServerConnectionInterface {
    private final ConnectionType connectionType;
    protected ConnectionMessage connectionMessage = null;

    /* Constructor */
    public ServerConnectionInterface(ConnectionType connectionType)
    {
        this.connectionType = connectionType;
    }

    public ConnectionMessage getConnectionMessage(){
        return this.connectionMessage;
    }
    protected abstract String readFromNetwork() throws IOException;
    protected abstract void writeOnNetwork(String msg);
    public abstract String getConnectionInfo();

    public final void sendToClient(GenericMessage msg)
    {
        writeOnNetwork(serialize(msg));
    }

    public final VCVisitable readFromClient() throws IOException
    {
        return deserialize(readFromNetwork());
    }

    public String serialize(GenericMessage genericMessage) {
        //Imbusto il messaggio
        ConcreteEnvelope envelope = genericMessage.wrap();

        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Serializzo l'oggetto e ritorno il risultato
        return gson.toJson(envelope);
    }

    private ConcreteEnvelope deserializeEnvelope(String msg)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo e ritorno la busta
        return gson.fromJson(msg, ConcreteEnvelope.class);
    }

    public VCVisitable deserialize(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getVcVisitable();
    }

    public ConnectionMessage deserializeCM(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getConnectionMessage();
    }


    public void setConnectionMessage(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
    }

    public abstract void disconnect();

    public void waitForConnectionMessage()
    {
        try {
            setConnectionMessage(deserializeCM(readFromNetwork()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receivedCM()
    {
        return ( this.connectionMessage != null );
    }

    @Override
    public boolean equals(Object o)
    {
        return ( this==o );
    }
}
