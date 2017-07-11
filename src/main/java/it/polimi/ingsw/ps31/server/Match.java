package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.DebugUtility;
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
 *
 * Classe che crea e gestisce una partita. Implementa un thread in modo che possa essere attivato in parallelo
 * al resto del sistema. Contiene un secondo thread che, indipendentemente dal primo, rimane in ascolto di messaggi
 * sugli input buffer e li notifica al controller tramite la virtual view.
 *
 * @see it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface
 * @see it.polimi.ingsw.ps31.server.serverNetworking.ServerInputBuffer
 * @see Controller
 * @see VirtualView
 * @see Thread
 */

/**
 * Thread che legge gli input buffer dei player connessi alla partita
 */
class InputBufferReader extends Thread{
    /** Booleano che indica se continuare ad ascoltare*/
    private boolean listenNetworkInterfaces = true;

    private NetworkInterface networkInterface;
    private VirtualView virtualView;

    /** Numero di player connessi alla partita*/
    private int connectedPlayers;

    public InputBufferReader(NetworkInterface networkInterface, VirtualView virtualView, int initialNumberOfConnections)
    {
        this.networkInterface = networkInterface;
        this.virtualView = virtualView;
        this.connectedPlayers = initialNumberOfConnections;
    }

    /**
     * Cicla sugl InputBuffer dei player connessi in ascolto di messaggi provenienti dai client.
     * Quando ne riceve, li notifica al controller tramite la virtualView
     */
    public void run() {
        //System.out.println("Match:run> Entro nel run");
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

/**
 * Thread che gestisce la creazione della partita e le connessioni ad essa.
 */
public class Match extends Thread{
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;

    /** Classe che gestisce la logica di gioco */
    private final GameLogic gameLogic; //Partita vera e propria

    /** Classe da "riempire" con le informazioni sui client man mano che si connettono. Serve a
     * gameLogic per sapere quando iniziare la partita */
    private InformationFromNetworking informationFromNetworking;

    /** CommunicationInterface del client che crea la partita*/
    private PlayerCommunicationInterface hostConnection; //primo client che si collega alla partita

    /** Id univoco del match*/
    private int id;

    /** Thread di lettura degli input buffer dei player*/
    private InputBufferReader inputBufferReader;

    /** Lista di player che si sono disconnessi */
    private List<PlayerId> disconnectedPlayers;

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
        DebugUtility.simpleDebugMessage("Creata partita (id = " + id + ")");
        addConnection(host);
        start();

    }

    /** metodo che gestisce l'inizializzazione della partita */
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

    /**
     * Metodo che invia ai client le informazioni necessarie per creare istanziare la view.
     * Viene richiamato appena la partita inizia.
     * @param playerNumber numero di giocatori connessi
     */
    public void sendViews(int playerNumber)
    {
        DebugUtility.simpleDebugMessage("Inizio invio view");

        //Se invocato, significa che la partita è iniziata, quindi la marco come tale
        networkInterface.setMatchStarted();

        DebugUtility.simpleDebugMessage("debug1");
        //spedisco le view ai client
        for(int i = 0; i<playerNumber; i++)
        {
            DebugUtility.simpleDebugMessage("debug2");
            //System.out.println("Match : sendViews()> entrato nel ciclo!");
            PlayerId currentPlayerId = PlayerId.values()[i];
            DebugUtility.simpleUserMessage(/*"Match : sendViews()>*/"Invio view al player " + currentPlayerId);
            networkInterface.sendToClient(new ViewMessage(currentPlayerId, playerNumber), currentPlayerId);
        }

    }

    /**
     * Aggiunge un giocatore alla partita, inserisce le sue informazioni in informationFromNetworking
     * @param clientConnection PlayerCommunicationInterface del clientche si sta connettendo
     */
    public void addConnection(PlayerCommunicationInterface clientConnection)
    {
        if ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            return; //sarebbe meglio gestire la cosa in modo diverso: si è tentato di aggiungere un player ad una partita già completa
        }

        this.networkInterface.addConnection(clientConnection);

        TypeOfView tov = clientConnection.getConnectionMessage().getTypeOfView();
        String username = clientConnection.getConnectionMessage().getUsername();
        this.informationFromNetworking.addPlayerViewChoice(tov, username);

        clientConnection.switchOn();

        if( inputBufferReader == null)
            this.connectedPlayers++;
        else
            inputBufferReader.incConnectedPlayers();

        networkInterface.printPlayerTable();
    }

    /**
     * Disconnette un player dal match, aggiungendo una nuovo elemento alla lista di disconnessioni e
     * notificando la networkInterface
     * @param playerId playerId del player da disconnettere
     */
    public void disconnectPlayer(PlayerId playerId)
    {
        DebugUtility.simpleDebugMessage("disconnessione in corso del player "+playerId);
        disconnectedPlayers.add(playerId);
        networkInterface.notifyPlayerDisconnection(playerId);
    }

    /**
     * Riconnette un giocatore precedentemente disconnesso.
     * @param newCommunicationInterface nuova PlayerCommunicationInterface per la comunicazione con il client
     * @param playerId playerId del giocatore
     * @param sendViews indica se si devono inviare al client le informazioni per creare la view. Durante la partita
     *                  è a true, se il match è ancora in attesa di player è a false (le info verranno inviate alla
     *                  partenza del match)
     */
    public void reconnectPlayer(PlayerCommunicationInterface newCommunicationInterface, PlayerId playerId, boolean sendViews)
    {
        disconnectedPlayers.remove(playerId);
        PlayerCommunicationInterface oldCommunicationInterface = networkInterface.playerIdToConnection(playerId);
        networkInterface.notifyPlayerReconnection(newCommunicationInterface, playerId);
        if(sendViews)
            networkInterface.sendToClient(new ViewMessage(playerId, informationFromNetworking.getPlayerNameList().size()), playerId);

        networkInterface.sendHistory(playerId);

        newCommunicationInterface.reopenClosedConnection(oldCommunicationInterface);
    }

    public PlayerId connectionToPlayerId(PlayerCommunicationInterface playerCommunicationInterface)
    {
        return networkInterface.connectionToPlayerId(playerCommunicationInterface);
    }

    public List<PlayerId> getDisconnectedPlayers() {
        return disconnectedPlayers;
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
