package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class AddValueNeutralFamilyMemberBonus extends Bonus {
    private final int bonusNeutralFamilyMember;

    protected AddValueNeutralFamilyMemberBonus(int bonusNeutralFamilyMember) {
        super();
        this.bonusNeutralFamilyMember = bonusNeutralFamilyMember;
    }

    @Override
    public void activate(Player player) {
        player.getActionControlSet().getDiceValueVsDiceColorControl().
                addPermanentValueAtSpecificMember(DiceColor.NEUTRAL, bonusNeutralFamilyMember);
    }
}
