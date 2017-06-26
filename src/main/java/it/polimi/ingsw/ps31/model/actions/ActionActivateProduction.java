package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionActivateProduction extends Action {
    private Integer diceValue = null;
    private Integer diceBonus = 0;

    /* Constructor */
    public ActionActivateProduction(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setters & Getters */
    public Integer getDiceValue()
    {
        return diceValue;
    }

    public void setDiceValue(Integer diceValue)
    {
        this.diceValue = diceValue;
    }

    public void resetDiceValue()
    {
        this.diceValue = null;
    }

    /* Activation Method */
    @Override
    public void activate() {

        player.getProductionList().activate(this.diceValue + this.diceBonus);

        resetDiceValue();
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato PlayerResources", player.getStatePlayerResources()));

    }

    /* Modifiers */
    public void addDiceBonus(int diceBonus)
    {
        this.diceBonus += diceBonus;
    }
}
