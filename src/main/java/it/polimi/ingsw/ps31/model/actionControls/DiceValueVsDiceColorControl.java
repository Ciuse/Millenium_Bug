package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 24/05/2017.
 */
public class DiceValueVsDiceColorControl extends Control {
    private Integer diceValue = null;
    private DiceColor diceColor = null;
    protected Map<DiceColor, Integer> bonusValues;

    /* Constructor */
    public DiceValueVsDiceColorControl(Player player) {
        super(player);
        this.bonusValues = new HashMap<>();

        for(DiceColor currentColor : DiceColor.values())
            this.bonusValues.put(currentColor, new Integer(0));
    }

    @Override
    public String getControlStringError() {
        return  "booooh";
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

            boolean ret =   player.getSpecificFamilyMember(diceColor).getDiceValue()+
                            this.bonusValues.get(diceColor) >= this.diceValue;
            resetDiceColor();
            resetDiceValue();

            return ret;

    }

    /* Modifiers */
    public void addPermanentValueAtSpecificMember(DiceColor memberColor, Integer valueToAdd)
    {
        Integer currentValue = bonusValues.get(memberColor);
        bonusValues.put(memberColor, currentValue+valueToAdd);
    }


}
