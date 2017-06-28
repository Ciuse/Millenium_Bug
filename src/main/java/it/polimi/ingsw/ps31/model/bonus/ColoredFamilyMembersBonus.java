package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ColoredFamilyMembersBonus extends Bonus {
    private final int bonus; //riduzione del valore dei familiari colorati quando li piazzo su uno spazio azione(scomunica (-1)) ma anche leader
                                //lucrezia borgia ,che da +2 sul valore dei familiari colorati

    public ColoredFamilyMembersBonus(int bonus) {
        super();
        this.bonus = bonus;
    }

    @Override
    public void activate(Player player) {
        for (FamilyMember familyMember:player.getFamilyMembers()
             ) {
            if(!familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                familyMember.addPermanentAdditionalValue(bonus);
            }
        }

    }
}
