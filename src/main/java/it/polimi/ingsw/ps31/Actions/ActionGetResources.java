package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

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
            //TODO: mi serve l'iteratore per ResourceList
            //player.addResources(this.resourcesToGet);

            this.resetResourcesToGet();
        }
    }
}
