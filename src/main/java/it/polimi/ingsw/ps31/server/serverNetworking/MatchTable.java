package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
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
        this.match.addConnection(client);
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Match getMatch()
    {
        return this.match;
    }

    public ArrayList<PlayerCommunicationInterface> getClientList()
    {
        return new ArrayList<>(this.clientList);
    }

    public String printRow()
    {
        String res = "-----------------------------------\n";
        boolean start = true;
        for( PlayerCommunicationInterface playerCommunicationInterface : clientList ) {
            if (start) {
                res += match.getMatchId();
                start = false;
            }
            res += "\t|\t CI: "+playerCommunicationInterface+"\n";
        }

        return res;
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
    private void newMatch(PlayerCommunicationInterface playerCommunicationInterface)
    {
        //Creo il match
        Match match = new Match(playerCommunicationInterface, nextMatchId, this);
        nextMatchId++;

        //Aggiungo il match alla tabella
        this.matchTable.add(new MatchRow(match, playerCommunicationInterface));

        //TODO: istruzione di test da cancellare
        DebugUtility.simpleUserMessage(/*"MatchTable:newMatch>*/" Creata nuova partita #"+match.getMatchId()+". Client associato.");

    }

    public void addPlayer(PlayerCommunicationInterface connection)
    {
        DebugUtility.simpleUserMessage(/*"MatchTable:addPlayer>*/" CM=" + connection.getConnectionMessage().toString());

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
            DebugUtility.simpleUserMessage(/*"MatchTable" : addPlayer>*/"riconnessione in corso al match "+currentDisconnection.getMatch().getMatchId());
            PlayerId disconnectedPlayerId = currentDisconnection.getPlayerId();
            currentDisconnection.getMatch().reconnectPlayer(connection, disconnectedPlayerId, isMatchStarted(currentDisconnection.getMatch()));
            currentDisconnection.getMatch().printPlayerTable();

            return;
        }

        //Iteratore che scorre la lista di partite
        Iterator<MatchRow> matchItr = matchTable.iterator();

        //Se non ci sono partite nella lista, ne creo una nuova e la ritorno
        if( !matchItr.hasNext() ){
            newMatch(connection);
            return;
        }

        MatchRow currentMatch;
        do
        {
            currentMatch = matchItr.next();
        } while ( currentMatch.isStarted() && matchItr.hasNext() );

        //se il match puntato all'uscita dal ciclo è già iniziato, allora ne creo uno nuovo e lo ritorno
        if ( currentMatch.isStarted() ){
            newMatch(connection);
            return;
        }

        currentMatch.addPlayer(connection);

        DebugUtility.simpleUserMessage("Client connesso alla partita #"+currentMatch.getMatch().getMatchId());
    }

    private Match communicationInterfaceToMatch(PlayerCommunicationInterface communicationInterface)
    {
        printTable();
        for(MatchRow currentRow : matchTable)
        {
            for( PlayerCommunicationInterface currentInterface : currentRow.getClientList() )

            if ( currentInterface == communicationInterface ) {  //Mi interessa che siano esattamente lo stesso oggetto
                return currentRow.getMatch();
            }
        }

        return null;
    }

    public void disconnectClient (PlayerCommunicationInterface connection)//, Match match, PlayerId playerId)
    {
        DebugUtility.simpleDebugMessage("Avvio disconnessione player "+connection.getConnectionMessage().getUsername());
        DebugUtility.simpleDebugMessage("Invocato da "+DebugUtility.getCaller());

        Match match = communicationInterfaceToMatch(connection);
        PlayerId playerId = match.connectionToPlayerId(connection);

        DebugUtility.simpleDebugMessage(/*"MatchTable":disconnectClient()>*/"disconnetto client "+connection.toString()+
                          " dal match "+match.getMatchId());
        this.disconnections.add(new DisconnectedClient(connection.getConnectionMessage(), match, playerId));
        match.disconnectPlayer(playerId);

    }

    public void notifyMatchStarted(Match match)
    {
        int i = 0;
        boolean trovato = false;
        while ( !trovato  &&  i < matchTable.size() )
        {
            MatchRow currentRow = matchTable.get(i);
            if( currentRow.getMatch() == match ) {
                trovato = true;
                currentRow.setStarted(true);
            }
        }

    }

    public boolean isMatchStarted(Match match) {
        int i = 0;
        boolean trovato = false;
        while (!trovato && i < matchTable.size()) {
            MatchRow currentRow = matchTable.get(i);
            if (currentRow.getMatch() == match) {
                return currentRow.isStarted();
            }
        }
        //TODO eccezione
        return true;
    }

    public void printTable()
    {
        String tableStr="===========[MATCH TABLE]===========\n";
        for ( MatchRow row : matchTable ){
            tableStr += row.printRow();
        }

        System.out.println(tableStr);
    }
    //global = 3;
}