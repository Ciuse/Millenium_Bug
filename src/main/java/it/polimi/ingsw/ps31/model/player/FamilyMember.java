package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

/**
 * Created by Francesco on 15/05/2017.
 */
public class FamilyMember {

    private final Player player;
    private final DiceColor diceColor;
    private int diceValue;
    private int additionalValue;
    private int permanentAdditionalValue=0;
    private ActionSpace actionSpace;
    private Integer fixedValue;

    /* Constructor */
    public FamilyMember(Player player, DiceColor diceColor)
    {
        this.player = player;
        this.diceColor = diceColor;
        this.diceValue=0;
        this.additionalValue = 0;
        this.actionSpace = null;
        this.fixedValue = null;
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

    public void addPermanentAdditionalValue(int bonus){
        this.permanentAdditionalValue = this.permanentAdditionalValue+bonus;
    }
    public void resetFamilyMember() {
        this.diceValue = 0;
        this.additionalValue = 0;
        if (actionSpace != null) {
            this.actionSpace.removeFamilyMember(this);
            this.actionSpace = null;
        }
    }

    public int getTotalValue()
    {
        if( this.fixedValue == null )
            return this.diceValue + this.additionalValue+this.permanentAdditionalValue;
        else
            return this.fixedValue;
    }

    public int getPermanentAdditionalValue() {
        return permanentAdditionalValue;
    }

    public void setPermanentAdditionalValue(int permanentAdditionalValue) {
        this.permanentAdditionalValue = permanentAdditionalValue;
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
            StateFamilyMember stateFamilyMember = new StateFamilyMember(player.getPlayerId(), player.getPlayerColor(), diceValue, additionalValue, diceColor, -1);
            return stateFamilyMember;
        }
        else return new StateFamilyMember(player.getPlayerId(), player.getPlayerColor(), diceValue, additionalValue, diceColor, this.actionSpace.getActionSpaceId());
     }

     public void setFixedValue(int fixedValue){
         this.fixedValue = fixedValue;
     }
}
