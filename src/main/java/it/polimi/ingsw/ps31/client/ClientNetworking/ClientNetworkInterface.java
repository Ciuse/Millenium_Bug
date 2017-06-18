package it.polimi.ingsw.ps31.client.ClientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.GenericView;
import it.polimi.ingsw.ps31.client.view.ViewProva;
import it.polimi.ingsw.ps31.networking.MexProva;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
class InboundMessageBuffer {
    private List<String> buffer;

    /* Constructor */
    public InboundMessageBuffer(){
        this.buffer = new ArrayList<>();
    }

    /* Class methods */
    public void bufferizeMessage(String msg)
    {
        this.buffer.add(msg);
    }

    public String read()
    {
        if(buffer.isEmpty())
            return null;

        return buffer.remove(0);
    }

    public List<String> readAll()
    {
        if(buffer.isEmpty())
            return null;

        List<String> toReturn = new ArrayList<>();
        for(String currentMsg : buffer)
            toReturn.add(currentMsg);

        buffer.clear();

        return toReturn;
    }

    @Override
    public String toString()
    {
        int i = 1;
        String b;

        b =  "MESSAGE BUFFER - SIZE = " + buffer.size()+"\n";
        b += "=========================\n";

        for (String currentMsg : buffer)
        {
            b += "#"+i+": "+currentMsg+"\n";
            i++;
        }
        b += "=========================\n";

        return b;
    }


}

public abstract class ClientNetworkInterface {
    private final InboundMessageBuffer inboundMessageBuffer;
    private boolean switchOff = false;
    protected  GenericView genericView;


    /* Constructor */
    public ClientNetworkInterface()
    {
        inboundMessageBuffer = new InboundMessageBuffer();
    }

    /* Abstract methods */
    //Metodi astratti di accesso alla rete
    public abstract void switchOn();
    protected abstract void prepareForNetwork(String msg);
//    protected abstract String prepareForClient();
    public abstract void close();


    /* Class Methods */
    //Metodo pubblico di connessione alla view
    public void attachView(GenericView view)
    {
        this.genericView = view;
    }
    //Metodi pubblici per lettura/scrittura di oggetti sulla rete
    //Scrittura sulla rete. Invoca il metodo astratto corrispondente
    public final void sendToServer(MexProva msg)
    {
        prepareForNetwork(serialize(msg));
    }

    //Lettura bloccante sul buffer. Legge il buffer finchè non restituisce un messaggio non nullo
    public final MexProva readFromBuffer()
    {
        String msgToReturn;
        do
        {
            msgToReturn = this.inboundMessageBuffer.read();
        }while(msgToReturn == null);

        return deserialize(msgToReturn);

    }

    //Metodi protected di accesso al buffer
    protected final void fromNetworkToBuffer(String str)
    {
        inboundMessageBuffer.bufferizeMessage(str);
    }

    public final String printBuffer()
    {
        return inboundMessageBuffer.toString();
    }

    //Metodi privati di serializzazione/deserializzazione
    private String serialize(Object obj) {

        //Creo gson
        Gson gson = new Gson();

        //Serializzo l'oggetto
        String strObj = gson.toJson(obj);

        return strObj;
    }

    private MexProva deserialize(String msg) {
        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        MexProva strObj = gson.fromJson(msg, MexProva.class);

        return strObj;
    }

    protected void switchOff()
    {
        this.switchOff = true;
    }

    public boolean isSwitchOff()
    {
        return this.switchOff;
    }
}
