package it.polimi.ingsw.ps31.server.serverNetworking;

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
    MatchTable matchTable;

    /* Singleton Methods */
    public static SocketAccepter createInstance(MatchTable matchTable)
    {
        if( ourInstance == null)
            ourInstance = new SocketAccepter(matchTable);

        return ourInstance;
    }

    public static SocketAccepter getInstance() {
        return ourInstance;
    }

    /* Constructor */
    private SocketAccepter(MatchTable matchTable) {
        this.matchTable = matchTable;

        //creo il socket
        try {
            socket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startAccepting()
    {
        //TODO: istruzione di test da cancellare
        System.out.println("Server avviato. In attesa di connessioni...\n");

        boolean closeConnection = false;
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

    private synchronized void acceptConnection(Socket clientSocket)
    {
        if ( clientSocket == null )
            return;
        //Creo l'oggetto connessione relativo al tipo di connessione scelta
        //todo: se il client passasse un oggetto ChosenConnection, si potrebbero evitare l'if seguente e l'enum

        //TODO: istruzione di test da cancellare
        System.out.println("Server> Connessione in ingresso. Deviata su porta "+clientSocket.getPort()+". Associazione del client in corso...");

        ServerConnectionInterface serverConnectionInterface;
      //if ( chosenConnection.equals(ConnectionType.SOCKET) )
        try {
            serverConnectionInterface = new ServerSocketConnection(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //else
      //  serverConnectionInterface = new RMIConnection();

        //aggiungo il client alla prima partita libera
        matchTable.addPlayer(serverConnectionInterface);
    }

    public void refuseConnection() {

    }
}