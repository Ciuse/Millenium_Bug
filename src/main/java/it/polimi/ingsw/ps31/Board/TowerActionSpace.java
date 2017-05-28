package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.EffectList;

/**
 * Created by Francesco on 28/05/2017.
 */
public class TowerActionSpace extends ActionSpace {
    private final TowerCardSpace towerCardSpace;

    /* Constructor */
    public TowerActionSpace(int diceCost, int familyMemberLimit, EffectList immediateEffectList, TowerCardSpace towerCardSpace) {
        super(diceCost, familyMemberLimit, immediateEffectList);
        this.towerCardSpace = towerCardSpace;

    }

    /* Setters & Getters */
    public TowerCardSpace getTowerCardSpace()
    {
        return this.towerCardSpace;
    }
    public  boolean isTowerSpace()
    {
        return (this.towerCardSpace != null);
    }
}
