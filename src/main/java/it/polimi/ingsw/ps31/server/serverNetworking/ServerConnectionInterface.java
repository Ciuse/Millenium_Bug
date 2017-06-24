package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.AskConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.networking.ConnectionType;

/**
 * Created by Francesco on 08/06/2017.
*/

public abstract class ServerConnectionInterface {
    private final ConnectionType connectionType;
    protected ConnectionMessage connectionMessage;

    /* Constructor */
    public ServerConnectionInterface(ConnectionType connectionType)
    {
        this.connectionType = connectionType;

        //chiedo al player di inviare la ConnectionMessage
        notifyClient(new AskConnectionMessage());

        //leggo sulla socket il ConnectionMessage
        this.connectionMessage = deserializeCM(readFromNetwork());

    }

    public ConnectionMessage getConnectionMessage(){
        return this.connectionMessage;
    }
    protected abstract String readFromNetwork();
    protected abstract void sendToNetwork(String msg);
    public abstract String getConnectionInfo();

    public final void notifyClient(GenericMessage msg)
    {
        sendToNetwork(serialize(msg));
    }

    public final GenericMessage notifyModel()
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

        sendToNetwork(serialize(view));
    }
}
