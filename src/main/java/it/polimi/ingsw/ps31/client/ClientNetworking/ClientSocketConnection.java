package it.polimi.ingsw.ps31.client.ClientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.GenericView;
import it.polimi.ingsw.ps31.client.view.ViewProva;
import it.polimi.ingsw.ps31.networking.MexProva;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ClientSocketConnection extends ClientNetworkInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;
    private boolean terminateSocketListening = false;
    private boolean closed = false;

    /* Constructor */
    public ClientSocketConnection(int port) throws IOException {

        super();

        //Creo la socket e la collego al server
        this.socket = new Socket("127.0.0.1", port);

        //Creo il reader da socket
        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);
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
        while(! this.terminateSocketListening)
        {
            inputFromServer = readFromSocket();
            if ( inputFromServer != null )
            {
                //System.out.println("Client> ciclo di lettura : "+inputFromServer);
                super.fromNetworkToBuffer(inputFromServer);
            }
        }
    }

    @Override
    protected void prepareForNetwork(String msg)
    {
        //scrive sulla rete la stringa msg
        try
        {
            socketWriter.write(msg+"\n");
            socketWriter.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

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
                super.sendToServer(new MexProva("close"));
                serverAnswer = super.readFromBuffer().visit();

            } while(!serverAnswer.equals("closeAck"));

            //Spengo l'ascoltatore della socket
            stopListening();

            //Chiudo stream I/O e la socket
            socketReader.close();
            socketWriter.close();
            socket.close();

            //Indico la connessione come chiusa
            this.closed = true;

            System.out.println("ClientSC> connection closed");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Spengo l'interfaccia --> il thread termina
        super.switchOff();
    }


}
