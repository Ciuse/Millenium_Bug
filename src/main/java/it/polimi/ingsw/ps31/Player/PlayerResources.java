package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PlayerResources {

    private ResourceList currentResources = new ResourceList();

    /* Constructor */
    public PlayerResources(ResourceList initialResources)
    {
        this.currentResources = initialResources;
    }

    /* Setters & Getters */
    public void setResources()
    {
        //TODO: implementare
    }

    public ResourceList getResources()
    {
        //TODO: implementare
        return new ResourceList(); //Istruzione inutile, scritta solo per consentire la compilazione
    }
}
