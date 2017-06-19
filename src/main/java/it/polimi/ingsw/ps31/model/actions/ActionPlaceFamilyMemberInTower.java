package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.board.TowerActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerActionSpace towerActionSpace;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Getters & Setters */
    public TowerActionSpace getTowerActionSpace() {
        return towerActionSpace;
    }

    public void setTowerActionSpace(TowerActionSpace towerActionSpace) {
        this.towerActionSpace = towerActionSpace;
    }

    /* Resetters */
    public void resetActionSpace()
    {
        this.towerActionSpace = null;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if ( this.familyMember == null || this.towerActionSpace == null || !this.towerActionSpace.isTowerSpace())
        {
            //TODO: gestire (eccezione?)
        } else
        {
            //Eseguo i controlli
           if ( actionControlSet.towerPlacementControl(familyMember, this.towerActionSpace.getTowerCardSpace()) )
           {
               this.towerActionSpace.addFamilyMember(familyMember); //TODO: chi attiva gli effetti??
               player.setLastUsedFamilyMember(familyMember);
           }

           resetActionSpace();
           resetFamilyMember();
        }
    }

    @Override
    public String toString() {
        return "Place FM in Tower";
    }
}
