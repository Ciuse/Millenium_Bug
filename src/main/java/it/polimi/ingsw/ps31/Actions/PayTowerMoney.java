package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.Coin;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 22/05/2017.
 */
public class PayTowerMoney extends Actions {

    private static final int COINTOPAY = 3; //Valore di default delle monete da pagare
    private int coinToPay;

    public PayTowerMoney(Player player) {
        super(player);
        this.coinToPay = COINTOPAY;
    }

    public void setCoinToPay(int coinToPay)
    {
        this.coinToPay = coinToPay;

    }

    public void resetCoinToPay ()
    {
        this.coinToPay = COINTOPAY;
    }

    public int getCoinToPay()
    {
        return this.coinToPay;
    }
    @Override
    public void activate()
    {
        Coin payment = new Coin(0-this.coinToPay);
        player.addResources(payment);
    }
}
