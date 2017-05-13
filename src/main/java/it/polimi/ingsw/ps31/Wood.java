package it.polimi.ingsw.ps31;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public class Wood extends PhysicalResource {

    public Wood(int value) {
        super(value);
    }

    public String toString() {
        return "["+this.getValue()+"]";
    }

}
