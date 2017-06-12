package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.serverNetworking.ConnectionInterface;
import it.polimi.ingsw.ps31.server.serverNetworking.NetworkInterface;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ModelProva {
    private String state;
    private Match match;
    private NetworkInterface networkInterface;

    public ModelProva(Match match, NetworkInterface networkInterface) {
        this.match = match;
        this.networkInterface = networkInterface;
    }

    public void startModel()
    {
        while (true)
        {
            networkInterface.readFromClient(PlayerId.ONE);
            networkInterface.readFromClient(PlayerId.TWO);
            networkInterface.readFromClient(PlayerId.THREE);
            networkInterface.readFromClient(PlayerId.FOUR);
        }
    }

    public void setState(String state, PlayerId sender)
    {
        System.out.println("Model> Messaggio in ingresso: " + state + " proveniente dal player "+sender);

        this.state = state;
        generateAnswer(sender);
    }

    private void generateAnswer(PlayerId sendBackTo)
    {
        this.state = new StringBuilder(this.state).reverse().toString();
        notifyState(sendBackTo);
    }

    public void notifyState(PlayerId receiver)
    {
        networkInterface.sendToClient(this.state, receiver);
        System.out.println("Server> Risposta spedita: "+this.state+". In attesa di ulteriore comando...");

    }

}
