package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.networking.MexProva;
import it.polimi.ingsw.ps31.server.serverNetworking.ServerNetworkInterface;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ModelProva extends GenericModel{
    private MexProva state;
    private Match match;
    private ServerNetworkInterface serverNetworkInterface;

    public ModelProva(Match match, ServerNetworkInterface serverNetworkInterface) {
        this.match = match;
        this.serverNetworkInterface = serverNetworkInterface;
    }

    public void startModel()
    {
        boolean exit = false;
        while (!exit)
        {
            MexProva msgFromClient = serverNetworkInterface.readFromClient(PlayerId.ONE);
            if( msgFromClient.visit().equals("closedConnection") )
                exit = true;
            else
                setState(msgFromClient, PlayerId.ONE);
        }
    }

    public void setState(MexProva newState, PlayerId sender)
    {
        this.state = newState;
        generateAnswer(sender);
    }

    private void generateAnswer(PlayerId sendBackTo)
    {

        this.state = new MexProva(new StringBuilder(this.state.visit()).reverse().toString());

        System.out.println("Model: generateAnswer()> stato modificato. Novo stato: "+this.state);

        //genero l'oggetto di risposta
        MexProva answerObj = this.state;

        notifyState(sendBackTo, answerObj);


    }

    public void notifyState(PlayerId receiver)
    {
        serverNetworkInterface.sendToClient(this.state, receiver);
        System.out.println("Server> Risposta spedita: "+this.state.visit()+". In attesa di ulteriore comando...");

    }

    public void notifyState(PlayerId receiver, MexProva obj)
    {
        serverNetworkInterface.sendToClient(obj, receiver);
    }

}
