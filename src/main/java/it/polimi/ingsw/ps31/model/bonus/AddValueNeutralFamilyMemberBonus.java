package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class AddValueNeutralFamilyMemberBonus extends Bonus {
    private final int bonusNeutralFamilyMember;

    protected AddValueNeutralFamilyMemberBonus(Action actionToModify, int bonusNeutralFamilyMember) {
        super(actionToModify);
        this.bonusNeutralFamilyMember = bonusNeutralFamilyMember;
    }

    @Override
    public void activate(Player player) {

    }
}
