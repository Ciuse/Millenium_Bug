package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.server.serverNetworking.ConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread {
    private final int MAX_PLAYER_NUMBER = 4;
    private final NetworkInterface networkInterface;
    private ConnectionInterface hostConnection; //primo client che si collega alla partita
    private int id;
    private Model model;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(ConnectionInterface host, int id){
        this.networkInterface = new NetworkInterface();
        this.hostConnection = host;
        this.id = id;
    }

    /* Getters & Setters*/
    public int getMatchId()
    {
        return this.id;
    }

    /* Run() Method*/
    @Override
    public void run()
    {
        //Instanzia connessione con il primo client
        this.networkInterface.addConnection(hostConnection);

        //Rimane in attesa degli altri giocatori


        //Avvia partita
        //Fa cose alla fine della partita?
    }

    public boolean addConnection(ConnectionInterface clientConnection)
    {
        if ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            //TODO: eccezione
        }

        this.networkInterface.addConnection(clientConnection);
        boolean started = ( this.networkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER );
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
