package it.polimi.ingsw.ps31.client.clientNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 10/06/2017.
 *
 * Classe che implementa la connessione via socket. Contiene reader e writer ed espone metodi di
 * lettura e scrittura di stringhe
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

    /**
     * Metodo di scrittura su rete
     * @param msgStr stringa da scrivere
     */
    @Override
    protected void writeOnNetwork(String msgStr) {

        try {
            socketWriter.write(msgStr + "\n");
            socketWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo di lettura da rete
     * @param returnIfNull indica se bisogna restare in attesa(false) di un messaggio o
     *                     ritornare subito(true) in caso non ci siano messaggi da leggere
     * @return stringa letta
     * @throws IOException
     */
    @Override
    protected String readFromNetwork(boolean returnIfNull) throws IOException {
        String msg = null;
        if( !returnIfNull || socketReader.ready() ) {
            msg = socketReader.readLine();
        }
        return msg;
    }
}
