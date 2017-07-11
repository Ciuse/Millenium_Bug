package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Francesco on 24/06/2017.
 *
 * Messaggio iniziale che parte dal Match contenente le informazioni per permettere alla view
 * destinataria di crearsi in modo dinamico in base all id del player della view e al numero
 * di giocatori presente in una partita
 *
 * @see NetworkingMessage
 */
public class ViewMessage extends NetworkingMessage{
    private PlayerId playerId;
    private int maxPlayerNumber;

    /* Constructor */
    public ViewMessage(PlayerId playerId, int maxPlayerNumber)
    {
        super();
        this.playerId = playerId;
        this.maxPlayerNumber = maxPlayerNumber;
    }

    /* Getters */
    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public int getMaxPlayerNumber()
    {
        return maxPlayerNumber;
    }

    /* Astract Methods Implementation */
    public ConcreteEnvelope wrap()
    {
        return new ConcreteEnvelope(this);
    }

    @Override
    public String toString()
    {
        return "[VIEW_MESSAGE| PlayerId="+playerId+"; maxPlayerNumber="+maxPlayerNumber+"]";
    }

}
