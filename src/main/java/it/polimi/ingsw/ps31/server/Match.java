package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.actionControls.Control;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.model.game.InformationFromNetworking;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.PlayerCommunicationInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerListeningThread;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread{
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;
    private final GameLogic gameLogic; //Partita vera e propria
    private InformationFromNetworking informationFromNetworking;
    private PlayerCommunicationInterface hostConnection; //primo client che si collega alla partita
    private int id;
    private boolean listenNetworkInterfaces = true;

    //Attibuti di test
    private Model model;
    private VirtualView virtualView;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(PlayerCommunicationInterface host, int id, MatchTable matchTable){
        System.out.println("Match: init> Match in costruzione");
        this.informationFromNetworking = new InformationFromNetworking();
        this.model = new Model();
        this.gameLogic = new GameLogic(informationFromNetworking, model, this);
        this.networkInterface = new NetworkInterface(this, matchTable, this.gameLogic);
        //this.networkInterface.setModelProva(modelProva);
        this.hostConnection = host;
        this.id = id;
        addConnection(host);
        start();
    }

    public void start()
    {
        System.out.println("Match:run> entrato nello start");
        this.virtualView=new VirtualView(networkInterface);
        Controller controller = new Controller(model,virtualView,gameLogic.getGameUtility());
        virtualView.addController(controller);
        controller.start();
        gameLogic.createJson();
        gameLogic.startConnection(virtualView);
        super.start();
        gameLogic.playGame();
    }

    public void run()
    {
        System.out.println("Match:run> Entro nel run");
        //Ciclo in attesa di messaggi sulle socket
        while ( listenNetworkInterfaces )
        {
            System.out.println("Match:run> ascolto. Timestamp="+System.currentTimeMillis());
            for ( PlayerId playerId : networkInterface.getPlayerIdList() )
                networkInterface.readFromClient(playerId);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void sendViews(int playerNumber)
    {
        System.out.println("Match : sendViews()> VIEW PARTENZA!!!");

        //spedisco le view ai client
        for(int i = 0; i<playerNumber; i++)
        {
            System.out.println("Match : sendViews()> entrato nel ciclo!");
            PlayerId currentPlayerId = PlayerId.values()[i];
            System.out.println("Match : sendViews()> Player: " + currentPlayerId);
            networkInterface.sendToClient(new ViewMessage(currentPlayerId, playerNumber), currentPlayerId);
        }
        System.out.println("Match : sendViews()> fuori dal ciclo");

    }

    public boolean addConnection(PlayerCommunicationInterface clientConnection)
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

    public void reconnectPlayer(PlayerCommunicationInterface connectionThread, PlayerId playerId)
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
