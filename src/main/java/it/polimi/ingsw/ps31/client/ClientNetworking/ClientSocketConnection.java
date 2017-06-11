package it.polimi.ingsw.ps31.client.ClientNetworking;

import it.polimi.ingsw.ps31.client.Client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ClientSocketConnection extends ClientNetworkInterface {
    private Socket socket;
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;

    /* Constructor */
    public ClientSocketConnection(int port) throws IOException {

        //Creo la socket e la collego al server
        this.socket = new Socket("127.0.0.1", port);

        //Creo il reader da socket
        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
        socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        socketWriter = new BufferedWriter(socketOutStream);
    }

    @Override
    public void writeToServer(Serializable object) {

    }

    @Override
    public Object readFromServer() {
        return null;
    }
}
