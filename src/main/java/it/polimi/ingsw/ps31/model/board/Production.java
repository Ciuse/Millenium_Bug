package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe che rappresenta una produzione generica
 */
public abstract class Production extends ActionSpace {
    public Production(int diceCost, int familyMemberLimit, EffectList effectList) {
        super(diceCost, familyMemberLimit, effectList);
    }

}