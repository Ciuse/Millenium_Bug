package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
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
        if (cardToPay.getCostList() != null) {
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
                }while(!player.getPlayerResources().greaterThan(listDiscounted)||!canPayMilitaryStrength);   // se fallisce il pagamento glielo richiedo magari poteva pagare solo 1 dei due costi
            }
        }
        if (listToPay != -1) {
            //pago la carta in base alla lista che mi ha detto il giocatore se c era piu di una lsita
            player.getPlayerActionSet().payResources(listDiscounted);
        }

        resetCardToPay();
        resetListDiscount();
    }

    public void addCardResourceDiscount(CardColor cardColor, ResourceList discountList)
    {
        ResourceList currentDiscount = this.cardResourceDiscount.get(cardColor);
        currentDiscount.discountResourceList(discountList);
        cardResourceDiscount.put(cardColor, currentDiscount );

    }
}
