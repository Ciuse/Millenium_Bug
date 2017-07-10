package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.io.IOException;

/**
 * Created by Francesco on 25/06/2017.
 *
 * Thread con il compito di rimanere in attesa di messaggi sulla rete. Quando viene attivato continua a rimanere
 * in ascolto dei messaggi dal client. Quando ne riceve uno, lo bufferizza e ritorna ad ascoltare.
 * Inoltre è la classe che si occupa di accorgersi se il client è stato disconnesso.
 *
 * @see ServerInputBuffer
 * @see ServerConnectionInterface
 */
public class ServerListeningThread extends Thread {
    /** La connectionInterface da cui ascoltare */
    private ServerConnectionInterface serverConnectionInterface;

    /** Il buffer in cui inserire i messaggi letti */
    private ServerInputBuffer serverInputBuffer;

    /** booleano che indica se la connessione è stata interrotta (true) o meno (false) */
    private boolean closeConnection;

    public ServerListeningThread ( ServerConnectionInterface serverConnectionInterface, ServerInputBuffer serverInputBuffer)
    {
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = serverInputBuffer;
        this.closeConnection = false;
    }

    /**
     * Ascolta la rete in attesa di messaggi e li bufferizza quando ne riceve.
     * Se la ServerConnectionInterface lancia una eccezione di tipo IOException, essa viene
     * interpretata come una disconnessione del client e viene quindi richiamata la catena di invocazione
     * che la gestisce.
     */
    public void run()
    {
        DebugUtility.simpleUserMessage("Inizio ascolto del client");
        while (!closeConnection)
        {
            VCVisitable msgFromNetwork;
            try {
                msgFromNetwork = serverConnectionInterface.readFromClient();

                serverInputBuffer.bufferizeMessage(msgFromNetwork);
            } catch (IOException e)
            {
                DebugUtility.simpleUserMessage(/*"ServerListeningThread:run> */"Client "+serverConnectionInterface.getConnectionInfo()+" disconnesso");
                closeConnection = true; //Esce dal ciclo e disconnette il client
            }

        }
        //matchTable.disconnectClient(serverConnectionInterface, networkInterface.getMatch(), this.playerId);
        serverConnectionInterface.disconnect();
        serverInputBuffer.disconnect();

    }
}