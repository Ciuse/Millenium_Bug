package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.TowerActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceTowerActionSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerActionSpace towerActionSpace;
    private boolean immediateEffectsAreActivable = true;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Getters & Setters */
    public TowerActionSpace getTowerActionSpace() {
        return towerActionSpace;
    }

    public void setTowerActionSpaceChosen(TowerActionSpace towerActionSpaceChosen) {
        this.towerActionSpace = towerActionSpaceChosen;
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
        super.notifyViews(new MVAskChoice(player.getPlayerId(),"Quale family member vuoi usare?",new ChoiceFamilyMember()));
        this.familyMember=super.waitFamilyMemberChosen();

        super.notifyViews(new MVAskChoice(player.getPlayerId(),"In quale tower action space vuoi mettere il tuo family member?",new ChoiceTowerActionSpace()));
        this.towerActionSpace=super.waitTowerActionSpaceChosen();

        //chiedo se vuole pagare dei servitori
        player.getPlayerActionSet().payServants(super.familyMember);

        //Controllo che i parametri siano settati
        if ( this.familyMember == null || this.towerActionSpace == null || !this.towerActionSpace.isTowerSpace())
        {
            //TODO: gestire (eccezione?)
        } else
        {
            //Eseguo i controlli
           if ( actionControlSet.towerPlacementControl(familyMember, this.towerActionSpace.getTowerCardSpace()) )
           {
               this.towerActionSpace.addFamilyMember(familyMember);
               player.setLastUsedFamilyMember(familyMember);
               if(immediateEffectsAreActivable)
                   towerActionSpace.activeEffectList(player);
           }else  super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTowerPlacementControl().getControlStringError()));
           setUsed(true);
           resetActionSpace();
           resetFamilyMember();
            super.notifyViews(new MVUpdateState("Aggiornato stato family member",familyMember.getStateFamilyMember()));
            super.notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella tower",towerActionSpace.getStateActionSpace()));
        }
    }

    @Override
    public String toString() {
        return "Place FM in Tower";
    }

    /* Modifiers */
    public void setImmediateEffectsAreActivable(boolean areActivable)
    {
        this.immediateEffectsAreActivable = areActivable;
    }
}
