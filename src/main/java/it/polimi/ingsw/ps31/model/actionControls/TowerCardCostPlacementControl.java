package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.ChangeResourceEffect;
import it.polimi.ingsw.ps31.model.effect.GetResourceEffect;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import javafx.scene.control.TextFormatter;

import java.util.Map;

/**
 * Created by Francesco on 26/05/2017.
 */
public class TowerCardCostPlacementControl extends Control {
    private TowerCardSpace towerCardSpace = null;
    private Map<CardColor, ResourceList> cardResourceDiscount;

    /* Constructor */
    public TowerCardCostPlacementControl(Player player) {
        super(player);
    }


    /* Setters & Getters */
    public void setTowerCardSpace(TowerCardSpace towerCardSpace) {
        this.towerCardSpace = towerCardSpace;
    }

    @Override
    public String getControlStringError() {
        return "Non puoi pagare la torre e/o la carta";
    }

    /* Resetters */
    public void resetTowerCardSpace() {
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
                }
            } else {
                costAffordable = false;
            }
        }
        //simulo l attivazione dell effetto che mi fa guadagnare risorse dell action space (se ha un effetto che mi fa guadagnare risorse)
        if (towerCardSpace.getActionSpace().getImmediateEffectList() != null) {
            if (towerCardSpace.getActionSpace().getImmediateEffectList().getEffectList().get(0).getResourceToGainString() != null) {
                GetResourceEffect tempGetRes = (GetResourceEffect) towerCardSpace.getActionSpace().getImmediateEffectList().get(0);
                for (Resource resource : tempGetRes.getResources().getListOfResource()
                        ) {
                    tempPlayerResources.addSpecificResource(resource);
                }
            }
        }
         //simulo il dover pagare la carta
        if (!player.getActionControlSet().payCardControl(towerCardSpace.getCard(), null, tempPlayerResources)) {
            costAffordable = false;
        }



        resetTowerCardSpace();
        return costAffordable;
    }
}