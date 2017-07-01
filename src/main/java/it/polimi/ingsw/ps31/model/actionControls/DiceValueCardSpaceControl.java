package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 28/05/2017.
 */
public class DiceValueCardSpaceControl extends Control {
    private Integer diceValue = null;
    private TowerCardSpace towerCardSpace = null;
    private Map<CardColor, Integer> cardDiceBonuses;


    /* Constructor */
    public DiceValueCardSpaceControl(Player player) {
        super(player);
        this.cardDiceBonuses = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardDiceBonuses.put(cardColor, 0);
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

    @Override
    public String getControlStringError() {
        return "Il costo del dado della carta è maggiore del valore dell azione con cui la stai prendendo ";
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
            //Controllo che il valore dell azione più i vari bonus sia maggiore al costro del dado dell action space associato al tower card space
            if ( diceValue+cardDiceBonuses.get(towerCardSpace.getTowerColor())>=towerCardSpace.getActionSpace().getDiceCost())
                result = true;
            else
                result = false;
        }

        resetDiceValue();
        resetTowerCardSpace();
        return result;

    }

    /* Modifiers */
    public void addCardDiceBonus (CardColor cardColor, Integer bonus)
    {
        //bonus può anche essere negativo (es. scomuniche)
        Integer currentBonus = this.cardDiceBonuses.get(cardColor);
        cardDiceBonuses.put(cardColor, currentBonus+bonus);

    }
}
