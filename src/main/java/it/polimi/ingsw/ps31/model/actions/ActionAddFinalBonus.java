package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 31/05/2017.
 */
public class ActionAddFinalBonus extends Action {
    private ResourceList resourceList = null;

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
        resetResourceList();
    }
}