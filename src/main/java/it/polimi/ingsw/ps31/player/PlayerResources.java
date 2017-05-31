package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.gameThings.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PlayerResources {

    private HashMap<String, Resource> currentResources = new HashMap<>();
    private ResourceList playerResourceList;

    /* Constructor */
    public PlayerResources(ResourceList resourceList) {
        this.playerResourceList=resourceList;
    }

    /* Setters & Getters */
    public void addResources(Resource resourceToAdd){

    }

    public void subResources(Resource resourceToSub)
    {
    }


    public ResourceList getPlayerResourceList(){
        return this.playerResourceList;
    }


    public HashMap getResourcesMap()
    {
        return new HashMap<>(this.currentResources);
    }

}
