package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Harvest extends ActionSpace {

    public Harvest(int diceCost, int familyMemberLimit, EffectList effectList)
    {
        super(diceCost, familyMemberLimit, effectList);    //TODO: l'effetto immediato è l'attivazione del raccolto
    }

}
