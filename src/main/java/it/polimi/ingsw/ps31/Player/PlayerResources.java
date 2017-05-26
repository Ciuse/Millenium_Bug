package it.polimi.ingsw.ps31.Player;

import com.sun.org.apache.regexp.internal.RE;
import it.polimi.ingsw.ps31.GameThings.*;

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
            this.currentResources.get(resourceName).addValue(resourceToAdd.getValue());
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
