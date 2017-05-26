package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Board.ActionSpace;
import it.polimi.ingsw.ps31.Board.Dice;
/**
 * Created by Francesco on 15/05/2017.
 */
public class FamilyMember {

    private final Player player;
    private final Dice dice;
    private int additionalValue;
    private ActionSpace actionSpace;

    /* Constructor */
    public FamilyMember(Player player, Dice dice)
    {
        this.player = player;
        this.dice = dice;
        this.additionalValue = 0;
        this.actionSpace = null;
    }

    /* Setters & Getters */
    public Player getPlayer()
    {
        return this.player;
    }

    public Dice getDice()
    {
        return this.dice;
    }

    public int getAdditionalValue()
    {
        return this.additionalValue;
    }

    public void addAdditionalValue(int additionalValue)
    {
        this.additionalValue = this.additionalValue+additionalValue;
    }

    public void resetAdditionalValue()
    {
        this.additionalValue = 0;
    }

    public int getTotalValue()
    {
        return this.dice.getValue() + this.additionalValue;
    }

    public ActionSpace getActionSpace()
    {
        return this.actionSpace;
    }

    public void setActionSpace(ActionSpace actionSpace)
    {
        this.actionSpace = actionSpace;
    }

    public void resetActionSpace()
    {
        this.actionSpace=null;
    }

    public boolean isPlaced()
    {
        if ( this.actionSpace == null )
            return false;
        else
            return true;
    }
}
