package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.gameThings.*;

import java.util.HashMap;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PlayerResources {

    private HashMap<String, Resource> currentResources = new HashMap<>();

    /* Constructor */
    public PlayerResources(int woodAmt, int stoneAmt, int coinAmt, int servantAmt)
    {
        //Inizializzo le risorse del giocatore con i valori iniziali

        this.currentResources.put("Wood", new Wood(woodAmt));
        this.currentResources.put("Stone", new Stone(stoneAmt)) ;
        this.currentResources.put("Coin", new Coin(coinAmt));
        this.currentResources.put("Servant", new Servant(servantAmt));

        this.currentResources.put("MilitaryStrength", new MilitaryStrength(0));
        this.currentResources.put("FaithPoint", new FaithPoint(0));
        this.currentResources.put("VictoryPoint", new VictoryPoint(0));
}

    /* Setters & Getters */
    public void addResources(Resource resourceToAdd)
    {
        String resourceName = new String(resourceToAdd.toStringName());

        if ( this.currentResources.containsKey(resourceName) )
            this.currentResources.get(resourceName).addValue(resourceToAdd.getValue()); //TODO: fare metodo add() nelle classi risorsa
        else
        {
            //TODO: gestire (eccezione?)
        }
    }

    public void subResources(Resource resourceToSub)
    {
        String resourceName = new String(resourceToSub.toStringName());

        if ( this.currentResources.containsKey(resourceName) )
            this.currentResources.get(resourceName).subValue(resourceToSub.getValue());
        else
        {
            //TODO: gestire (eccezione?)
        }
    }

    public Resource getResource(String resourceName)
    {
        if ( this.currentResources.containsKey(resourceName) )
            return this.currentResources.get(resourceName);
        else
        {
            //TODO: gestire (eccezione?)
            return null; //Istruzione messa solo per avere un return
        }
    }

    public ResourceList getPlayerResourceAsResourceList(){
        ResourceList resourceListToReturn = new ResourceList();
        resourceListToReturn.addSpecificResource(this.getResource("Wood"));
        resourceListToReturn.addSpecificResource(this.getResource("Stone"));
        resourceListToReturn.addSpecificResource(this.getResource("Coin"));
        resourceListToReturn.addSpecificResource(this.getResource("Servant"));
        resourceListToReturn.addSpecificResource(this.getResource("MilitaryStrength"));
        resourceListToReturn.addSpecificResource(this.getResource("FaithPoint"));
        resourceListToReturn.addSpecificResource(this.getResource("VictoryPoint"));
        return resourceListToReturn;
    }


    public HashMap getResourcesMap()
    {
        return new HashMap<>(this.currentResources);
    }

    public boolean greaterThan(ResourceList resourceList)
    {
        //TODO: implementare
        return true;
    }
}
