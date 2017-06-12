package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class Stone extends PhysicalResource {
    public Stone(int value) {
        super(value);
    }

    @Override
    public String toString(){
        return "ST"+"["+this.getValue()+"]";
    }
}
