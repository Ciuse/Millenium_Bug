package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.Tower;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public class SelfOccupiedTowerControl extends Control {
    private Tower tower = null;
    private FamilyMember familyMember = null;

    /* Constructor */
    public SelfOccupiedTowerControl(Player player) {
        super(player);
    }



    /*Getters & Setters */
    public void setTower(Tower tower)
    {
        this.tower = tower;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember = familyMember;
    }

    @Override
    public String getControlStringError() {
        return "non puoi mettere due family member non neutri nella torre di colore ";
    }

    /*Reset Method*/
    public void resetFamilyMember()
    {
        this.familyMember = null;
    }

    public void resetTowerCardSpace()
    {
        this.tower = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if (this.familyMember.getDiceColor() == DiceColor.NEUTRAL)
            return true;

        Player fmOwner = familyMember.getPlayer();
        boolean azionePossibile = true;

        for (TowerCardSpace tcs : tower.getTowerCardSpaceList()) {
            for (FamilyMember family : tcs.getActionSpace().getFamilyMemberList()
                    ) {
                if (!family
                        .getDiceColor()
                        .equals(DiceColor.NEUTRAL)
                        && family
                        .getPlayer()
                        .equals(fmOwner))
                    azionePossibile = false;
            }

        }

        resetFamilyMember();
        resetTowerCardSpace();
        return azionePossibile;
    }
}
