package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionPayResources extends Action{
    private ResourceList resourceToPay = null;

    /* Constructor */
    public ActionPayResources(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Setter & Getter */
    public void setResourceToPay (ResourceList resourcesToPay)
    {
        this.resourceToPay = resourceToPay;
    }

    public void resetResourceToPay()
    {
        this.resourceToPay = null;
    }

    @Override
    public void activate()
    {
        if ( this.resourceToPay == null )
        {
            //TODO: fare cose
        } else
        {
            //Eseguo il controllo
            if ( this.actionControlSet.payResourceControl(this.resourceToPay) )
            {
                //Eseguo l'azione
                List<Resource> resourcesToGetList = this.resourceToPay.getResourceList();
                for(Resource currentResource : resourcesToGetList)
                    player.subResources(currentResource);

                resetResourceToPay();
            } else
            {
                //TODO: lanciare eccezione
            }
        }
    }
}
