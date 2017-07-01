package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 15/05/2017.
 *
 * Classe astratta che unifica tutte le risorse di tipo Point: FaithPoint,MilitaryPoint,VictoryPoint
 * Utile per poter calcolare il valore di tutte le risorse di tipo Point quando si è all'interno di una lista di risorse
 * @see Resource
 */
public abstract class PointResource extends Resource {

    public PointResource(int value) {
        super(value);
    }

    @Override
    public int getPhysicalResourceValue() {
        return 0;
    }

    @Override
    public int getPointResourceValue() {
        return this.getValue();
    }

    public abstract String getPointResourceType();
}
