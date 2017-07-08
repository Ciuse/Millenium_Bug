package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Francesco on 08/06/2017.
 */
public class SocketAccepter{
    /* Singleton */
    private static SocketAccepter ourInstance = null;
    private final int SOCKET_PORT = 2727;
    private ServerSocket socket;
    private MatchTable matchTable;
    private boolean closeConnection = false;

    /* Singleton Methods */
    public static SocketAccepter createInstance(MatchTable matchTable) throws IOException
    {
        if( ourInstance == null)
            ourInstance = new SocketAccepter(matchTable);

        return ourInstance;
    }

    public static SocketAccepter getInstance() {
        return ourInstance;
    }

    /* Constructor */
    private SocketAccepter(MatchTable matchTable) throws IOException {
        this.matchTable = matchTable;

        //creo il socket
        socket = new ServerSocket(SOCKET_PORT);
    }

    public void startAccepting()
    {
        //TODO: istruzione di test da cancellare
        System.out.println("Server avviato. In attesa di connessioni...\n");

        while( !closeConnection )
        {
            try {
                Socket currentClientSocket = this.socket.accept();
                acceptConnection(currentClientSocket);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void acceptConnection(Socket clientSocket)
    {
        if ( clientSocket == null )
            return;

        DebugUtility.debuggedUserMessage(/*"SocketAccepter:acceptConnection> */"Connessione in ingresso. Porta remota: "+clientSocket.getPort()+".");



        try {
            SocketServerConnection connectionInterface = new SocketServerConnection(clientSocket);
            PlayerCommunicationInterface playerCommunicationInterface = new PlayerCommunicationInterface(connectionInterface, matchTable);
            //System.out.println("SocketAccepter:acceptConnection> DebugUtility 1");

            connectionInterface.writeOnNetwork("Connection ok");
            playerCommunicationInterface.setConnectionMessage(connectionInterface.waitForConnectionMessage());

            //aggiungo il client alla prima partita libera
            matchTable.addPlayer(playerCommunicationInterface);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refuseConnection() {

    }

    public synchronized void shutDownServer()
    {
        this.closeConnection = true;
    }
}