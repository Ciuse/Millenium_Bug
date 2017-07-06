package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.GetResourceEffect;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 26/05/2017.
 */
public class TowerCardCostPlacementControl extends Control {
    private TowerCardSpace towerCardSpace = null;
    private Map<CardColor, ResourceList> cardResourceDiscount;

    /* Constructor */
    public TowerCardCostPlacementControl(Player player)
    {
        super(player);
        this.cardResourceDiscount = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardResourceDiscount.put(cardColor, null);
    }


    /* Setters & Getters */
    public void setTowerCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    @Override
    public String getControlStringError() {
        return "non puoi piazzare il familiare";
    }

    /* Resetters */
    public void resetTowerCardSpace()
    {
        this.towerCardSpace = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {

        boolean costAffordable = true;

        ResourceList tempPlayerResources = new ResourceList(player.getPlayerResources().getListOfResource());

        //simulo il poter pagare o no la torre
        if (towerCardSpace.getTower().isOccupied()) {
            if (player.getPlayerActionSet().getPayTowerMoney().isToPay()) {
                if (player.getPlayerActionSet().getActionControlSet().payResourceControl(player.getPlayerActionSet().getPayTowerMoney().getCOINTOPAY())) {
                    tempPlayerResources.subSpecificResource(player.getPlayerActionSet().getPayTowerMoney().getCOINTOPAY());
                    costAffordable = true;

                }
            } else {
                costAffordable = false;
            }
        }
        //simulo l attivazione dell effetto che mi fa guadagnare risorse dell action space (se ha un effetto che mi fa guadagnare risorse)
        if(towerCardSpace.getActionSpace().getImmediateEffectList()!=null){
            if(towerCardSpace.getActionSpace().getImmediateEffectList().getEffectList().get(0).getResourceToGainString()!=null){
                GetResourceEffect tempGetRes = (GetResourceEffect) towerCardSpace.getActionSpace().getImmediateEffectList().get(0);
                for (Resource resource: tempGetRes.getResources().getListOfResource()
                     ) {
                    tempPlayerResources.addSpecificResource(resource);
                }
            }
        }

        //simulo il dover pagare la carta
        if (towerCardSpace.getCard().getCostList() == null)
            costAffordable = true;
        else
            for (ResourceList currentCost : towerCardSpace.getCard().getCostList()) {
                if (!currentCost.lessOrEquals(tempPlayerResources))
                    costAffordable = false;
            }

        resetTowerCardSpace();
        return costAffordable;
    }
    public void addCardResourceDiscount(CardColor cardColor, boolean anyColor, ResourceList discountList)
    {
        if(anyColor){
            ResourceList currentDiscount1 = this.cardResourceDiscount.get(CardColor.GREEN);
            currentDiscount1.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.GREEN, currentDiscount1);
            ResourceList currentDiscount2 = this.cardResourceDiscount.get(CardColor.BLUE);
            currentDiscount2.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.BLUE, currentDiscount2);
            ResourceList currentDiscount3 = this.cardResourceDiscount.get(CardColor.YELLOW);
            currentDiscount3.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.YELLOW, currentDiscount3);
            ResourceList currentDiscount4 = this.cardResourceDiscount.get(CardColor.PURPLE);
            currentDiscount4.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.PURPLE, currentDiscount4);
        }
        else {
            if(cardColor!=null) {
                ResourceList currentDiscount = this.cardResourceDiscount.get(cardColor);
                currentDiscount.discountResourceList(discountList);
                cardResourceDiscount.put(cardColor, currentDiscount);
            }
        }
    }
}
