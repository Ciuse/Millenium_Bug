package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.effect.GetResourceEffect;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.player.PlayerResources;

/**
 * Created by Francesco on 26/05/2017.
 */
public class TowerCostPlacementControl extends Control {
    private TowerCardSpace towerCardSpace = null;

    /* Constructor */
    public TowerCostPlacementControl(Player player)
    {
        super(player);
    }

    @Override
    public String getControlStringError() {
        return "non puoi piazzare il familiare";
    }

    /* Setters & Getters */
    public void setTowerCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    public TowerCardSpace getTowerCardSpace()
    {
        return towerCardSpace;
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

        PlayerResources tempPlayerResources = new PlayerResources(player.getPlayerResources().getPlayerResourceList());

        if (!towerCardSpace.getTower().isOccupied()) {

        } else {
            if (player.getPlayerActionSet().getActionControlSet().payResourceControl(player.getPlayerActionSet().getPayTowerMoney().getCOINTOPAY())) {
                tempPlayerResources.subResources(player.getPlayerActionSet().getPayTowerMoney().getCOINTOPAY());
                costAffordable = true;
            } else {
                costAffordable = false;
            }
        }

        //simulo l attivazione dell effetto che mi fa guadagnare risorse dell action space (se ha un effetto che mi fa guadagnare risorse)
        if(towerCardSpace.getActionSpace().getImmediateEffectList()!=null){
            if(towerCardSpace.getActionSpace().getImmediateEffectList().getEffectList().get(0).getResourceToGainString()!=null){
                GetResourceEffect tempGetRes = (GetResourceEffect) towerCardSpace.getActionSpace().getImmediateEffectList().get(0);
                for (Resource resource: tempGetRes.getResources().getResourceList()
                     ) {
                    tempPlayerResources.addResources(resource);
                }
            }
        }

        if (towerCardSpace.getCard().getCostList() == null)
            costAffordable = true;
        else
            for (ResourceList currentCost : towerCardSpace.getCard().getCostList()) {
                if (tempPlayerResources.greaterThan(currentCost))
                    costAffordable = true;
            }

        resetTowerCardSpace();
        return costAffordable;
    }

}
