package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 *
 * Azione generica per poter aggiungere risorse alla lista principale del player
 *
 * @see Player
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

    /* Resetters */
    public void resetResourcesToGet()
    {
        this.resourcesToGet = null;
    }

    /* Class Methods */
    @Override
    public void activate() {
        //Eseguo l'azione
        List<Resource> resourcesToGetList = this.resourcesToGet.getListOfResource();
        for (Resource currentResource : resourcesToGetList) {
            currentResource.addResource(super.player);
        }
        resetResourcesToGet();
    }
}
