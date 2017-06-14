package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.constants.DiceColor;

/**
 * Created by Francesco on 15/05/2017.
 */
public class FamilyMember {

    private final Player player;
    private final DiceColor diceColor;
    private int diceValue;
    private int additionalValue;
    private ActionSpace actionSpace;

    /* Constructor */
    public FamilyMember(Player player, DiceColor diceColor)
    {
        this.player = player;
        this.diceColor = diceColor;
        this.diceValue=0;
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
        return this.diceValue;
    }

    public int getAdditionalValue()
    {
        return this.additionalValue;
    }

    public void addAdditionalValue(int additionalValue)
    {
        this.additionalValue = this.additionalValue+additionalValue;
    }

    public void resetFamilyMember() {
        this.diceValue = 0;
        this.additionalValue = 0;
        this.actionSpace = null;
    }

    public int getTotalValue()
    {
        return this.diceValue + this.additionalValue;
    }

    public ActionSpace getActionSpace()
    {
        return this.actionSpace;
    }

    public void setActionSpace(ActionSpace actionSpace)
    {
        this.actionSpace = actionSpace;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public boolean isPlaced()
    {
        if ( this.actionSpace == null )
            return false;
        else
            return true;
    }
     public StateFamilyMember getStateFamilyMember(){
        if(this.actionSpace==null){
            StateFamilyMember stateFamilyMember = new StateFamilyMember(player.getPlayerId(),diceValue, additionalValue, diceColor, -1);
            return stateFamilyMember;
        }
        else return new StateFamilyMember(player.getPlayerId(),diceValue, additionalValue, diceColor, this.actionSpace.getActionSpaceId());
     }
}
