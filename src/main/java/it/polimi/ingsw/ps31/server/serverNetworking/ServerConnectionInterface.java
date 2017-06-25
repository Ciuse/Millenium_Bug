package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.networking.ConnectionType;

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
    protected abstract String readFromNetwork();
    protected abstract void writeOnNetwork(String msg);
    public abstract String getConnectionInfo();

    public final void sendToClient(GenericMessage msg)
    {
        writeOnNetwork(serialize(msg));
    }

    public final GenericMessage readFromClient()
    {
        return deserialize(readFromNetwork());
    }

    public String serialize(Object obj) {

        //Creo gson
        Gson gson = new Gson();

        //Serializzo l'oggetto
        String strObj = gson.toJson(obj);

        return strObj;
    }

    public GenericMessage deserialize(String msg) {
        if ( msg == null )
            return null;

        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        GenericMessage strObj = gson.fromJson(msg, GenericMessage.class);

        return strObj;
    }

    //todo eliminare dopo aver messo il gson builder
    public ConnectionMessage deserializeCM(String msg) {
        if ( msg == null )
            return null;

        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        ConnectionMessage strObj = gson.fromJson(msg, ConnectionMessage.class);

        return strObj;
    }

    public void sendView(View view)
    {
        if ( view == null )
            return;

        writeOnNetwork(serialize(view));
    }

    public void setConnectionMessage(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
    }

    public void waitForConnectionMessage()
    {
        setConnectionMessage(deserializeCM(readFromNetwork()));
    }

    public boolean receivedCM()
    {
        return ( this.connectionMessage != null );
    }
}
