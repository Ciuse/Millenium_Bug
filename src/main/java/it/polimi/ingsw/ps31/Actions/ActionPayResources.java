package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionPayResources extends Action{
    private ResourceList resourceToPay = null;

    /* Constructor */
    public ActionPayResources(Player player)
    {
        super(player);
    }

    /* Setter & Getter */
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
            //TODO: mi serve l'iteratore per ResourceList
            //player.subResources(this.resourcesToGet);
            //this.resourcesToGet = null;

            //Inizializzo il controllo
           // player.getPlayerActionSet().getActionControlSet().

        }


    }
}
