package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.model.game.InformationFromNetworking;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerConnectionThread;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread{
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;
    private final GameLogic gameLogic; //Partita vera e propria
    private InformationFromNetworking informationFromNetworking;
    private ServerConnectionThread hostConnection; //primo client che si collega alla partita
    private int id;
    private boolean listenNetworkInterfaces = true;

    //Attibuti di test
    private Model model;
    private ModelProva modelProva;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(ServerConnectionThread host, int id, MatchTable matchTable){
        this.informationFromNetworking = new InformationFromNetworking();
        this.gameLogic = new GameLogic(informationFromNetworking, model, this);
        this.networkInterface = new NetworkInterface(this, matchTable, this.gameLogic);
        this.networkInterface.setModelProva(modelProva);
        this.hostConnection = host;
        this.id = id;
//        gameLogic.run();

        addConnection(host);
    }

    public void run()
    {
        //Ciclo in attesa di messaggi sulle socket
        while ( listenNetworkInterfaces )
        {
            for ( PlayerId playerId : networkInterface.getPlayerIdList() )
                networkInterface.readFromClient(playerId);

//            try {
//                sleep(700);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    /* Getters & Setters*/
    public int getMatchId()
    {
        return this.id;
    }

    public Model getModel()
    {
        return model;
    }

    /* Run() Method*/

    public void sendViews(Map<PlayerId, View> clientViewList)
    {
        //spedisco le view ai client
        for(Map.Entry<PlayerId, View> currentPair : clientViewList.entrySet())
        {
            PlayerId currentPlayerId = currentPair.getKey();
            View currentView = currentPair.getValue();
            networkInterface.sendToClient(new ViewMessage(currentView), currentPlayerId);
        }
    }

    public boolean addConnection(ServerConnectionThread clientConnection)
    {
        if ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            //TODO: eccezione
            return true; //ma sarebbe meglio gestire la cosa in modo diverso: si è tentato di aggiungere un player ad una partita già completa
        }

        this.networkInterface.addConnection(clientConnection);

        TypeOfView tov = clientConnection.getConnectionMessage().getTypeOfView();
        String username = clientConnection.getConnectionMessage().getUsername();
        int playerNumber = this.informationFromNetworking.addPlayerViewChoice(tov, username);

        boolean started = ( playerNumber == MAX_PLAYER_NUMBER );

        networkInterface.printPlayerTable();

        return started;
    }

    public void setListenNetworkInterfaces( boolean listenNetworkInterfaces )
    {
        this.listenNetworkInterfaces = listenNetworkInterfaces;
    }

    public void disconnectPlayer(PlayerId playerId)
    {
        networkInterface.disconnectPlayer(playerId);
    }

    public void reconnectPlayer(ServerConnectionThread connectionThread, PlayerId playerId)
    {
        this.networkInterface.reconnectPlayer(connectionThread, playerId);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        return id == match.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
