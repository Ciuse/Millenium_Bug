package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
class MatchRow {
    private Match match;
    private List<PlayerCommunicationInterface> clientList;
    private boolean started = false;

    public MatchRow(Match match, PlayerCommunicationInterface hostConnection)
    {
        this.match = match;
        this.clientList = new ArrayList<>();
        clientList.add(hostConnection);
    }

    public boolean isStarted()
    {
        return this.started;
    }

    public void addPlayer(PlayerCommunicationInterface client)
    {
        //aggiungo la connessione alla lista di connessioni della row
        this.clientList.add(client);

        //passo il socket alla partita
        this.started = this.match.addConnection(client);
    }

    public Match getMatch()
    {
        return this.match;
    }

    public ArrayList<PlayerCommunicationInterface> getClientList()
    {
        return new ArrayList<>(this.clientList);
    }
}

class DisconnectedClient{
    private ConnectionMessage connectionMessage;
    private Match match;
    private PlayerId playerId;

    /* Constructor */
    public DisconnectedClient(ConnectionMessage connectionMessage, Match match, PlayerId playerId)
    {
        this.connectionMessage = connectionMessage;
        this.match = match;
        this.playerId = playerId;
    }

    public ConnectionMessage getConnectionMessage()
    {
        return connectionMessage;
    }

    public Match getMatch()
    {
        return match;
    }

    public PlayerId getPlayerId()
    {
        return this.playerId;
    }

}

public class MatchTable {
    /* Singleton */
    private List<MatchRow> matchTable = new ArrayList<>();
    private int nextMatchId;
    private List<DisconnectedClient> disconnections = new ArrayList<>();

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
    private Match newMatch(PlayerCommunicationInterface playerCommunicationInterface)
    {
        //TODO: istruzione di test da cancellare
        System.out.println("MatchTable:newMatch> inizio creazione match");

        //Creo il match
        Match match = new Match(playerCommunicationInterface, nextMatchId, this);
        nextMatchId++;

        //Aggiungo il match alla tabella
        this.matchTable.add(new MatchRow(match, playerCommunicationInterface));

        //TODO: istruzione di test da cancellare
        System.out.println("MatchTable:newMatch> Creata nuova partita #"+match.getMatchId()+". Client associato.");

        //match.start();

        return match;
    }

    public Match addPlayer(PlayerCommunicationInterface connection)
    {
        System.out.println("MatchTable:addPlayer> CM=" + connection.getConnectionMessage().toString());

        //Controllo se la connessione corrisponde a un client disconnesso in precedenza
        boolean found = false;
        int i = 0;
        DisconnectedClient currentDisconnection = null;
        while( i < disconnections.size() && !found)
        {
            currentDisconnection = disconnections.get(i);
            if (currentDisconnection.getConnectionMessage().equals(connection.getConnectionMessage()))
            {
                found = true;
                disconnections.remove(i);
            }
            else
                i++;
        }
        if( found )
        {
            System.out.println("MatchTable : addPlayer>\t\triconnessione in corso");
            PlayerId disconnectedPlayerId = currentDisconnection.getPlayerId();
            currentDisconnection.getMatch().reconnectPlayer(connection, disconnectedPlayerId);
            return currentDisconnection.getMatch();
        }


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

    private Match communicationInterfaceToMatch(PlayerCommunicationInterface communicationInterface)
    {
        for(MatchRow currentRow : matchTable)
        {
            if ( currentRow.getClientList().contains(communicationInterface) )
                return currentRow.getMatch();
        }

        return null;
    }

    public void disconnectClient (PlayerCommunicationInterface connection)//, Match match, PlayerId playerId)
    {
        Match match = communicationInterfaceToMatch(connection);
        System.out.println("MatchTable : disconnectClient()> match:" + match);
        System.out.println("MatchTable : disconnectClient()> connection:" + connection);
        PlayerId playerId = match.connectionToPlayerId(connection);

        System.out.println("MatchTable : disconnectClient()> disconnetto client "+connection.toString()+
                          " dal match "+match.getMatchId());
        this.disconnections.add(new DisconnectedClient(connection.getConnectionMessage(), match, playerId));
        match.disconnectPlayer(playerId);

    }

    //global = 3;
}