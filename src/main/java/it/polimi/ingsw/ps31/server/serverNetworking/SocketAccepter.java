package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Francesco on 08/06/2017.
 *
 * Incapsula una ServerSocket che rimane in attesa delle connessioni di tipo SOCKET dai client
 * e crea una Socket dedicata ad ogni connessione. Può essere instanziata una sola volta.
 */
public class SocketAccepter{
    /* Singleton */
    private static SocketAccepter ourInstance = null;

    /** Porta su cui la ServerSocket rimane in ascolto */
    private final int SOCKET_PORT = 2727;

    /** ServerSocket che rimane in attesa */
    private ServerSocket socket;

    /** Riferimento alla matchTable a cui passare le connessioni per aggiungerle ad una partita */
    private MatchTable matchTable;

    /** Booleano che indica se la connessione deve essere chiusa */
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

    /**
     * Metodo che cicla in ascolto di connessioni finchè closeCnnection rimane a true.
     */
    public void startAccepting()
    {
        DebugUtility.debuggedUserMessage("Server avviato. In attesa di connessioni...\n", false);

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

    /**
     * metodo che si occupa di accettare una connessione, incapsulando la socket ricevuta in una nuova
     * infrastruttura di comunicazione telematica.
     * @param clientSocket socket da incapsulare
     */
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