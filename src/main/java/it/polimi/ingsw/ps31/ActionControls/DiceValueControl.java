package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class DiceValueControl extends Control {
    private Integer diceValue = null;
    private DiceColor diceColor = null;

    /* Constructor */
    public DiceValueControl(Player player) {
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

    public void resetDiceColor()
    {
        this.diceColor = null;
    }

    public void resetDiceValue()
    {
        this.diceValue = null;
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
