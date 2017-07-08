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
    private PlayerCommunicationInterface playerCommunicationInterface = null;
    private boolean disconnected = false;

    /* Constructor */
    public PlayerConnectionRow(PlayerId playerId, PlayerCommunicationInterface playerCommunicationInterface)
    {
        this.playerId = playerId;
        this.playerCommunicationInterface = playerCommunicationInterface;
    }

    /* Getters */
    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public PlayerCommunicationInterface getPlayerCommunicationInterface()
    {
        return playerCommunicationInterface;
    }

    public void disconnect()
    {
       this.disconnected = true;
    }

    public void reconnect(PlayerCommunicationInterface playerCommunicationInterface)
    {
        this.disconnected = false;
        this.playerCommunicationInterface = playerCommunicationInterface;
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
        return getPlayerCommunicationInterface() != null ? getPlayerCommunicationInterface().equals(that.getPlayerCommunicationInterface()) : that.getPlayerCommunicationInterface() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlayerId() != null ? getPlayerId().hashCode() : 0;
        result = 31 * result + (getPlayerCommunicationInterface() != null ? getPlayerCommunicationInterface().hashCode() : 0);
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
    public void addPlayer(PlayerCommunicationInterface playerCommunicationInterface)
    {
        if ( nextPlayerIdIndex == PlayerId.values().length )
        {
            //todo: eccezione
        }

        PlayerId playerId = PlayerId.values()[nextPlayerIdIndex];
        PlayerConnectionRow newRow = new PlayerConnectionRow(playerId, playerCommunicationInterface);
        playerCommunicationInterface.setPlayerId(playerId);
        playerCommunicationInterface.setPlayerTable(this);
        this.playerConnectionRows.add(newRow);

        nextPlayerIdIndex++;
    }

    public PlayerId connectionToPlayerId(PlayerCommunicationInterface serverConnectionInterface)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerId playerIdToReturn = null;
        while (rowsItr.hasNext() && playerIdToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerCommunicationInterface().equals(serverConnectionInterface) )
                playerIdToReturn = currentRow.getPlayerId();
        }

        return playerIdToReturn;
    }

    public PlayerCommunicationInterface playerIdToConnection(PlayerId playerId)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerCommunicationInterface connectionToReturn = null;
        while (rowsItr.hasNext() && connectionToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerId().equals(playerId) )
                connectionToReturn = currentRow.getPlayerCommunicationInterface();
        }

        return connectionToReturn;
    }

    public int size()
    {
        return this.playerConnectionRows.size();
    }

    public void printTable(Match match)
    {
        String tblStr;
        tblStr = "\n";

        tblStr += "Tabella della partita #"+match.getMatchId();
        tblStr += "\n========================";
        for(PlayerConnectionRow currentRow : playerConnectionRows) {
            String alignment = (currentRow.getPlayerId()==PlayerId.THREE) ? "\t" : "\t\t";
            tblStr += "\nPlayer " + currentRow.getPlayerId() + alignment + ": " + currentRow.getPlayerCommunicationInterface().toString();
        }
        tblStr += "\n";

        System.out.println(tblStr);
    }

    public List<PlayerCommunicationInterface> getAllConnections()
    {
        List<PlayerCommunicationInterface> list = new ArrayList<>();

        for(PlayerConnectionRow currentRow : this.playerConnectionRows)
            list.add(currentRow.getPlayerCommunicationInterface());

        return list;
    }

    public List<PlayerId> getPlayerIdList()
    {
        List<PlayerId> result = new ArrayList<>();
        for( PlayerConnectionRow player : playerConnectionRows)
            result.add(player.getPlayerId());

        return result;
    }

    public void disconnectPlayer(PlayerId playerId)
    {
        for(PlayerConnectionRow currentRow : playerConnectionRows)
            if( currentRow.getPlayerId().equals(playerId) )
                currentRow.disconnect();
    }

    public void reconnectPlayer(PlayerCommunicationInterface connection, PlayerId playerId)
    {
        for(PlayerConnectionRow currentRow : playerConnectionRows)
            if( currentRow.getPlayerId().equals(playerId) )
                currentRow.reconnect(connection);
    }

}
