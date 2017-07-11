package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 25/05/2017.
 */
public class PlacedFamilyMemberControl extends Control {
    private FamilyMember familyMember = null;

    /* Constructor */
    public PlacedFamilyMemberControl(Player player) {
        super(player);
    }

    @Override
    public String getControlStringError() {
        return null;
    }


    /* Getters & Setters */
    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public String getControlStringError(FamilyMember familyMember) {
        return "Non puoi usare il tuo family member "+familyMember.getDiceColor().name();
    }

    /* Resetters */
    public void resetFamilyMember() {
        this.familyMember = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if ( this.familyMember == null )
        {
            return false;
        }

        boolean ret = this.familyMember.isPlaced();
        resetFamilyMember();

        return ret;
    }
}
