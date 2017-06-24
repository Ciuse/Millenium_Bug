package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.AskConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.networking.ConnectionType;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 09/06/2017.
 */
public class SocketServerConnection extends ServerConnectionInterface {
    private final Socket socket;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;

    /* Constructor */
    public SocketServerConnection(Socket socket) throws IOException {
        super(ConnectionType.SOCKET);
        this.socket = socket;

        //Creo il reader da socket
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);

        //chiedo al client di comunicare il suo ConnectionMessage
        super.notifyClient(new AskConnectionMessage());

        //TODO: istruzione di test da cancellare
        System.out.println("server> messaggio inviato ");

        //rimango in attesa del messaggio contenente le informazioni di connessione
        ConnectionMessage receivedMessage;
        do {

            //TODO: istruzione di test da cancellare
            System.out.println("server> in attesa");

            receivedMessage = deserializeCM(socketReader.readLine());
        }
        while (!receivedMessage.getMessageType().equals("ConnectionMessage"));

        super.connectionMessage = (ConnectionMessage) receivedMessage;

    }

    @Override
    protected String readFromNetwork()
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
    protected void sendToNetwork(String msg)
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
