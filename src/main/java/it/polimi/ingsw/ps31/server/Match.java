package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.serverNetworking.ConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

import java.io.IOException;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread {
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final NetworkInterface networkInterface;
    private ConnectionInterface hostConnection; //primo client che si collega alla partita
    private int id;
    private Model model;

    private ModelProva modelProva;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(ConnectionInterface host, int id){
        this.networkInterface = new NetworkInterface(this);
        this.modelProva = new ModelProva(this, networkInterface);
        this.networkInterface.setModelProva(modelProva);
        this.hostConnection = host;
        this.id = id;
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
        //Instanzia connessione con il primo client
        this.networkInterface.addConnection(hostConnection);

        //Rimane in attesa degli altri giocatori


        //Avvia partita

        modelProva.startModel();

        //Fa cose alla fine della partita?
    }

    public boolean addConnection(ConnectionInterface clientConnection)
    {
        if ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            //TODO: eccezione
            return false;
        }

        this.networkInterface.addConnection(clientConnection);
        boolean started = ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER );

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
