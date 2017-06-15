package it.polimi.ingsw.ps31.client.ClientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.ViewProva;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Francesco on 10/06/2017.
 */
class InboundMessageBuffer {
    private List<String> buffer;

    /* Constructor */
    public InboundMessageBuffer(){
        this.buffer = new ArrayList<>();
    }

    /* Class methods */
    public void bufferizeMessage(String msg)
    {
        System.out.println("Client socket> bufferizzo messaggio : "+msg);
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

public class ClientSocketConnection extends ClientNetworkInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;
    private  ViewProva viewProva;
    private final InboundMessageBuffer inboundMessageBuffer;
    private boolean terminateSocketListening = false;
    private boolean closed = false;

    /* Constructor */
    public ClientSocketConnection(int port) throws IOException {

        //Creo la socket e la collego al server
        this.socket = new Socket("127.0.0.1", port);

        //Creo il reader da socket
        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);

        //creo il buffer di messaggi
        this.inboundMessageBuffer = new InboundMessageBuffer();
    }

    private String readFromSocket()
    {
        String msgToReturn = null;

        try
        {
            msgToReturn = this.socketReader.readLine();
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return msgToReturn;
    }

    @Override
    public void switchOn()
    {
        String inputFromServer;
        System.out.println("Client> thread attivato. Inizio ascolto");
        while(! this.terminateSocketListening)
        {
            inputFromServer = readFromSocket();
            if ( inputFromServer != null )
            {

                //System.out.println("Client> ciclo di lettura : "+inputFromServer);
                inboundMessageBuffer.bufferizeMessage((String) deserialize(inputFromServer)); //todo sistemare oggetti/casting
            }
        }
    }

    @Override
    public void attachView(ViewProva viewProva)
    {
        this.viewProva = viewProva;
    }

    @Override
    public void sendToServer(String msg)
    {
        msg = serialize(msg);

        try {

            socketWriter.write(msg+"\n");
            socketWriter.flush();
            //socketWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Lettura bloccante sul buffer. Legge il buffer finchè non restituisce un messaggio non nullo
    public String readFromBuffer()
    {
        String msgToReturn;

        do
        {
            msgToReturn = this.inboundMessageBuffer.read();
        }while(msgToReturn == null);

        return msgToReturn;
    }

    @Override
    public String serialize(Object obj) {

        //Creo gson
        Gson gson = new Gson();

        //Serializzo l'oggetto
        String strObj = gson.toJson(obj);

        return strObj;
    }

    @Override
    public Object deserialize(String msg) {
        return msg;//todo usare codice commentato qui sotto
        /*
        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        MexProva strObj = gson.fromJson(msg, MexProva.class);

        return strObj;
        */
    }

    public void stopListening()
    {
        this.terminateSocketListening = true;
    }

    @Override
    public synchronized void close() {

        //Evita l'invocazione del metodo se la connessione è già stata chiusa
        if( this.closed )
            return;

        System.out.println("ClientSC> closing socket");
        try
        {
            String serverAnswer;
            do
            {
                socketWriter.write("close");
                serverAnswer = readFromBuffer();

            } while(!serverAnswer.equals("closeAck"));


            //Spengo l'ascoltatore della socket
            stopListening();

            //Chiudo stream I/O e la socket
            socketReader.close();
            socketWriter.close();
            socket.close();

            //Marco la connessione come chiusa
            this.closed = true;

            System.out.println("ClientSC> connection closed");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String printBuffer()
    {
        return inboundMessageBuffer.toString();
    }


}
