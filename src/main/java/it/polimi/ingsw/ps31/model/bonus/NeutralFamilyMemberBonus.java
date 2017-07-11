package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Bonus che cambia il valore di default del famigliare Neutro
 *
 * @see FamilyMember
 */
public class NeutralFamilyMemberBonus extends Bonus {
    private final int bonusNeutralFamilyMember;

    public NeutralFamilyMemberBonus(int bonusNeutralFamilyMember) {
        super();
        this.bonusNeutralFamilyMember = bonusNeutralFamilyMember;
    }

    @Override
    public void activate(Player player) {
        for (FamilyMember familyMember:player.getFamilyMembers()
                ) {
            if(familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                familyMember.addPermanentAdditionalValue(bonusNeutralFamilyMember);
            }
        }
    }
}
