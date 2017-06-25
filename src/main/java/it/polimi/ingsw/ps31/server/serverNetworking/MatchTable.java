package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
class MatchRow {
    private Match match;
    private List<ServerConnectionInterface> clientList;
    private boolean started = false;

    public MatchRow(Match match, ServerConnectionInterface hostConnection)
    {
        this.match = match;
        this.clientList = new ArrayList<>();
        clientList.add(hostConnection);
    }

    public boolean isStarted()
    {
        return this.started;
    }

    public void addPlayer(ServerConnectionInterface client)
    {
        //aggiungo la connessione alla lista di connessioni della row
        this.clientList.add(client);

        //passo il socket alla partita
        this.started = this.match.addConnection(client);

        //Se la partitia deve iniziare, avvio il match
        if( this.started )
            match.start();

        //todo aggiungere started = true allo scadere del timeout
    }

    public Match getMatch()
    {
        return this.match;
    }

}

public class MatchTable {
    /* Singleton */
    private List<MatchRow> matchTable = new ArrayList<>();
    private int nextMatchId;

    /* Singleton Methods */
    private static MatchTable ourInstance = new MatchTable();
    public static MatchTable getInstance() {
        return ourInstance;
    }

    /* Constructor */
    private MatchTable() {
        this.nextMatchId = 1;
    }

    /* Class Methods */
    private Match newMatch(ServerConnectionInterface serverConnectionInterface)
    {
        //TODO: istruzione di test da cancellare
        System.out.println("Server> inizio creazione match");

        //Creo il match (che è un thread)
        Match match = new Match(serverConnectionInterface, nextMatchId);
        nextMatchId++;

        //Aggiungo il match alla tabella
        this.matchTable.add(new MatchRow(match, serverConnectionInterface));

        //TODO: istruzione di test da cancellare
        System.out.println("Server> Creata nuova partita #"+match.getMatchId()+". Client associato.");

        return match;
    }

    public Match addPlayer(ServerConnectionInterface connection)
    {
        //Iteratore che scorre la lista di partite
        Iterator<MatchRow> matchItr = matchTable.iterator();

        //Se non ci sono partite nella lista, ne creo una nuova e la ritorno
        if( !matchItr.hasNext() )
           return newMatch(connection);

        MatchRow currentMatch;
        do
        {
            currentMatch = matchItr.next();
        } while ( currentMatch.isStarted() && matchItr.hasNext() );

        //se il match puntato all'uscita dal ciclo è già iniziato, allora ne creo uno nuovo e lo ritorno
        if ( currentMatch.isStarted() )
           return newMatch(connection);

        currentMatch.addPlayer(connection);

        //TODO: istruzione di test da cancellare
        System.out.println("Server> Client connesso alla partita #"+currentMatch.getMatch().getMatchId());


        return currentMatch.getMatch(); //todo a cosa serve??
    }

    //global = 3;
}