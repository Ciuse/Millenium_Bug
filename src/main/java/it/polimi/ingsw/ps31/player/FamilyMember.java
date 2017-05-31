package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.board.ActionSpace;
import it.polimi.ingsw.ps31.board.Dice;
import it.polimi.ingsw.ps31.board.GameBoard;
import it.polimi.ingsw.ps31.constants.DiceColor;

/**
 * Created by Francesco on 15/05/2017.
 */
public class FamilyMember {

    private final Player player;
    private final DiceColor diceColor;
    private int additionalValue;
    private ActionSpace actionSpace;
    private static GameBoard gameBoard = GameBoard.getInstance();

    /* Constructor */
    public FamilyMember(Player player, DiceColor diceColor)
    {
        this.player = player;
        this.diceColor = diceColor;
        this.additionalValue = 0;
        this.actionSpace = null;
    }

    /* Setters & Getters */
    public Player getPlayer()
    {
        return this.player;
    }

    public DiceColor getDiceColor()
    {
        return this.diceColor;
    }

    public int getDiceValue(){
        return gameBoard.getSpecificDice(this.diceColor).getValue();
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
        return gameBoard.getSpecificDice(this.diceColor).getValue() + this.additionalValue;
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
