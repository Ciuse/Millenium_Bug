package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.GameThings.Servant;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class PayServants extends Actions{

    private DiceColor diceColor = null;
    private int servantsAmount = 0;

    public PayServants(Player player) {
        super(player);
    }

    public void setDiceColor(DiceColor diceColor)
    {
        //TODO: Controllare che il familiare sia libero
        this.diceColor = diceColor;
    }

    public void setServantsAmount(int servantsAmount)
    {
        //Controllo che ci siano abbastanza servitori
        if(this.player.getResources().get("Servant").getValue() < servantsAmount)
        {
            //TODO: eccezione?
        }

        this.servantsAmount = servantsAmount;
    }

    @Override
    public void activate()
    {
        //TODO: controllare che i parametri siano validi
        player.addResources(new Servant(0-servantsAmount));
        player.getFamilyMember(this.diceColor).addAdditionalValue(servantsAmount);
    }
}
