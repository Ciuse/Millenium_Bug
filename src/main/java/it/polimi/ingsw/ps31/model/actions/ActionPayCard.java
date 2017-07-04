package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceListToPay;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.MilitaryStrength;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Giuseppe on 28/06/2017.
 */
public class ActionPayCard extends Action {
   private DevelopmentCard cardToPay;
   private ResourceList resourceListDiscount;
   private Map<CardColor, ResourceList> cardResourceDiscount;

    public ActionPayCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet); this.cardResourceDiscount = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardResourceDiscount.put(cardColor, null);

    }

    public void setCardToPay(DevelopmentCard cardToPay) {
        this.cardToPay = cardToPay;
    }

    public Map<CardColor, ResourceList> getCardResourceDiscount() {
        return cardResourceDiscount;
    }

    public void setResourceListDiscount(ResourceList resourceListDiscount) {
        this.resourceListDiscount = resourceListDiscount;
    }

    public void resetCardToPay()
    {
        this.cardToPay = null;
    }
    public void resetListDiscount()
    {
        this.resourceListDiscount = null;
    }

    @Override
    public void activate() {

        int listToPay=-1;
        ResourceList listDiscounted=null;
        boolean canPayMilitaryStrength=true;
        if (!cardToPay.getCostList().isEmpty()) {
            if(cardToPay.getCostList().size()==1) {
                listToPay = 0;
                listDiscounted = cardToPay.getCostList().get(listToPay);
                listDiscounted.discountResourceList(this.cardResourceDiscount.get(cardToPay.getCardColor()));
                if (resourceListDiscount != null) {
                    listDiscounted.discountResourceList(resourceListDiscount);
                }
            }
            //se la carta ha almeno una lista da pagare vedo quante ne ha
            if (cardToPay.getCostList().size() > 1) {     //se la carta ha più di una lista da pagare chiedo alla view quale vuole pagare
                do {
                    String string = player.getPlayerId() + "Quale costo della carta vuoi pagare?";
                    player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(cardToPay.getCostList());
                    player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceListToPay(cardToPay.getCardId())));
                    listToPay = player.getModel().getModelChoices().waitIntListToPay();

                    if(listToPay==-1) {  //TIMER SCADUTO -> paga una lista a caso...
                        player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                        listToPay=0;
                    }
                    //controllo i requisiti dei military strength
                    if(cardToPay.getCostList().get(listToPay).getSpecificResource(MilitaryStrength.class)!=null){
                        MilitaryStrength militaryStrength = (MilitaryStrength)cardToPay.getCostList().get(listToPay).getSpecificResource(MilitaryStrength.class);
                        if(player.getPlayerResources().getSpecificResource(MilitaryStrength.class).lessOrEquals(militaryStrength.getValueRequest())){
                            canPayMilitaryStrength=false;
                        }
                    }

                    listDiscounted=cardToPay.getCostList().get(listToPay);
                    listDiscounted.discountResourceList(this.cardResourceDiscount.get(cardToPay.getCardColor()));
                    if(resourceListDiscount!=null){
                        listDiscounted.discountResourceList(resourceListDiscount);
                    }
                }while(!listDiscounted.lessOrEquals(player.getPlayerResources())||!canPayMilitaryStrength);   // se fallisce il pagamento glielo richiedo magari poteva pagare solo 1 dei due costi
            }
        }
        if (listToPay != -1) {
            //pago la carta in base alla lista che mi ha detto il giocatore se c era piu di una lsita
            player.getPlayerActionSet().payResources(listDiscounted);
        }

        resetCardToPay();
        resetListDiscount();
    }

    public void addCardResourceDiscount(CardColor cardColor, boolean anyColor, ResourceList discountList)
    {
        if(anyColor){
            ResourceList currentDiscount1 = this.cardResourceDiscount.get(CardColor.GREEN);
            currentDiscount1.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.GREEN, currentDiscount1);
            ResourceList currentDiscount2 = this.cardResourceDiscount.get(CardColor.BLUE);
            currentDiscount2.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.BLUE, currentDiscount2);
            ResourceList currentDiscount3 = this.cardResourceDiscount.get(CardColor.YELLOW);
            currentDiscount3.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.YELLOW, currentDiscount3);
            ResourceList currentDiscount4 = this.cardResourceDiscount.get(CardColor.PURPLE);
            currentDiscount4.discountResourceList(discountList);
            cardResourceDiscount.put(CardColor.PURPLE, currentDiscount4);
        }
        else {
            if(cardColor!=null) {
                ResourceList currentDiscount = this.cardResourceDiscount.get(cardColor);
                currentDiscount.discountResourceList(discountList);
                cardResourceDiscount.put(cardColor, currentDiscount);
            }
        }
    }
}
