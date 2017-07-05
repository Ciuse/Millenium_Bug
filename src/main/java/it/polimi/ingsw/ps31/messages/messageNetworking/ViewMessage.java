package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Francesco on 24/06/2017.
 */
public class ViewMessage extends NetworkingMessage{
    private PlayerId playerId;
    private int maxPlayerNumber;

    /* Constructor */
    public ViewMessage(PlayerId playerId, int maxPlayerNumber)
    {
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
