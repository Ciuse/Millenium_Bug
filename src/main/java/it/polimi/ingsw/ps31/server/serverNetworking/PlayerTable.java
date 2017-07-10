package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 12/06/2017.
 * Associa ogni PlayerId di una partita con la PlayerConnectionInterface collegata al giocatore.
 * É contenuta nella NetworkInterface ed è unica per ogni partita.
 * @see it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface
 */

/**
 * Singola riga della tabella. Contine playerId, riferimento alla PlayerConnectinInterface e un flag che indica
 * se il client è disconnesso o meno.
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

    /**
     * Marca il client come disconnesso
     */
    public void disconnect()
    {
       this.disconnected = true;
    }

    /**
     * riconnette il client salva il riferimento alla nuova PlayerCommunicationInterface
     * @param playerCommunicationInterface nuova communication interface verso il client
     */
    public void reconnect(PlayerCommunicationInterface playerCommunicationInterface)
    {
        this.disconnected = false;
        this.playerCommunicationInterface = playerCommunicationInterface;
    }

    /**
     * @return true se il client è disconnesso, false se non lo è
     */
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

/**
 * Tabella contenente le associazioni
 */
public class PlayerTable {
    /** Tabella vera e propria, modellizzata come una lista di PlayerConnectionRow */
    private List<PlayerConnectionRow> playerConnectionRows;

    private int nextPlayerIdIndex = 0;

    /* Constructor */
    public PlayerTable()
    {
        playerConnectionRows = new ArrayList<>();
    }

    /* Class Methods */

    /**
     * Aggiunge un player alla tabella e associa il playerId corretto alla PlayerCommunicationInterface
     * @param playerCommunicationInterface interfaccia del player da aggiungere
     */
    public void addPlayer(PlayerCommunicationInterface playerCommunicationInterface)
    {
        if ( nextPlayerIdIndex == PlayerId.values().length )
        {
            //todo: eccezione
        }

        PlayerId playerId = PlayerId.values()[nextPlayerIdIndex];
        PlayerConnectionRow newRow = new PlayerConnectionRow(playerId, playerCommunicationInterface);
        playerCommunicationInterface.setPlayerId(playerId);
        //playerCommunicationInterface.setPlayerTable(this);
        this.playerConnectionRows.add(newRow);

        nextPlayerIdIndex++;
    }

    /**
     * Data una connessione a un client, restituisce il playerId del client connesso
     * @param playerCommunicationInterface connessione della quale si vuole sapere l'id del player
     * @return il PlayerId del giocatore a cui la communicationInterface è collegata
     */
    public PlayerId connectionToPlayerId(PlayerCommunicationInterface playerCommunicationInterface)
    {
        Iterator<PlayerConnectionRow> rowsItr = this.playerConnectionRows.iterator();
        PlayerConnectionRow currentRow;
        PlayerId playerIdToReturn = null;
        while (rowsItr.hasNext() && playerIdToReturn == null)
        {
            currentRow = rowsItr.next();
            if( currentRow.getPlayerCommunicationInterface().equals(playerCommunicationInterface) )
                playerIdToReturn = currentRow.getPlayerId();
        }

        return playerIdToReturn;
    }

    /**
     * Dato un playerId, restituisce la PlayerCommunicationInterface per raggiungerlo
     * @param playerId id di cui si cerca il riferimento alla PlayerComunicatoinInterface
     * @return PlayerCommunicationInterface per comunicare con il player
     */
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

    /**
     * @return una lista contenente tutte le PlayerCommunicationInterface collegate alla partita, in ordine
     * di connessione (e quindi con PlayerId crescente)
     */
    public List<PlayerCommunicationInterface> getAllConnections()
    {
        List<PlayerCommunicationInterface> list = new ArrayList<>();

        for(PlayerConnectionRow currentRow : this.playerConnectionRows)
            list.add(currentRow.getPlayerCommunicationInterface());

        return list;
    }

    /**
     * @return una lista con tutti i PlayerId già in uso nella partita
     */
    public List<PlayerId> getPlayerIdList()
    {
        List<PlayerId> result = new ArrayList<>();
        for( PlayerConnectionRow player : playerConnectionRows)
            result.add(player.getPlayerId());

        return result;
    }

    /**
     * Disconnette il player
     * @param playerId id del player da disocnnettere
     */
    public void disconnectPlayer(PlayerId playerId)
    {
        for(PlayerConnectionRow currentRow : playerConnectionRows)
            if( currentRow.getPlayerId().equals(playerId) )
                currentRow.disconnect();
    }

    /**
     * Riconnette un player alla partita
     * @param connection nuova PlayerCommunicationInterface per comunicare con il player
     * @param playerId id del playerche si vuole riconnettere
     */
    public void reconnectPlayer(PlayerCommunicationInterface connection, PlayerId playerId)
    {
        for(PlayerConnectionRow currentRow : playerConnectionRows)
            if( currentRow.getPlayerId().equals(playerId) )
                currentRow.reconnect(connection);
    }

    public boolean isDisconnected(PlayerId playerId)
    {
       for(PlayerConnectionRow currentRow : playerConnectionRows) {
           if (currentRow.getPlayerId().equals(playerId))
               return currentRow.isDisconnected();
       }
       return true;
    }

}
