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
 *
 * Classe astratta che rappresenta la connessione del client con la rete. Cotiene metodi protected per
 * la serializzazione e la deserializzazione di messaggi ed espone all'esterno metodi di I/O.
 * Unifica connessioni che sfruttano diverse tecnologia sotto un'unica interfaccia di funzionamento.
 */
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

    /**
     * Imposta il riferimento alla classe di notifica della view
     */
    public void setClientMessageHistory(ClientMessageHistory clientMessageHistory)
    {
        this.clientMessageHistory = clientMessageHistory;
    }

    /**
     * Avvia il thread di lettura da rete
     */
    public void startReading()
    {
        this.clientReadingThread = new ClientReadingThread(this, clientMessageHistory);
        clientReadingThread.start();
    }

    /**
     *  Invia un messaggio di tipo NetworkingMessage
     * @param msg NetworkingMessage da inviare
     */
    public final void sendToServer(NetworkingMessage msg){
        writeOnNetwork(serialize(msg));
    }

    /**
     *  Invia un messaggio di tipo VCVisitable
     * @param msg messaggio VCVisitable da inviare
     */
    public final void sendToServer(VCVisitable msg){
        writeOnNetwork(serialize(msg));
    }


    /**
     *  Legge un messaggio proveninete dal server, lo deserializza e lo restituisce
     *
     * @param returnIfNull indica se bisogna restare in attesa(false) di un messaggio o
     *                     ritornare subito(true) in caso non ci siano messaggi da leggere
     * @return messaggio letto
     * @throws IOException
     */
    public final MVVisitable readFromServer(boolean returnIfNull) throws IOException {
        return deserialize(readFromNetwork(returnIfNull));
    }

    /**
     * Aspetta un messaggio di tipo ViewMessage dal server.
     * Si suppone che il primo messaggio ricevuto dal server dopo l'invocazione di questo metodo
     * sia esattamente di tipo ViewMessage
     * @return  il messaggio ViewMessage ricevuto
     * @throws IOException
     */
    public final ViewMessage readViewMessageFromServer() throws IOException {
        return deserializeVM(readFromNetwork(false));
    }

    /**
     * Sfrutta gson per serializzare un messaggio di tipo GenericMessage per
     * poterlo inviare sulla rete, dopo averlo inserito in una ConcreteEnvelope
     * @param genericMessage il messaggio da inviare
     * @return il messaggio serializzato
     */
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

    /**
     * Deserializza, tramite gson, una stringa ricevuta dalla rete e restituise la ConcreteEnvelope che
     * contiene il messaggio inviato dal server
     * @param msg la stinga da deserializzare
     * @return la ConcreteEnvelope contenente il messaggio ricevuto
     */
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

    /**
     * Metodo astratto di output sulla rete
     * @param msgStr stringa da scrivere
     */
    protected abstract void writeOnNetwork(String msgStr);

    /**
     * Metodo astratto di input da rete
     * @param returnIfNull indica se bisogna restare in attesa(false) di un messaggio o
     *                     ritornare subito(true) in caso non ci siano messaggi da leggere
     * @return la stringa letta
     * @throws IOException
     */
    protected abstract String readFromNetwork(boolean returnIfNull) throws IOException;
}