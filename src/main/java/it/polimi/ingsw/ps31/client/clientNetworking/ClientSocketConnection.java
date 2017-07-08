package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import sun.security.ssl.Debug;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

import static com.sun.org.apache.xerces.internal.dom.DOMNormalizer.abort;
import static java.lang.Thread.sleep;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ClientSocketConnection extends ClientNetworkInterface {
    private Socket socket;
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private final int port;

    /* Constructor */
    public ClientSocketConnection(int port, ConnectionMessage connectionMessage) throws IOException {
        super(connectionMessage);
        this.port = port;

        //Creo la socket e la collego al server
        this.socket = new Socket("127.0.0.1", this.port);


        //Creo il reader da socket
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);

//        DebugUtility.debugConsoleUserMessage("ClientSocketConnection", "init", "Connessione");

        //PROTOCOLLO DI INIZIALIZZAZIONE DELLA CONNESSIONE

        //Attendo il "via libera" del server alla comunicazione
        DebugUtility.simpleDebugMessage("Attesa connection ok");

        /*String s = null;
        while (s == null) {*/

           String s = readFromNetwork(false);

          /*  try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        DebugUtility.simpleDebugMessage("Message from server: "+s);
    }

    @Override
    protected void writeOnNetwork(String msgStr) {
        //TODO: istruzione di test da cancellare
        //System.out.println("ClientSocketConnection : writeOnNetwork> sto per inviare il messaggio: " + msgStr);

        try {
            socketWriter.write(msgStr + "\n");
            socketWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String readFromNetwork(boolean returnIfNull) throws IOException {
        String msg = null;
        if( !returnIfNull || socketReader.ready() ) {
            msg = socketReader.readLine();
        }
        return msg;
    }
}
