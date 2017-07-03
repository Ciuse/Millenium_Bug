package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 14/05/2017.
 * Classe raffigurante la risorsa Servant
 * @see Resource
 */
public class Servant extends PhysicalResource {

    public Servant(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new Servant(resource.getValue());
    }

    @Override
    public String toString(){
        return "SE"+this.getValue();
    }
}
