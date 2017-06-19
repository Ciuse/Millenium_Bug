package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.actions.ActionControlSet;

/**
 * Created by Francesco on 19/06/2017.
 */
public class BonusActivator {
    private final PlayerActionSet playerActionSet;
    private final ActionControlSet actionControlSet;
    private final Player player;

    /* Constructor */
    public BonusActivator(PlayerActionSet playerActionSet, ActionControlSet actionControlSet, Player player)
    {
        this.playerActionSet = playerActionSet;
        this.actionControlSet = actionControlSet;
        this.player = player;
    }

    public void addValueNeutralFamilyMember(){}
    public void canPlaceInOccupatedActionSpace(){}
    public void cantPlaceInActionSpace(){}
    public void cardDiscountBonus(){}
    public void coloredfamilyMemberBonus(){}
    public void discountOnOccupatedTowerBox(){}
    public void extraFinalVictoryPointsBonus(){}
    public void harvestBonus(){}
    public void lostFinalVictoryPointBonus(){}
    public void lostFinalVictoryPointFromCardCost(){}
    public void lostFinalVictoryPointFromPlayerResources(){}
    public void modifyPlayerServantsBonus(){}
    public void noImmediateEffectBonus(){}
    public void productionBonus(){}
    public void resourceBonus(){}



}
