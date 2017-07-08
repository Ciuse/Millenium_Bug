package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.SocketAccepter;

import java.io.IOException;
import java.net.BindException;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Server {
//    static final int PORT = 2727;
//    protected static final int MAXCONNECTION=4;
//    protected static int connection=0;
    private static SocketAccepter connectionAccepter;
    private static MatchTable matchTable;


    public static void main(String args[])
    {
        boolean error = false;

        //Crea tabella partite
        matchTable = MatchTable.getInstance();

        //Crea socket di ascolto
        try {
            connectionAccepter = SocketAccepter.createInstance(matchTable);
        }catch (BindException e) {
            DebugUtility.debuggedUserMessage("Attenzione! Un'istanza del server è già attiva. " +
                    "Non può esserne avviata un'altra.");
            error = true;
        }catch (IOException e){
            e.printStackTrace();
        }

        //Avvio il socket
        if( !error)
            connectionAccepter.startAccepting();

        //Spegnimento del server
        return;
    }
}
