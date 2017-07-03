package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 14/05/2017.
 *
 * Classe raffigurante la risorsa Coin.
 * @see Resource
 */

public class Coin extends PhysicalResource {

    public Coin(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new Coin(resource.getValue());
    }

        @Override
    public String toString(){
        return "CO"+this.getValue();
    }
}