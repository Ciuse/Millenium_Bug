package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 14/05/2017.
 */
public class Servant extends PhysicalResource {

    public Servant(int value) {
        super(value);
    }

    @Override
    public String toString(){
        return "SE "+this.getValue();
    }
}
