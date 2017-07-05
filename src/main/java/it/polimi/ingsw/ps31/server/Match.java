package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.model.game.InformationFromNetworking;
import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.PlayerCommunicationInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
class InputBufferReader extends Thread{
    private boolean listenNetworkInterfaces = true;
    private NetworkInterface networkInterface;
    private VirtualView virtualView;
    private int connectedPlayers;

    public InputBufferReader(NetworkInterface networkInterface, VirtualView virtualView, int initialNumberOfConnections)
    {
        this.networkInterface = networkInterface;
        this.virtualView = virtualView;
        this.connectedPlayers = initialNumberOfConnections;
    }

    public void run() {
        System.out.println("Match:run> Entro nel run");
        //Ciclo in attesa di messaggi sulle socket
        while (listenNetworkInterfaces) {
            for( int i = 0; i<connectedPlayers; i++) {
                VCVisitable vcVisitable = networkInterface.readFromClient(PlayerId.values()[i]);
                if (vcVisitable != null)
                    virtualView.notifyController(vcVisitable);

                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void alt()
    {
        listenNetworkInterfaces = false;
    }

    public void incConnectedPlayers()
    {
        this.connectedPlayers++;
    }
}

public class Match extends Thread{
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;
    private final GameLogic gameLogic; //Partita vera e propria
    private InformationFromNetworking informationFromNetworking;
    private PlayerCommunicationInterface hostConnection; //primo client che si collega alla partita
    private int id;
    private InputBufferReader inputBufferReader;
    private List<PlayerId> disconnectedPlayers;
    //Attibuti di test
    private Model model;
    private VirtualView virtualView;
    private int connectedPlayers;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(PlayerCommunicationInterface host, int id, MatchTable matchTable){
        //System.out.println("Match: init> Match in costruzione");
        this.informationFromNetworking = new InformationFromNetworking();
        this.model = new Model();
        this.gameLogic = new GameLogic(informationFromNetworking, model, this);
        this.networkInterface = new NetworkInterface(this, matchTable, this.gameLogic);
        //this.networkInterface.setModelProva(modelProva);
        this.hostConnection = host;
        this.id = id;
        this.disconnectedPlayers = new ArrayList<>();
        this.connectedPlayers = 0;

        addConnection(host);
        start();

    }

    public void initializeGame()
    {
        //System.out.println("Match:run> entrato nello start");
        this.virtualView=new VirtualView(networkInterface);
        Controller controller = new Controller(model,virtualView,gameLogic.getGameUtility());
        this.inputBufferReader = new InputBufferReader(this.networkInterface, this.virtualView, this.connectedPlayers);
        virtualView.addController(controller);
        controller.start();
        gameLogic.createJson();
        gameLogic.startConnection(virtualView);
        System.out.println("Match:initializeGame> prima del ritorno al run");

    }
    public void run()
    {
        initializeGame();
        inputBufferReader.start();
        gameLogic.playGame();
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
        //System.out.println("Match : sendViews()> VIEW PARTENZA!!!");

        //spedisco le view ai client
        for(int i = 0; i<playerNumber; i++)
        {
            //System.out.println("Match : sendViews()> entrato nel ciclo!");
            PlayerId currentPlayerId = PlayerId.values()[i];
            System.out.println("Match : sendViews()> Player: " + currentPlayerId);
            networkInterface.sendToClient(new ViewMessage(currentPlayerId, playerNumber), currentPlayerId);
        }
       // System.out.println("Match : sendViews()> fuori dal ciclo");

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

        clientConnection.switchOn();

        if( inputBufferReader == null)
            this.connectedPlayers++;
        else
            inputBufferReader.incConnectedPlayers();

        boolean started = ( playerNumber == MAX_PLAYER_NUMBER );

        networkInterface.printPlayerTable();

        return started;
    }

    public void disconnectPlayer(PlayerId playerId)
    {
        disconnectedPlayers.add(playerId);
    }

    public void reconnectPlayer(PlayerCommunicationInterface newCommunicationInterface, PlayerId playerId)
    {
        disconnectedPlayers.remove(playerId);
        PlayerCommunicationInterface oldCommunicationInterface = networkInterface.playerIdToConnection(playerId);

        newCommunicationInterface.reopenClosedConnection(oldCommunicationInterface);

    }

    public PlayerId connectionToPlayerId(PlayerCommunicationInterface playerCommunicationInterface)
    {
        return networkInterface.connectionToPlayerId(playerCommunicationInterface);
    }

    public void printPlayerTable()
    {
        networkInterface.printPlayerTable();
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
