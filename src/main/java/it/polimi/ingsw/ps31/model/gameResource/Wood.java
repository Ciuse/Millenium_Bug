package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public class Wood extends PhysicalResource {

    public Wood(int value) {
        super(value);
    }


    @Override
    public String toString(){
        return "WO "+this.getValue();
    }
}
