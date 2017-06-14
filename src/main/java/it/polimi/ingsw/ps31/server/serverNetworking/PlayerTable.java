package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 12/06/2017.
 */
class PlayerConnectionRow{
    private final PlayerId playerId;
    private final ConnectionInterface connectionInterface;

    /* Constructor */
    public PlayerConnectionRow(PlayerId playerId, ConnectionInterface connectionInterface)
    {
        this.playerId = playerId;
        this.connectionInterface = connectionInterface;
    }

    /* Getters */
    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public ConnectionInterface getConnectionInterface()
    {
        return connectionInterface;
    }
}

public class PlayerTable {
    private List<PlayerConnectionRow> playerConnectionRows;
    private int nextPlayerIdIndex = 0;

    /* Constructor */
    public PlayerTable()
    {
        playerConnectionRows = new ArrayList<>();
    }

    /* Class Methods */
    public void addPlayer(ConnectionInterface connectionInterface)
    {
        if ( nextPlayerIdIndex == PlayerId.values().length )
        {
            //todo: eccezione
        }

        PlayerId playerId = PlayerId.values()[nextPlayerIdIndex];
        PlayerConnectionRow newRow = new PlayerConnectionRow(playerId, connectionInterface);
        this.playerConnectionRows.add(newRow);

        nextPlayerIdIndex++;
    }

    public PlayerId connectionToPlayerId(ConnectionInterface connectionInterface)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerId playerIdToReturn = null;
        while (rowsItr.hasNext() && playerIdToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getConnectionInterface().equals(connectionInterface) )
                playerIdToReturn = currentRow.getPlayerId();
        }

        return playerIdToReturn;
    }

    public ConnectionInterface playerIdToConncetion(PlayerId playerId)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        ConnectionInterface connectionToReturn = null;
        while (rowsItr.hasNext() && connectionToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerId().equals(playerId) )
                connectionToReturn = currentRow.getConnectionInterface();
        }

        return connectionToReturn;
    }

    public int size()
    {
        return this.playerConnectionRows.size();
    }

    public void printTable(Match match)
    {
        System.out.println("\n");

        System.out.println("Tabella della partita #"+match.getMatchId());
        System.out.println("========================");
        for(PlayerConnectionRow currentRow : playerConnectionRows)
            System.out.println("Player "+currentRow.getPlayerId()+"\t : "+currentRow.getConnectionInterface().toString());

        System.out.println("\n");
    }

}
