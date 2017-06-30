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
import java.sql.Timestamp;

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
        System.out.println("ServerConnectionInterface:sendToClient> invio messaggio: "+msg.toString());
        writeOnNetwork(serialize(msg));
    }

    public final VCVisitable readFromClient() throws IOException
    {
        return deserialize(readFromNetwork());
    }

    private String serialize(GenericMessage genericMessage) {
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

    private VCVisitable deserialize(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getVcVisitable();
    }

    private ConnectionMessage deserializeCM(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getConnectionMessage();
    }


    public ConnectionMessage waitForConnectionMessage()
    {
        ConnectionMessage connectionMessage = null;
        try {
            System.out.println("ServerConnectionInterface:waitForConnectionMessage> inizio attesa: "+ System.currentTimeMillis());
            connectionMessage = deserializeCM(readFromNetwork());
            System.out.println("ServerConnectionInterface:waitForConnectionMessage> fine attesa: "+ System.currentTimeMillis());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connectionMessage;
    }

    public abstract void disconnect();



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
