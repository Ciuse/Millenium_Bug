package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 28/05/2017.
 */
public class DiceValueVsCardSpaceControl extends Control {
    private Integer diceValue = null;
    private TowerCardSpace towerCardSpace = null;

    /* Constructor */
    public DiceValueVsCardSpaceControl(Player player) {
        super(player);
    }

    /* Setters & Getters */
    public void setDiceValue(Integer diceValue)
    {
        this.diceValue = diceValue;
    }
    public void setTowerCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    public Integer getDiceValue()
    {
        return diceValue;
    }
    public TowerCardSpace getTowerCardSpace()
    {
        return towerCardSpace;
    }

    /* Resetters */
    public void resetDiceValue()
    {
        this.diceValue = null;
    }
    public void resetTowerCardSpace()
    {
        this.towerCardSpace = null;
    }

    /* Class Methods */
    @Override
    public boolean execute()
    {
        boolean result;

        //Controllo che i parametri siano settati
        if ( this.diceValue == null || this.towerCardSpace == null )
        {
            //TODO: gestire
            result = false; //Altrimenti non compila
        } else
        {
            //Controllo che il costo del dado del towerCardSpace sia minore del valore del dado indicato
            if ( towerCardSpace.getActionSpace().getDiceCost() > diceValue.intValue() )
                result = false;
            else
                result = true;
        }

        resetDiceValue();
        resetTowerCardSpace();
        return result;

    }
}
