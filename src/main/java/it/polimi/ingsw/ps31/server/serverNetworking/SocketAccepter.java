package it.polimi.ingsw.ps31.server.serverNetworking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

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

        //TODO: istruzione di test da cancellare
        System.out.println("SocketAccepter:acceptConnection> Connessione in ingresso. Deviata su porta "+clientSocket.getPort()+".");

        try {
            SocketServerConnection connectionInterface = new SocketServerConnection(clientSocket);
            ServerConnectionThread connectionThread = new ServerConnectionThread(connectionInterface, matchTable);
            System.out.println("SocketAccepter:acceptConnection> Debug 1");
            connectionThread.start();

            do {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while ( !connectionThread.receivedCM() );

            //aggiungo il client alla prima partita libera
            matchTable.addPlayer(connectionThread);

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