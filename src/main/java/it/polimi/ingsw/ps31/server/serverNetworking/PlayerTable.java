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
    private final ServerConnectionInterface serverConnectionInterface;

    /* Constructor */
    public PlayerConnectionRow(PlayerId playerId, ServerConnectionInterface serverConnectionInterface)
    {
        this.playerId = playerId;
        this.serverConnectionInterface = serverConnectionInterface;
    }

    /* Getters */
    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public ServerConnectionInterface getServerConnectionInterface()
    {
        return serverConnectionInterface;
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
    public void addPlayer(ServerConnectionInterface serverConnectionInterface)
    {
        if ( nextPlayerIdIndex == PlayerId.values().length )
        {
            //todo: eccezione
        }

        PlayerId playerId = PlayerId.values()[nextPlayerIdIndex];
        PlayerConnectionRow newRow = new PlayerConnectionRow(playerId, serverConnectionInterface);
        this.playerConnectionRows.add(newRow);

        nextPlayerIdIndex++;
    }

    public PlayerId connectionToPlayerId(ServerConnectionInterface serverConnectionInterface)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerId playerIdToReturn = null;
        while (rowsItr.hasNext() && playerIdToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getServerConnectionInterface().equals(serverConnectionInterface) )
                playerIdToReturn = currentRow.getPlayerId();
        }

        return playerIdToReturn;
    }

    public ServerConnectionInterface playerIdToConncetion(PlayerId playerId)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        ServerConnectionInterface connectionToReturn = null;
        while (rowsItr.hasNext() && connectionToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerId().equals(playerId) )
                connectionToReturn = currentRow.getServerConnectionInterface();
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
            System.out.println("Player "+currentRow.getPlayerId()+"\t : "+currentRow.getServerConnectionInterface().toString());

        System.out.println("\n");
    }

    public List<ServerConnectionInterface> getAllConnections()
    {
        List<ServerConnectionInterface> list = new ArrayList<>();

        for(PlayerConnectionRow currentRow : this.playerConnectionRows)
            list.add(currentRow.getServerConnectionInterface());

        return list;
    }

}
