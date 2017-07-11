package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Spazio azione del raccolto piccolo, il quale ha un limite di una pedina
 */
public class SmallHarvest extends Harvest {

    /* Constructor */
    public SmallHarvest(int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
