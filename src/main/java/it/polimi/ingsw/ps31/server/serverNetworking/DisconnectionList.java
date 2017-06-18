package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/06/2017.
 */
class DisconnectedRow{
    private final ServerConnectionInterface serverConnectionInterface;
    private final Match match;

    /* Constructor */
    public DisconnectedRow(ServerConnectionInterface serverConnectionInterface, Match match){
        this.serverConnectionInterface = serverConnectionInterface;
        this.match = match;
    }

    /* Getters */
    public String getUsername() {
        return serverConnectionInterface.getUsername();
    }

    public String getPassword() {
        return serverConnectionInterface.getPassword();
    }

    public Match getMatch() {
        return match;
    }


}
public class DisconnectionList {
    /* Singleton */
    private List<DisconnectedRow> disconnections;
    private MatchTable matchTable;
    private static DisconnectionList ourInstance = new DisconnectionList();

    public static DisconnectionList getInstance() {
        return ourInstance;
    }

    private DisconnectionList() {
        this.disconnections = new ArrayList<>();
    }

    public Match search (String username, String password)
    {
        for( DisconnectedRow currentRow : disconnections )
        {
            if (currentRow.getUsername().equals(username) && currentRow.getPassword().equals(password))
                return currentRow.getMatch();
        }

        return null;
    }

//    public void disconnect(ServerConnectionInterface serverConnectionInterface)
//    {
//        Match match = matchTable.getMatchFromConnection(serverConnectionInterface);
//        if ( match == null )
//            return;
//
//        disconnections.add(new DisconnectedRow(serverConnectionInterface, match));
//    }
//
//    public void reconnect(String username, String password, Socket newSocket)
//    {
//        for (DisconnectedRow currentConnection : disconnections )
//        {
//            if (currentConnection.getUsername().equals(username) && currentConnection.getPassword().equals(password))
//
//        }
//    }
}
