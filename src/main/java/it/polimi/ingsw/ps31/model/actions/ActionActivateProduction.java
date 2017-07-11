package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 *
 * Azione che attiva le carte produzione del player. Necessita di un intero che rappresenta il valore dell'attivazione
 *
 * @see it.polimi.ingsw.ps31.model.player.ProductionList

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

    /**
     * l'attivazione consiste nell'attivare la lista di produzioni del player con un valore
     * che è dato dai vari bonus che il player ha più il valore base con cui è stata invocata
     * l'azione
     */
    @Override
    public void activate() {

        player.getProductionList().activate(this.diceValue + this.diceBonus);

        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato PlayerResources", player.getStatePlayerResources()));

        resetDiceValue();

    }

    /* Modifiers */
    public void addDiceBonus(int diceBonus)
    {
        this.diceBonus += diceBonus;
    }
}
