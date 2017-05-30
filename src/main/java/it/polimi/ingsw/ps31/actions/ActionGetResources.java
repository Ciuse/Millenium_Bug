package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.gameThings.CouncilPrivilege;
import it.polimi.ingsw.ps31.gameThings.Resource;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionGetResources extends Action {
    private ResourceList resourcesToGet = null;

    /* Constructor */
    public ActionGetResources(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Setters & Getters */
    public void setResourcesToGet (ResourceList resourcesToGet)
    {
        this.resourcesToGet = resourcesToGet;
    }

    public ResourceList getResourcesToGet()
    {
        return this.resourcesToGet;
    }

    public void resetResourcesToGet()
    {
        this.resourcesToGet = null;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if (resourcesToGet == null)
        {
            //TODO: fare qualcosa (eccezione?)
        } else
        {
            //Eseguo l'azione
            List<Resource> resourcesToGetList = this.resourcesToGet.getResourceList();
            for(Resource currentResource : resourcesToGetList)
            {
                    player.addResources(currentResource);
                    //TODO: controllo per CouncilPrivilege
            }
            this.resetResourcesToGet();
        }
    }
}
