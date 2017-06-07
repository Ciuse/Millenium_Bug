package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Coin;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 22/05/2017.
 */
public class ActionPayTowerMoney extends Action {
    private static final int COINTOPAY = 3; //Valore di default delle monete da pagare

    /* Constructor */
    public ActionPayTowerMoney(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setters & Getters */

    public int getCoinToPay()
    {
        return this.COINTOPAY;
    }

    /* Resetters */
    public void resetCoinToPay (){
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        Coin payment = new Coin(this.COINTOPAY);
        super.player.getPlayerActionSet().payResources(payment);
    }
}
