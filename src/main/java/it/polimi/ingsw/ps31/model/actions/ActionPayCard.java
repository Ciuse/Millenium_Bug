package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 28/06/2017.
 *
 * Azione che permette al player di pagare una carta dopo che il controllo del pagamento
 * ha settato all'azione il valore giusto da pagare ( con bonus e sconti già sottratti)
 */
public class ActionPayCard extends Action {
    private ResourceList listToPay =null;

    public ActionPayCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);

    }

    public void setListToPay(ResourceList listTopay) {
        this.listToPay = listTopay;
    }

    public void resetListToPay(){
        this.listToPay=null;
    }

    @Override
    public void activate() {
        player.getPlayerActionSet().payResources(listToPay);
        resetListToPay();
    }

}
