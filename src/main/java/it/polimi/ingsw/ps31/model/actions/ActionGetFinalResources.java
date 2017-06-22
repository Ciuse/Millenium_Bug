package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 30/05/2017.
 */
public class ActionGetFinalResources extends ActionGetResources {   //Attivato solo alla fine della partita

    public ActionGetFinalResources(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public void setResourcesToGet(List<ResourceList> resourcesToGet)
    {
        for(ResourceList currentResourceList : resourcesToGet)
        super.setResourcesToGet(currentResourceList);
    }

    @Override
    public void activate()
    {
        List<ResourceList> resList = player.getFinalBonusResources();
        for(ResourceList currentList : resList)
            player.getPlayerActionSet().getResources(currentList);
    }
}
