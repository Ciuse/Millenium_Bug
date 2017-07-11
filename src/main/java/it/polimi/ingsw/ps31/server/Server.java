package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.SocketAccepter;

import java.io.IOException;
import java.net.BindException;

/**
 * Created by Giuseppe on 05/06/2017.
 *
 * Classe contenente il main() lato server. Istanzia le classi SocketAccepter e MatchTable, a cui delega la
 * gestione delle future connessioni.
 * Se lanciata più di una volta, viene rilevato un errore (tramite gestione delle eccezioni durante la creazione
 * del ServerSocket) el'esecuzione termina immediatamente con un messaggio a console.
 *
 * @see SocketAccepter
 * @see MatchTable
 */
public class Server {
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
