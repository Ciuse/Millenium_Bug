package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.TowerActionSpace;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerActionSpace towerActionSpace;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
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
        }
    }
}
