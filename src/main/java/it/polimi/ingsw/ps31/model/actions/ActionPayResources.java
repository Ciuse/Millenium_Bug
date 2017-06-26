package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionPayResources extends Action{
    private ResourceList resourceToPay = null;

    /* Constructor */
    public ActionPayResources(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Setter & Getter */
    public void setResourceToPay (ResourceList resourceToPay)
    {
        this.resourceToPay = resourceToPay;
    }

    public void resetResourceToPay()
    {
        this.resourceToPay = null;
    }

    @Override
    public void activate()
    {
        if ( this.resourceToPay == null )
        {
            //TODO: fare cose
        } else
        {
            //Eseguo il controllo
            if ( super.actionControlSet.payResourceControl(this.resourceToPay) )
            {
                //Eseguo l'azione
                List<Resource> resourcesToGetList = this.resourceToPay.getResourceList();
                for(Resource currentResource : resourcesToGetList)
                    player.subResources(currentResource);

                resetResourceToPay();
            } else
            {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPayResourceControl().getControlStringError()));
            }

            resetResourceToPay();
            player.getModel().notifyViews(new MVUpdateState("Aggiornato stato player resource",player.getStatePlayerResources()));
        }
    }
}
