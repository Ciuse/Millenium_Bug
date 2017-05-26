package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.Player.FamilyMember;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 25/05/2017.
 */
public class PlacedFamilyMemberControl extends Control {
    private FamilyMember familyMember = null;

    /* Constructor */
    public PlacedFamilyMemberControl(Player player) {
        super(player);
    }

    /* Getters & Setters */
    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public void resetFamilyMember() {
        this.familyMember = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if ( this.familyMember == null )
        {
            //TODO: gestire
            return false;
        }

        boolean ret = (this.familyMember.isPlaced());
        resetFamilyMember();

        return ret;
    }
}
