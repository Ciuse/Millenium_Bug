package it.polimi.ingsw.ps31.GameThings;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class Stone extends Resource {
    public Stone(int value) {
        super(value);
    }

    public String toString() {
        return "["+this.getValue()+"]";
    }
}
