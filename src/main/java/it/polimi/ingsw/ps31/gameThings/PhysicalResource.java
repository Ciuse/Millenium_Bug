package it.polimi.ingsw.ps31.gameThings;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public abstract class PhysicalResource extends Resource{

    public PhysicalResource(int value){
        super(value);
    }

    @Override
    public int getPhysicalResourceValue() {
        return this.getValue();
    }

    @Override
    public int getPointResourceValue() {
        return 0;
    }
}
