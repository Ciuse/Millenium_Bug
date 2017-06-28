package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 24/05/2017.
 */
public class DiceValueActionSpaceControl extends Control {
    private Integer diceValue = null;
    private DiceColor diceColor = null;

    /* Constructor */
    public DiceValueActionSpaceControl(Player player) {
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

    @Override
    public String getControlStringError() {
        return  "Il valore del famigliare è inferiore a quello dell action space";
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
    public boolean execute() {
        if (this.diceValue == null || this.diceColor == null) {
            //TODO: gestire
            return false;
        }

        boolean ret = player.getSpecificFamilyMember(diceColor).getTotalValue() >= this.diceValue;
        resetDiceColor();
        resetDiceValue();

        return ret;

    }

}
