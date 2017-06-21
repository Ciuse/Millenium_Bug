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

    @Override
    public String getControlStringError() {
        return "non puoi mettere due family member non neutri nella stessa torre"+tower.getColor().name();
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
            if(this.familyMember.getDiceColor() == DiceColor.NEUTRAL)
                return true;

            Player fmOwner = this.familyMember.getPlayer();
            boolean azionePossibile = true;
            for (TowerCardSpace tcs : this.tower.getTowerCardSpaceList())
            {
                FamilyMember currentFamilyMember = tcs.getActionSpace().getFamilyMemberIndex(0);
                if(!currentFamilyMember.getDiceColor().equals(DiceColor.NEUTRAL) && currentFamilyMember.getPlayer().equals(fmOwner))
                    azionePossibile = false;
            }

            return azionePossibile;
        }
    }
}
