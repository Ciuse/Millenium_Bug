package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionChooseCard extends Action {
    private Integer diceCost = null;
    private int diceDiscount= 0;
    private ResourceList resourceDiscount = null;
    private CardColor cardColor = null;
    private boolean anyCardColor = false;

    /* Constructor */
    public ActionChooseCard(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Getters & Setters*/
    public void setDiceCost(int diceCost) {
        Integer castedDiceCost = new Integer(diceCost);
        this.setDiceCost(castedDiceCost);
    }
    public void setDiceCost(Integer diceCost)
    {
        this.diceCost = diceCost;
    }
    public void setDiceDiscount(int diceDiscount)
    {
        this.diceDiscount = diceDiscount;
    }
    public void setResourceDiscount(ResourceList resourceDiscount)
    {
        this.resourceDiscount = resourceDiscount;
    }
    public void setCardColor(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }
    public void setAnyCardColor (boolean anyCardColor)
    {
        this.anyCardColor = anyCardColor;
    }

    public Integer getDiceCost()
    {
        return this.diceCost;
    }
    public Integer getDiceDiscount()
    {
        return this.diceDiscount;
    }
    public ResourceList getResourceDiscount()
    {
        return this.resourceDiscount;
    }
    public CardColor getCardColor()
    {
        return this.cardColor;
    }
    public boolean getAnyCardColor()
    {
        return this.anyCardColor;
    }

    /* Resetters */
    public void resetDiceCost()
    {
        this.diceCost=null;
    }
    public void resetDiceDiscount()
    {
        this.diceDiscount = 0;
    }
    public void resetResourceDiscount ()
    {
        this.resourceDiscount = null;
    }
    public void resetCardColor()
    {
        this.cardColor = null;
    }
    public void resetAnyCardColor()
    {
        this.anyCardColor = false;
    }

    /* Activation method*/
    @Override
    public void activate()
    {
        //Controllo che campi obbligatori siano settati
        if( (this.cardColor == null && this.anyCardColor == false) || this.diceCost == null )
        {
            //TODO: eccezione
        }
        //Faccio richiesta alla view per scegliere la carta e controllo che la carta scelta rispetti i parametri settati
        TowerCardSpace chosenCardSpace;
        do
        {
            //TODO: fare richiesta alla view per scegliere il tower card space
            chosenCardSpace = new TowerCardSpace(null, null, null); //Cambiare questa riga
        }while (!checkChosenTowerCardSpace(chosenCardSpace));

        super.player.getPlayerActionSet().takeCard();    //TODO: e se il player non può attivare l'effetto della carta?

        resetAnyCardColor();
        resetCardColor();
        resetDiceCost();
        resetDiceDiscount();
        resetResourceDiscount();

    }

    /* Class Methods */
    private boolean checkChosenTowerCardSpace(TowerCardSpace chosenTCS)
    {
        //Controllo esistenza carta nel tcs
        if ( chosenTCS.getCard() == null )
            return false;

        //Controllo colore
        if( !this.anyCardColor )
            if ( this.cardColor != null && !chosenTCS.getColor().equals(this.cardColor) )
                return false;

        //Controllo costo dado
        if( !super.actionControlSet.diceValueVsCardSpaceControl(this.diceCost+this.diceDiscount, chosenTCS) )
            return false;

        //Controllo costo risorse
        if ( !super.actionControlSet.payResourceListControl(chosenTCS.getCard().getCostList()) )
                return false;

        return true;
    }
}
