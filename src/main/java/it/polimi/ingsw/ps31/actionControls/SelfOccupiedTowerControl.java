package it.polimi.ingsw.ps31.actionControls;

import it.polimi.ingsw.ps31.board.Tower;
import it.polimi.ingsw.ps31.board.TowerCardSpace;
import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.player.FamilyMember;
import it.polimi.ingsw.ps31.player.Player;

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
    public Tower getTower()
    {
        return tower;
    }

    public void setTower(Tower tower)
    {
        this.tower = tower;
    }

    public FamilyMember getFamilyMember()
    {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember = familyMember;
    }

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
    public boolean execute()
    {
        //Controllo che i parametri siano settati
        if ( this.tower == null || this.familyMember == null )
        {
            //TODO: eccezione
            return false;
        }else
        {
            if(this.familyMember.getDice().getColor() == DiceColor.NEUTRAL)
                return true;

            Player fmOwner = this.familyMember.getPlayer();
            boolean azionePossibile = true;
            for (TowerCardSpace tcs : this.tower.getTowerCardSpaceList())
            {
                FamilyMember currentFamilyMember = tcs.getActionSpace().getFamilyMemberIndex(0);
                if(!currentFamilyMember.getDice().getColor().equals(DiceColor.NEUTRAL) && currentFamilyMember.getPlayer().equals(fmOwner))
                    azionePossibile = false;
            }

            return azionePossibile;
        }
    }
}
