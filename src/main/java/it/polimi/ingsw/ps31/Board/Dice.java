package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Constants.DiceColor;

/**
 * Created by Francesco on 15/05/2017.
 */
public class Dice {

    private int value;
    private final DiceColor color;

    /* Constructor */
    public Dice(DiceColor color)
    {
        this.color = color;
    }

    /* Setters & Getters */
    public int getValue()
    {
        return this.value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public DiceColor getColor()
    {
        return this.color;
    }
}
