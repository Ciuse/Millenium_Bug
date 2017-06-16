package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 14/05/2017.
 */
public class Coin extends PhysicalResource {

    public Coin(int value) {
        super(value);
    }

    @Override
    public String toString(){
        return "CO"+this.getValue();
    }
}