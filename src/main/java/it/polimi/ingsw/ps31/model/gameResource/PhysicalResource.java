package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 10/05/2017.
 *
 * Classe astratta che unifica tutte le risorse di tipo Physical: Coin,Servant,Wood,Stone
 * Utile per poter calcolare il valore di tutte le risorse fisiche quando si è all'interno di una lista di risorse
 * @see Resource
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
