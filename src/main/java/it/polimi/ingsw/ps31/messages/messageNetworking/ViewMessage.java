package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Francesco on 24/06/2017.
 */
public class ViewMessage extends NetworkingMessage{
    private PlayerId playerId;
    private int maxPlayerNumber;

    public ViewMessage(PlayerId playerId, int maxPlayerNumber)
    {
        this.playerId = playerId;
        this.maxPlayerNumber = maxPlayerNumber;
    }

    public PlayerId getPlayerId()
    {
        return playerId;
    }

    public int getMaxPlayerNumber()
    {
        return maxPlayerNumber;
    }

    @Override
    public String toString()
    {
        return "[VIEW_MESSAGE| PlayerId="+playerId+"; maxPlayerNumber="+maxPlayerNumber+"]";
    }

}
