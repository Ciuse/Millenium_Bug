package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe di un raccolto generico
 */
public abstract class Harvest extends ActionSpace {

    public Harvest(int diceCost, int familyMemberLimit, EffectList effectList) {
        super(diceCost, familyMemberLimit, effectList);
    }

}
