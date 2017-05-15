package it.polimi.ingsw.ps31.Board;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Harvest extends ActionSpace {

    public Harvest(int diceCost, int familyMemberLimit)
    {
        super(diceCost, familyMemberLimit, null);    //TODO: l'effetto immediato è l'attivazione del raccolto
    }

}
