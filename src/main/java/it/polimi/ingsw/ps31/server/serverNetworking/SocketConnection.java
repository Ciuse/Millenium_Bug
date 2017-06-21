package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.networking.ConnectionType;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 09/06/2017.
 */
public class SocketConnection extends ConnectionInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;

    /* Constructor */
    public SocketConnection(Socket socket) throws IOException {
        super(ConnectionType.SOCKET);
        this.socket = socket;

        //Creo il reader da socket
        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);
    }

    @Override
    public String notifyModel()
    {
        String msgToReturn = null;

        try
        {
            msgToReturn = socketReader.readLine();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return msgToReturn;
    }

    @Override
    public void notifyClient(String msg)
    {

        try {
            socketWriter.write(msg+"\n");
            socketWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getConnectionInfo() {
        Integer boh = (Integer)socket.getPort();
        return boh.toString();
    }
}
