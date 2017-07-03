package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
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
    private boolean actionTimerEnded = false;

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
            player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceTowerCardSpace()));
            chosenCardSpace = player.getModel().getModelChoices().waitTowerCardChosen();

            if (chosenCardSpace == null) {      //TIMER SCADUTO
                actionTimerEnded=true;
                player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                break;
            }

        } while (!checkChosenTowerCardSpace(chosenCardSpace));

        if (!actionTimerEnded) {      //TIMER NON SCADUTO

            //pago la carta
            super.player.getPlayerActionSet().payCard(chosenCardSpace.getCard(), resourceDiscount);

            //prendo la carta
            super.player.addDevelopmentCard(chosenCardSpace.takeCard());

            player.getModel().notifyViews(new MVUpdateState("Aggiornato stato Player Personal Board", player.getPersonalBoard().getStatePersonalBoard()));
            player.getModel().notifyViews(new MVUpdateState("Aggiornato stato tower card space", chosenCardSpace.getStateTowerCardBox()));

        } else {
            player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
        }

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
            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto uno spazio vuoto,senza carta"));
            return false;
        }

        //Controllo colore
        if (!this.anyCardColor)
            if (this.cardColor != null && !chosenTCS.getTowerColor().equals(this.cardColor)) {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta del colore sbagliato"));
                return false;
            }

        //controllo requisiti personal board
        if (!this.actionControlSet.takeDevelopmentCardControl(chosenTCS.getCard())) {
            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: non puoi mettere la carta nella tua personal board"));
            return false;
        }

        //Controllo costo dado
        if (!super.actionControlSet.diceValueVsCardSpaceControl(this.diceCost, chosenTCS)) {
            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta dal costo in dado troppo alto"));
            return false;
        }

        //Controllo costo risorse

        //sommo sconto immediato
        if (resourceDiscount != null) {
            List<ResourceList> tempResourceListList = new ArrayList<>(chosenTCS.getCard().getCostList());
            for (ResourceList list: tempResourceListList
                 ) {
                for (Resource resource : resourceDiscount.getListOfResource()
                        ) {
                    list.discountSpecificResource(resource);
                }
            }
            //sommo sconto permanente
            if(player.getPlayerActionSet().getPayCard().getCardResourceDiscount().get(chosenTCS.getCard().getCardColor())!=null){
                for (ResourceList list: tempResourceListList
                        ) {
                    list.discountResourceList(player.getPlayerActionSet().getPayCard().getCardResourceDiscount().get(chosenTCS.getCard().getCardColor()));
                    }
            }

            if (!super.actionControlSet.payResourceListControl(tempResourceListList)) {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta che non puoi pagare"));
                return false;
            }
        }
        return true;
    }
}
