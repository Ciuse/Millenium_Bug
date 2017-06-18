package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.networking.ConnectionType;
import it.polimi.ingsw.ps31.networking.MexProva;
import jdk.jfr.events.SocketReadEvent;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Francesco on 09/06/2017.
 */
public class ServerSocketConnection extends ServerConnectionInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;

    /* Constructor */
    public ServerSocketConnection(Socket socket) throws IOException {
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
    protected String readMsgFromNetwork()
    {
        String msgToReturn = null;

        try
        {
            msgToReturn = socketReader.readLine();
        } catch (SocketException se)
        {
            //Client disconnesso all'improvviso
            msgToReturn = serialize(new MexProva("SuddenlyClosedConnection"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return msgToReturn;
    }

    @Override
    protected void sendMsgToNetwork(String msg)
    {

        try {
            socketWriter.write(msg+"\n");
            socketWriter.flush();

        } catch (IOException e) {
            System.out.println("Impossibile spedire il seguente messaggio : '"+msg+"'. Socket chiusa");
        }

//        return;
    }

    @Override
    public String getConnectionInfo() {
        Integer port = (Integer)socket.getPort();
        return port.toString();
    }

    @Override
    public void close() {

//        notifyModel();
        try {
            this.socketWriter.close();
            this.socketReader.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
