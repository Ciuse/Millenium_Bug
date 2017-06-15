package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.model.StateModel.StateInfo;
import it.polimi.ingsw.ps31.model.StateModel.StateVisitor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.message.MexStateInfo;
import it.polimi.ingsw.ps31.server.message.MexVisitable;
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
        boolean exit = false;
        while (!exit)
        {
            String msgFromClient = networkInterface.readFromClient(PlayerId.ONE);
            if( msgFromClient.equals("closedConnection") )
                exit = true;
            else
                setState(msgFromClient, PlayerId.ONE);
        }
    }

    public void setState(String newState, PlayerId sender)
    {
        System.out.println("Model> Messaggio in ingresso: '" + newState + "' proveniente dal player "+sender);

        this.state = newState;
        generateAnswer(sender);
    }

    private void generateAnswer(PlayerId sendBackTo)
    {
        this.state = new StringBuilder(this.state).reverse().toString();

        System.out.println("Model: generateAnswer()> stato modificato. Novo stato: "+this.state);

        //genero l'oggetto di risposta
        MexProva answerObj = new MexProva(this.state);

        notifyState(sendBackTo, answerObj);


    }

    public void notifyState(PlayerId receiver)
    {
        networkInterface.sendToClient(this.state, receiver);
        System.out.println("Server> Risposta spedita: "+this.state+". In attesa di ulteriore comando...");

    }

    public void notifyState(PlayerId receiver, MexProva obj)
    {
        System.out.println("Model: notifyState()> Metodo richiamato. Invio messaggio "+obj+" al playerId "+receiver);

        System.out.println("Server> receiver: "+receiver+"; obj: "+obj);
        networkInterface.sendToClient(obj, receiver);
    }

}
