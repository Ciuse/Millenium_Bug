package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 12/06/2017.
 */
class PlayerConnectionRow{
    private final PlayerId playerId;
    private ServerConnectionThread serverConnectionThread = null;
    private boolean disconnected = false;

    /* Constructor */
    public PlayerConnectionRow(PlayerId playerId, ServerConnectionThread serverConnectionThread)
    {
        this.playerId = playerId;
        this.serverConnectionThread = serverConnectionThread;
    }

    /* Getters */
    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public ServerConnectionThread getServerConnectionThread()
    {
        return serverConnectionThread;
    }

    public void disconnect()
    {
       this.disconnected = true;
    }

    public void reconnect(ServerConnectionThread serverConnectionThread)
    {
        this.serverConnectionThread = serverConnectionThread;
        this.disconnected = false;
    }

    public boolean isDisconnected()
    {
        return this.disconnected;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerConnectionRow)) return false;

        PlayerConnectionRow that = (PlayerConnectionRow) o;

        if (getPlayerId() != that.getPlayerId()) return false;
        return getServerConnectionThread() != null ? getServerConnectionThread().equals(that.getServerConnectionThread()) : that.getServerConnectionThread() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlayerId() != null ? getPlayerId().hashCode() : 0;
        result = 31 * result + (getServerConnectionThread() != null ? getServerConnectionThread().hashCode() : 0);
        return result;
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
    public void addPlayer(ServerConnectionThread serverConnectionThread)
    {
        if ( nextPlayerIdIndex == PlayerId.values().length )
        {
            //todo: eccezione
        }

        PlayerId playerId = PlayerId.values()[nextPlayerIdIndex];
        PlayerConnectionRow newRow = new PlayerConnectionRow(playerId, serverConnectionThread);
        serverConnectionThread.setPlayerId(playerId);
        this.playerConnectionRows.add(newRow);

        nextPlayerIdIndex++;
    }

    public PlayerId connectionToPlayerId(ServerConnectionThread serverConnectionInterface)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerId playerIdToReturn = null;
        while (rowsItr.hasNext() && playerIdToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getServerConnectionThread().equals(serverConnectionInterface) )
                playerIdToReturn = currentRow.getPlayerId();
        }

        return playerIdToReturn;
    }

    public ServerConnectionThread playerIdToConnection(PlayerId playerId)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        ServerConnectionThread connectionToReturn = null;
        while (rowsItr.hasNext() && connectionToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerId().equals(playerId) )
                connectionToReturn = currentRow.getServerConnectionThread();
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
            System.out.println("Player "+currentRow.getPlayerId()+"\t : "+currentRow.getServerConnectionThread().toString());

        System.out.println("\n");
    }

    public List<ServerConnectionThread> getAllConnections()
    {
        List<ServerConnectionThread> list = new ArrayList<>();

        for(PlayerConnectionRow currentRow : this.playerConnectionRows)
            list.add(currentRow.getServerConnectionThread());

        return list;
    }

    public void disconnectPlayer(PlayerId playerId)
    {
        int i = 0;
        boolean exit = false;
        while ( i<playerConnectionRows.size() && !exit )
        {
            PlayerConnectionRow row = playerConnectionRows.get(i);
            if ( row.getPlayerId() == playerId )
            {
                exit = true;
                row.disconnect();
            }

        }

    }

    public List<PlayerId> getPlayerIdList()
    {
        List<PlayerId> result = new ArrayList<>();
        for( PlayerConnectionRow player : playerConnectionRows)
            result.add(player.getPlayerId());

        return result;
    }

    public boolean reconnectPlayer(ServerConnectionThread connectionThread, PlayerId playerId)
    {
        int i = 0;
        boolean found = false;
        while( i < playerConnectionRows.size() && !found )
        {
            if ( playerConnectionRows.get(i).getPlayerId() == playerId )
            {
                playerConnectionRows.remove(i);
                playerConnectionRows.add( new PlayerConnectionRow(playerId, connectionThread) );
                found = true;
            }
        }
        return found;

    }

}
