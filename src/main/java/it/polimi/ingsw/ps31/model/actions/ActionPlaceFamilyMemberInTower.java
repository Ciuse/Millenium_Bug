package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.board.TowerActionSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerActionSpace towerActionSpace;
    private Map<CardColor, Integer> cardDiceBonuses;
    private boolean immediateEffectsAreActivable = true;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
        this.cardDiceBonuses = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardDiceBonuses.put(cardColor, 0);
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
               if(immediateEffectsAreActivable)
                   towerActionSpace.activeEffectList(player);
           }
           setUsed(true);
           resetActionSpace();
           resetFamilyMember();
        }
    }

    @Override
    public String toString() {
        return "Place FM in Tower";
    }

    /* Modifiers */
    public void addCardDiceBonus (CardColor cardColor, Integer bonus)
    {
        //bonus può anche essere negativo (es. scomuniche)
        Integer currentBonus = this.cardDiceBonuses.get(cardColor);
        cardDiceBonuses.put(cardColor, currentBonus+bonus);

    }

    public void setImmediateEffectsAreActivable(boolean areActivable)
    {
        this.immediateEffectsAreActivable = areActivable;
    }
}
