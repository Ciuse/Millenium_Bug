package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.constants.DiceColor;

/**
 * Created by Francesco on 15/05/2017.
 *
 * Classe che rapprenseta un dado con un valore e un colore
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
