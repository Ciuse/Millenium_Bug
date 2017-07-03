package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 11/05/2017.
 * Classe raffigurante la risorsa Stone
 * @see Resource
 */
public class Stone extends PhysicalResource {
    public Stone(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new Stone(resource.getValue());
    }

    @Override
    public String toString(){
        return "ST"+this.getValue();
    }
}
