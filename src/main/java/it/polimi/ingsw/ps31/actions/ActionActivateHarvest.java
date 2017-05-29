package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionActivateHarvest extends Action {
    private Integer diceValue = null;

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

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i paramatri siano settati
        if ( this.diceValue == null )
        {
            //TODO: gestire
        }
        else
            player.getHarvestList().activate(this.diceValue);

        resetDiceValue();
    }
}
