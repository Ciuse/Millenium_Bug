package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 23/05/2017.
 */
public class PayResources extends Action{
    private ResourceList resourceToPay = null;

    public PayResources(Player player)
    {
        super(player);
    }

    public void setResourceToPay (ResourceList resourcesToPay)
    {
        this.resourceToPay = resourceToPay;
    }

    public void resetResourcesToGet()
    {
        this.resourceToPay = null;
    }

    @Override
    public void activate()
    {
        if ( this.resourceToPay == null )
        {
            //TODO: fare cose
        }
        else
        {
            //player.subResources(this.resourcesToGet);
            //this.resourcesToGet = null;
        }


    }
}
