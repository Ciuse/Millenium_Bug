package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.networking.ConnectionState;
import it.polimi.ingsw.ps31.networking.ConnectionType;
import it.polimi.ingsw.ps31.networking.MexProva;

/**
 * Created by Francesco on 08/06/2017.
*/

public abstract class ServerConnectionInterface {
    private String username;
    private String password;
    private ConnectionType connectionType;
    private ConnectionState connectionState;
    private boolean active = false;

    /* Constructor */
    public ServerConnectionInterface(ConnectionType connectionType/*, String username, String password*/)
    {
//        this.username=username;
//        this.password=password;
        this.connectionType = connectionType;
        this.connectionState = ConnectionState.CONNECTED;
    }

    // I successivi due metodi, invocati dall'esterno per leggere e scrivere sulla connessione, sono final
    // in modo che tutte le sottoclassi concrete siano costrette a seralizzare e deserializzare i messaggi
    // prima di leggerli o scriverli
    public final MexProva notifyModel()
    {
         String msgStr = readMsgFromNetwork();

         return deserialize(msgStr);
    }

    public final void notifyClient(MexProva msgObj)
    {
        String msg = serialize(msgObj);
        sendMsgToNetwork(msg);
    }

    protected String serialize(Object obj) {

        //Creo gson
        Gson gson = new Gson();

        //Serializzo l'oggetto
        String strObj = gson.toJson(obj);

        return strObj;
    }

    protected MexProva deserialize(String msg) {
        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        MexProva strObj = gson.fromJson(msg, MexProva.class);

        return strObj;

    }

    public void setConnectionState(ConnectionState connectionState)
    {
        this.connectionState = connectionState;
    }

    public ConnectionState getConnectionState()
    {
        return this.connectionState;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    protected abstract String readMsgFromNetwork();
    protected abstract void sendMsgToNetwork(String msg);

    public abstract String getConnectionInfo();
    public abstract void close();
}
