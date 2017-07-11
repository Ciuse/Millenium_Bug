package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 30/05/2017
 *
 * Azione per poter aggiungere la lista delle risorse finali del player alle risorse
 * principali del player.
 * Viene usata solo alla fine del gioco (in automatico)
 *
 * @see Player
 */
public class ActionGetFinalResources extends ActionGetResources {

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
