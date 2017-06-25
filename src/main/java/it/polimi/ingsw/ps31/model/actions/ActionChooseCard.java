package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceTowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 23/05/2017.
 */
public class ActionChooseCard extends Action {
    private Integer diceCost = null;
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

    public void setResourceDiscount(ResourceList resourceDiscount)
    {
        if(resourceDiscount!=null){
        this.resourceDiscount = resourceDiscount;
        }

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
    public void activate() {
        TowerCardSpace chosenCardSpace;
        do {
            String string = player.getPlayerId() + ": Quale carta della torre vuoi?";
            super.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceTowerCardSpace()));
            chosenCardSpace = super.waitTowerCardChosen();
        } while (!checkChosenTowerCardSpace(chosenCardSpace));

        super.player.getPlayerActionSet().takeCard(chosenCardSpace);    //TODO: e se il player non può attivare l'effetto della carta?

        resetAnyCardColor();
        resetCardColor();
        resetDiceCost();
        resetDiceDiscount();
        resetResourceDiscount();

    }

    /* Class Methods */
    private boolean checkChosenTowerCardSpace(TowerCardSpace chosenTCS) {
        //Controllo esistenza carta nel tcs
        if (chosenTCS.getCard() == null) {
            super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto uno spazio vuoto,senza carta"));
            return false;
        }

        //Controllo colore
        if (!this.anyCardColor)
            if (this.cardColor != null && !chosenTCS.getTowerColor().equals(this.cardColor)) {
                super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta del colore sbagliato"));
                return false;
            }

        //Controllo costo dado
        if (!super.actionControlSet.diceValueVsCardSpaceControl(this.diceCost, chosenTCS)) {
            super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta dal costo in dado troppo alto"));
            return false;
        }

        //Controllo costo risorse
        if (resourceDiscount != null) {
            List<ResourceList> tempResourceListList = new ArrayList<>(chosenTCS.getCard().getCostList());
            for (ResourceList list: tempResourceListList
                 ) {
                for (Resource resource : resourceDiscount.getResourceList()
                        ) {
                    list.discountSpecificResource(resource);
                }
            }
            if (!super.actionControlSet.payResourceListControl(tempResourceListList)) {
                super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta che non puoi pagare"));
                return false;
            }
        }
        return true;
    }
}
