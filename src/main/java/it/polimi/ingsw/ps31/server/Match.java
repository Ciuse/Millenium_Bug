package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.model.GenericModel;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelProva;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerNetworkInterface;

/**
 * Created by Francesco on 08/06/2017.
 */
public class Match extends Thread {
    private final int MAX_PLAYER_NUMBER = PlayerId.values().length;
    private final ServerNetworkInterface serverNetworkInterface;
    private ServerConnectionInterface hostConnection; //primo client che si collega alla partita
    private int id;
    private Model model;

    private GenericModel modelProva;

    //private List<Socket> sockets = new ArrayList<>();

    /* Constructor */
    public Match(ServerConnectionInterface host, int id){
        this.serverNetworkInterface = new ServerNetworkInterface(this);
        this.modelProva = new ModelProva(this, serverNetworkInterface);
        this.serverNetworkInterface.setModelProva(modelProva);
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
        this.serverNetworkInterface.addConnection(hostConnection);

        //Rimane in attesa degli altri giocatori


        //Avvia partita

        modelProva.startModel();
        System.out.println("Match:run()> all listened connections closed. Shutting down match");
        serverNetworkInterface.closeAll();

        //Fa cose alla fine della partita?
    }

    public boolean addConnection(ServerConnectionInterface clientConnection)
    {
        if ( this.serverNetworkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER )
        {
            //TODO: eccezione
            return false;
        }

        this.serverNetworkInterface.addConnection(clientConnection);
        boolean started = ( this.serverNetworkInterface.getConnectionListSize() == MAX_PLAYER_NUMBER );

        serverNetworkInterface.printPlayerTable();

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
