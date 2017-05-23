package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionChooseCard extends Action {
    private Integer diceCost = null;
    private Integer diceDiscount = null;
    private CardColor cardColor = null;

    /* Constructor */
    public ActionChooseCard(Player player)
    {
        super(player);
    }

    /* Getters & Setters*/
    public void setDiceCost(int diceCost)
    {
        Integer castedDiceCost = new Integer(diceCost);
        this.setDiceCost(castedDiceCost);
    }

    public void setDiceCost(Integer diceCost)
    {
        this.diceCost = diceCost;
    }

    public void setCardColor(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    public void setDiceDiscount(Integer diceDiscount)
    {
        this.diceDiscount = diceDiscount;
    }

    public void resetCardColor()
    {
        this.cardColor = null;
    }

    public void resetDiceCost()
    {
        this.diceCost=null;
    }

    public void resetDiceDiscount()
    {
        this.diceDiscount = null;
    }

    public CardColor getCardColor()
    {
        return this.cardColor;
    }

    public Integer getDiceCost()
    {
        return this.diceCost;
    }

    public Integer getDiceDiscount()
    {
        return this.diceDiscount;
    }

    /* Activate method*/
    @Override
    public void activate()
    {
        //Controllare che campi obbligatori siano settati
        //Trovare carte selezionabili in base ai paametri
        //Fare richiesta alla view per scegliere la carta
        //Aggiungere la carta al player
    }
}
