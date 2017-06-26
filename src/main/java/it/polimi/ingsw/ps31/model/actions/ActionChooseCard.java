package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceListToPay;
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
            player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceTowerCardSpace()));
            chosenCardSpace = player.getModel().getModelChoices().waitTowerCardChosen();

        } while (!checkChosenTowerCardSpace(chosenCardSpace));

        int listToPay = -1;
        if (chosenCardSpace.getCard().getCostList() != null) {
            listToPay=0;
            //se la carta ha almeno una lista da pagare vedo quante ne ha
            if (chosenCardSpace.getCard().getCostList().size() > 1) {     //se la carta ha più di una lista da pagare chiedo alla view quale vuole pagare
                do {
                    String string = player.getPlayerId() + "Quale costo della carta vuoi pagare?";
                    player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(chosenCardSpace.getCard().getCostList());
                    player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceListToPay(chosenCardSpace.getCard().getCardId())));
                    listToPay = player.getModel().getModelChoices().waitIntChosen();
                }while(!player.getPlayerResources().greaterThan(chosenCardSpace.getCard().getCostList().get(listToPay)));   // se fallisce il pagamento glielo richiedo magari poteva pagare solo i dei due costi
            }
        }
        if (listToPay != -1) {
            //pago la carta in base alla lista che mi ha detto il giocatore se c era piu di una lsita
            player.getPlayerActionSet().payResources(chosenCardSpace.getCard().getCostList().get(listToPay));
        }


        super.player.addDevelopmentCard(chosenCardSpace.takeCard());

        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato Player Personal Board", player.getPersonalBoard().getStatePersonalBoard()));
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato tower card space", chosenCardSpace.getStateTowerCardBox()));

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
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "ERRORE: hai scelto una carta che non puoi pagare"));
                return false;
            }
        }
        return true;
    }
}
