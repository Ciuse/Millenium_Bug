package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.server.MexProva;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ClientSocketConnection extends ClientNetworkInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;

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
    }

    @Override
    public void sendToServer(String msg)
    {
        try {

            socketWriter.write(msg+"\n");
            socketWriter.flush();
            //socketWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromServer()
    {
        String msgToReturn;
        try {
            msgToReturn = socketReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

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
        //Creo gson
        Gson gson = new Gson();

        //Deserializzo l'oggetto
        MexProva strObj = gson.fromJson(msg, MexProva.class);

        return strObj;
    }
}
