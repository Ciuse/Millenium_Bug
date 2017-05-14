package it.polimi.ingsw.ps31.GameThings;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Resource {
    private int value;

    public Resource(int value){
        this.value=value;

    }

    public void addValue(int value){

        this.value=this.value+value;

    }

    public int getValue(){
        return this.value;
    }



}
