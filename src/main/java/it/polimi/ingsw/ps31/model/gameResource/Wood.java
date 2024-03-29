package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 10/05/2017.
 * Classe raffigurante la risorsa Wood
 * @see Resource
 */
public class Wood extends PhysicalResource {

    public Wood(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new Wood(resource.getValue());
    }


    @Override
    public String toString() {
        return "WO" + this.getValue();
    }
}
