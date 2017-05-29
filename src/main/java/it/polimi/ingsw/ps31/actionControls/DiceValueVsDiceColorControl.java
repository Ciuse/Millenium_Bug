package it.polimi.ingsw.ps31.actionControls;

import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class DiceValueVsDiceColorControl extends Control {
    private Integer diceValue = null;
    private DiceColor diceColor = null;

    /* Constructor */
    public DiceValueVsDiceColorControl(Player player) {
        super(player);
    }

    /* Setters & Getters */
    public void setDiceValue(Integer diceValue)
    {
        this.diceValue = diceValue;
    }
    public void setDiceColor(DiceColor diceColor)
    {
        this.diceColor = diceColor;
    }

    public Integer getDiceValue()
    {
        return this.diceValue;
    }
    public DiceColor getDiceColor()
    {
        return this.diceColor;
    }

    /* Resetters */
    public void resetDiceValue()
    {
        this.diceValue = null;
    }
    public void resetDiceColor()
    {
        this.diceColor = null;
    }

    /* Class Methods */
    @Override
    public boolean execute()
    {
        if ( this.diceValue == null || this.diceColor == null )
        {
            //TODO: gestire
            return false;
        }

            boolean ret = (player.getFamilyMember(diceColor).getDice().getValue() >= this.diceValue);
            resetDiceColor();
            resetDiceValue();

            return ret;

    }


}
