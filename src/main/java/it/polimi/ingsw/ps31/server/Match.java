package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameLogic;
import it.polimi.ingsw.ps31.model.game.InformationFromNetworking;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread {
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;
    private final GameLogic gameLogic; //Partita vera e propria
    private InformationFromNetworking informationFromNetworking;
    private ServerConnectionInterface hostConnection; //primo client che si collega alla partita
    private int id;

    //Attibuti di test
    private Model model;
    private ModelProva modelProva;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(ServerConnectionInterface host, int id){
        this.informationFromNetworking = new InformationFromNetworking();
        this.gameLogic = new GameLogic(informationFromNetworking);
        this.networkInterface = new NetworkInterface(this, this.gameLogic);
        this.networkInterface.setModelProva(modelProva);
        this.hostConnection = host;
        this.id = id;

        addConnection(host);
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
    @Override
    public void run()
    {
        //Metodo invocato solo alla fine della connessione di tutti i player alla partita

        //todo ricevere le view da playgame()
        List<View> clientViewList = new ArrayList<>();
        //spedisco le view ai client
        for(int i = 0; i<4; i++)
            networkInterface.sendToClient(new ViewMessage(clientViewList.get(i)), PlayerId.values()[i]);

        //Avvia partita
        gameLogic.playGame();

        //Fa cose alla fine della partita?
    }

    public boolean addConnection(ServerConnectionInterface clientConnection)
    {
        if ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            //TODO: eccezione
            return true; //ma sarebbe meglio gestire la cosa in modo diverso: si è tentato di aggiungere un player ad una partita già completa
        }

        this.networkInterface.addConnection(clientConnection);

        TypeOfView tov = clientConnection.
                getConnectionMessage().
                getTypeOfView();
        String username = clientConnection.getConnectionMessage().getUsername();
        int playerNumber = this.informationFromNetworking.addPlayerViewChoice(tov, username);

        boolean started = ( playerNumber == MAX_PLAYER_NUMBER );

        networkInterface.printPlayerTable();

        return started;
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
