package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.gameThings.*;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PlayerResources {

    private final ResourceList playerResourceList;

    /* Constructor */
    public PlayerResources(ResourceList resourceList) {
        playerResourceList=resourceList;
    }

    /* Setters & Getters */
    public void addResources(Resource resourceToAdd){

    }

    public void subResources(Resource resourceToSub) {
    }

    public int getResourceValue(Class<? extends Resource> resourceClass) throws NullPointerException{
        return playerResourceList.getSpecificResource(resourceClass).getValue();
    }
    protected ResourceList getPlayerResourceList(){
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
