package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionPayResources extends Action {
    private ResourceList resourceToPay = null;

    /* Constructor */
    public ActionPayResources(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setter & Getter */
    public void setResourceToPay(ResourceList resourceToPay) {
        this.resourceToPay = resourceToPay;
    }

    public void resetResourceToPay() {
        this.resourceToPay = null;
    }

    @Override
    public void activate() {

//            //Eseguo il controllo
        if (super.actionControlSet.payResourceControl(this.resourceToPay)) {
//           //Eseguo l'azione
            for (Resource currentResource : resourceToPay.getListOfResource())
                player.subResources(currentResource);

        }

        resetResourceToPay();
//            } else
//            {
//                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPayResourceControl().getControlStringError()));
//            }
//
//            resetResourceToPay();
//            player.getModel().notifyViews(new MVUpdateState("Aggiornato stato player resource",player.getStatePlayerResources()));
//        }
    }

}
