package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.gameResource.Coin;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 22/05/2017.
 */
public class ActionPayTowerMoney extends Action {
    private static final Coin COINTOPAY= new Coin(3); //Valore di default delle monete da pagare
    private boolean toPay=true;

    /* Constructor */
    public ActionPayTowerMoney(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setters & Getters */

    public boolean isToPay() {
        return toPay;
    }

    public void setToPay(boolean toPay) {
        this.toPay = toPay;
    }

    public Coin getCOINTOPAY() {
        return COINTOPAY;
    }

    /* Class Methods */
    @Override
    public void activate() {
        if (isToPay()) {
            super.player.getPlayerActionSet().payResources(COINTOPAY);
            player.getModel().notifyViews(new MVUpdateState("Aggiornato stato player resource", player.getStatePlayerResources()));
        }
    }
}
