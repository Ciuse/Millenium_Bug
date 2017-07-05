package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 *
 * Azione che attiva le carte raccolto del player. Necessita di un intero che rappresenta il costo
 */
public class ActionActivateHarvest extends Action {
    private Integer diceValue = null;
    private Integer diceBonus = 0;
    private boolean activated = false;
    private boolean activatedFromNeutral = false;

    /* Constructor */
    public ActionActivateHarvest(Player player, ActionControlSet actionControlSet) {
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
        System.out.println("PLAYER RES BEFORE " +(player.getPlayerResources()));

        System.out.println(">HARV ACT: ATTIVAZIONE HARVEST: " +(this.diceValue + this.diceBonus));

        player.getHarvestList().activate(this.diceValue + this.diceBonus);

        System.out.println("PLAYER RES AFTER " +(player.getPlayerResources()));

        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato PlayerResources", player.getStatePlayerResources()));

        resetDiceValue();
    }

    /* Modifiers */
    public void addDiceBonus(int diceBonus)
    {
        this.diceBonus += diceBonus;
    }
}
