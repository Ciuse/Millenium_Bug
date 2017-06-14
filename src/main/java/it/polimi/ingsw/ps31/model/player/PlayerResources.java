package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PlayerResources {

    private final ResourceList playerResourceList;

    /* Constructor */
    public PlayerResources(ResourceList resourceList) {
        this.playerResourceList=resourceList;
    }

    /* Setters & Getters */
    public void addResources(Resource resourceToAdd)
    {
        //controllo che il valore sia positivo
        if(resourceToAdd.getValue() < 0)
        {
            //todo eccezione
        }else
        {
            //Aggiungo la risorsa
            this.playerResourceList.addSpecificResource(resourceToAdd);
        }

    }

    public void subResources(Resource resourceToSub)
    {
        if(resourceToSub.getValue() < 0)
        {
            //todo eccezione
        }else
        {
            //Aggiungo la risorsa
            this.playerResourceList.subSpecificResource(resourceToSub);
        }
    }

    public int getResourceValue(Class<? extends Resource> resourceClass) throws NullPointerException{
        return playerResourceList.getSpecificResource(resourceClass).getValue();
    }

    public Resource getSpecificResource(Class<? extends Resource> resourceClass) throws NullPointerException {
        return playerResourceList.getSpecificResource(resourceClass);
    }
    public ResourceList getPlayerResourceList(){
        return this.playerResourceList;
    }

    public boolean greaterThan(ResourceList that){
        int contatore=0;
        for(int i=0; i<this.playerResourceList.size();i++){
            for(int j=0; j<that.size();j++){
                if(that.get(i).lessOrEquals(this.playerResourceList.get(j))){// confronto i vari elementi della lista con il metodo che ho implementato nel confronto tra risorse
                    contatore++;
                }
            }
        }
        if(contatore==that.size()){          // se tutte le mie risorse erano minore delle altre allora la mia lista è confrontabile ed è minore dell altra
            return true;
        }

        return false;
    }

}
