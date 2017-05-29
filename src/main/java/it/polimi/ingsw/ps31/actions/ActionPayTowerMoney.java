package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.gameThings.Coin;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 22/05/2017.
 */
public class ActionPayTowerMoney extends Action {
    private static final int COINTOPAY = 3; //Valore di default delle monete da pagare
    private Integer coinToPay;

    /* Constructor */
    public ActionPayTowerMoney(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
        this.coinToPay = COINTOPAY;
    }

    /* Setters & Getters */
    public void setCoinToPay(Integer coinToPay)
    {
        this.coinToPay = coinToPay;

    }

    public int getCoinToPay()
    {
        return this.coinToPay;
    }

    /* Resetters */
    public void resetCoinToPay ()
    {
        this.coinToPay = COINTOPAY;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if( this.coinToPay == null)
        {
            //TODO: gestire;
        }else
        {
            Coin payment = new Coin(this.coinToPay);
            player.getPlayerActionSet().payResources(payment);
        }
    }
}
