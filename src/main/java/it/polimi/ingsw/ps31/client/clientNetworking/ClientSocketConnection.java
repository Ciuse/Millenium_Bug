package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;

import java.io.*;
import java.net.Socket;

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

        //TODO: istruzione di test da cancellare
        System.out.println("Client> sto per creare la socket");

        //Creo la socket e la collego al server
        this.socket = new Socket("127.0.0.1", this.port);

        //TODO: istruzione di test da cancellare
        System.out.println("Client> socket creata ");

        //Creo il reader da socket
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);


        //PROTOCOLLO DI INIZIALIZZAZIONE DELLA CONNESSIONE

        //Attendo il "via libera" del server alla comunicazione
        String s = null;
        while(s == null)
        {
            s = readFromNetwork();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client> Message frome server: "+s);

    }

    @Override
    protected void writeOnNetwork(String msgStr) {
        //TODO: istruzione di test da cancellare
        System.out.println("ClientSocketConnection : writeOnNetwork> sto per inviare il messaggio: " + msgStr);

        try {

            socketWriter.write(msgStr + "\n");
            socketWriter.flush();
            //TODO: istruzione di test da cancellare
            System.out.println("ClientSocketConnection : writeOnNetwork> messaggio inviato");
            //socketWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String readFromNetwork() {
        String msg = null;

        try {
            msg = socketReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
