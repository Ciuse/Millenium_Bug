package it.polimi.ingsw.ps31.server.serverNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.ConnectionType;
import it.polimi.ingsw.ps31.networking.JsonNetworking;

import java.io.IOException;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 *
 * Classe astratta che rappresenta una comunicazione fisica con la rete. Può essere estesa per realizzare
 * connessioni usando diverse tecnologie (es. Socket o RMI). Comunica con le altre classi del server tramite
 * oggetti di tipo messaggio ed incapsula metodi per serializzarli e deserializzarli. A questo scopo sfrutta
 * oggetti di tipo ConcreteEnvelope.
 *
 * @see ConcreteEnvelope;
*/

public abstract class ServerConnectionInterface {
    private final ConnectionType connectionType;
    protected ConnectionMessage connectionMessage = null;
    private boolean closed = false;
    //private List<GenericMessage> disconnectionMessageBuffer;

    /* Constructor */
    public ServerConnectionInterface(ConnectionType connectionType)
    {
        this.connectionType = connectionType;
    }

    public ConnectionMessage getConnectionMessage(){
        return this.connectionMessage;
    }

    /**
     * Metodo astratto per leggere da rete
     * @return stringa letta
     * @throws IOException
     */
    protected abstract String readFromNetwork() throws IOException;

    /**
     * Metodo astratto per scrivere sulla rete
     * @param msg stringa da scrivere
     */
    protected abstract void writeOnNetwork(String msg);

    /**
     * Metodo astratto per chiudere la connessione fisica
     */
    protected abstract void closePhysicalConnection();
    public abstract String getConnectionInfo();

    /**
     * Metodo esposto all'esterno per inviare oggetti di tipo messaggio
     * @param msg messaggio da inviare
     */
    public final void sendToClient(GenericMessage msg)
    {
        ///System.out.println("ServerConnectionInterface:sendToClinet> msg = "+msg+"; closed="+closed);

        if( closed ){
            DebugUtility.simpleUserMessage("Si tenta di scrivere con connessione chiusa");
        }
        else
        {
            //System.out.println("ServerConnectionInterface:sendToClient> invio messaggio: "+msg.toString());
            writeOnNetwork(serialize(msg));
        }
    }

    /**
     * Metodo esposto all'esterno per leggere un VCVisitable da rete
     * @return messaggio VCVisitable letto
     * @throws IOException
     */
    public final VCVisitable readFromClient() throws IOException
    {
        if( closed )
            return null;
        return deserialize(readFromNetwork());
    }

    /**
     * Serializza un messaggio astratto dopo averlo inserito in una busta concreta (oggetti astratti
     * non possono essere serializzati da gson)
     * @param genericMessage messaggio da inviare
     * @return messaggio imbustatoe  serializzato
     */
    private String serialize(GenericMessage genericMessage) {
        //Imbusto il messaggio
        ConcreteEnvelope envelope = genericMessage.wrap();

        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Serializzo l'oggetto e ritorno il risultato
        return gson.toJson(envelope);
    }

    /**
     * Deserializza una stringa e la inserisce in un oggetto di tipo ConcreteEnvelope.
     * La stringa deve essere una serializzazione di una ConcreteEnvelope
     * @param msg serializzazione di una ConcreteEnvelope
     * @return ConcreteEnvelope contenente il messaggio deserializzato
     */
    private ConcreteEnvelope deserializeEnvelope(String msg)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo e ritorno la busta
        return gson.fromJson(msg, ConcreteEnvelope.class);
    }

    /**
     * Deserializza i messaggi di tipo VCVisitable (quasi tutti i messaggi destinati al server)
     * @param msg serializzazione di un VCVisitable
     * @return messaggio di tipo VCVisitable, null se msg è null.
     */
    private VCVisitable deserialize(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getVcVisitable();
    }

    /**
     * Deserializza il messaggio contenente il ConnectionMessage.
     * @param msg serializzazione di un ConnectionMessage
     * @return messaggio di tipo ConnectionMessage, null se msg è null o se la deserializzazione della busta
     * non contiene un ConnectionMessage
     */
    private ConnectionMessage deserializeCM(String msg) {
        if(msg == null)
            return null;

        return deserializeEnvelope(msg).getConnectionMessage();
    }

    /**
     * Metodo che si mette in ascolto sulla rete finchè non riceve un messaggio, necessariamente di
     * tipo ConnectionMessage. Se il messaggio ricevuto non contiene un ConnectionMessage, restituisce null
     * @return ConnectionMessage ricevuto dal client, null se il primo messaggio ricevuto dopo la
     * chiamata non contiene un ConnectoinMessage
     */
    public ConnectionMessage waitForConnectionMessage()
    {
        ConnectionMessage connectionMessage = null;
        try {
           // System.out.println("ServerConnectionInterface:waitForConnectionMessage> inizio attesa: "+ System.currentTimeMillis());
            connectionMessage = deserializeCM(readFromNetwork());
           // System.out.println("ServerConnectionInterface:waitForConnectionMessage> fine attesa: "+ System.currentTimeMillis());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connectionMessage;
    }

    public final void disconnect(){
        this.closed = true;
        closePhysicalConnection();
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
