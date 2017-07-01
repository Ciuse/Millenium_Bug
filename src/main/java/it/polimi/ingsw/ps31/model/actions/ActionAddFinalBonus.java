package it.polimi.ingsw.ps31.model.actions;

/**
 * Created by Giuseppe on 07/06/2017.
 */

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 31/05/2017.
 */
public class ActionAddFinalBonus extends Action {
    private ResourceList resourceList = null; //effetto permanente carte viola

    /* Constructor */
    public ActionAddFinalBonus(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setters  & Getters */
    public void setResourceList(ResourceList resourceList)
    {
        this.resourceList = resourceList;
    }

    public ResourceList getResourceList()
    {
        return this.resourceList;
    }

    /* Resetters */
    public void resetResourceList()
    {
        this.resourceList = null;
    }

    /* Activation Method */
    @Override
    public void activate()
    {
        player.addFinalBonusResource(this.resourceList);

        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato PlayerResources (effetto permanente carte viola)",player.getStatePlayerResources()));

        resetResourceList();
    }
}