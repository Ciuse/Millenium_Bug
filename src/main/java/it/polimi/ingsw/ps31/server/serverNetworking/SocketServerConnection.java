package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.networking.ConnectionType;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 09/06/2017.
 */
public class SocketServerConnection extends ServerConnectionInterface {
    private final Socket socket;
    private  BufferedReader socketReader;
    private  BufferedWriter socketWriter;

    /* Constructor */
    public SocketServerConnection(Socket socket) throws IOException {
        super(ConnectionType.SOCKET);

        this.socket = socket;

        //Creo il reader da socket
        InputStreamReader inputStreamReader = null;
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);
    }


    @Override
    protected String readFromNetwork() throws IOException {
        String msgToReturn = null;
        msgToReturn = socketReader.readLine();

        return msgToReturn;
    }

    @Override
    protected void writeOnNetwork(String msg)
    {
        try {
            socketWriter.write(msg+ "\n");
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

    @Override
    public void disconnect()
    {
        try
        {
            this.socket.close();
            this.socketWriter.close();
            this.socketReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
