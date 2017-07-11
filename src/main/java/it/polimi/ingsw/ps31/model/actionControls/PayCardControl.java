package it.polimi.ingsw.ps31.model.actionControls;

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
 * Created by Giuseppe on 06/07/2017.
 *
 * Controllo che verifica se una carta è possibile pagarla una volta simulato l ottenere
 * le risorse di un action space,e il pagare la torre.
 * Nel caso vi siano più possibili costi prima viene chiesto al giocatore quale lista pagare
 * e viene anche simulato l'aggiungere o sottrarre dei vari bonus inerenti al pagamento di una carta
 * derivante da bonus o effetti permanenti.
 *
 * Alla fine dei controlli se vengono superata, viene settato il costo della carta con tutti i bonus
 * e sconti applicati all' attributo dell azione "payCard"
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionPayCard
 */
public class PayCardControl extends Control {
    private DevelopmentCard cardToPay;
    private ResourceList resourceListDiscount;
    private ResourceList tempPlayerListAfterControl;
    private Map<CardColor, ResourceList> cardResourceDiscount;


    public PayCardControl(Player player) {
        super(player);
        this.cardResourceDiscount = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardResourceDiscount.put(cardColor, null);
    }

    public void setTempPlayerListAfterControl(ResourceList tempPlayerListAfterControl) {
        this.tempPlayerListAfterControl = tempPlayerListAfterControl;
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

    public void resetCardToPay() {
        this.cardToPay = null;
    }

    public void resetListDiscount() {
        this.resourceListDiscount = null;
    }

    public void resetTempList() {
        this.tempPlayerListAfterControl = null;
    }

    @Override
    public boolean execute() {

        if (cardToPay != null) {

            int listToPay = -1;
            ResourceList listDiscounted = null;
            boolean canPayMilitaryStrength = true;
            if (!cardToPay.getCostList().isEmpty()) {
                if (cardToPay.getCostList().size() == 1) {
                    listToPay = 0;
                    listDiscounted = cardToPay.getCostList().get(listToPay);
                    ResourceList discountList = this.cardResourceDiscount.get(cardToPay.getCardColor());
                    if (discountList != null) {
                        listDiscounted.discountResourceList(discountList);
                    }
                    if (resourceListDiscount != null) {
                        listDiscounted.discountResourceList(resourceListDiscount);
                    }
                }
                //se la carta ha almeno una lista da pagare vedo quante ne ha
                if (cardToPay.getCostList().size() > 1) {     //se la carta ha più di una lista da pagare chiedo alla view quale vuole pagare

                    String string = player.getPlayerId() + "Quale costo della carta vuoi pagare?";
                    player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(cardToPay.getCostList());
                    player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceListToPay(cardToPay.getCardId())));
                    listToPay = player.getModel().getModelChoices().waitIntListToPay();

                    if (listToPay == -1) {  //TIMER SCADUTO -> paga una lista a caso...
                        player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                        listToPay = 0;
                    }
                    //controllo i requisiti dei military strength
                    if (cardToPay.getCostList().get(listToPay).getSpecificResource(MilitaryStrength.class) != null) {
                        MilitaryStrength militaryStrength = (MilitaryStrength) cardToPay.getCostList().get(listToPay).getSpecificResource(MilitaryStrength.class);
                        if (player.getPlayerResources().getSpecificResource(MilitaryStrength.class).lessOrEquals(militaryStrength.getValueRequest())) {
                            canPayMilitaryStrength = false;
                        }
                    }
                    listDiscounted = cardToPay.getCostList().get(listToPay);
                    ResourceList discountList = this.cardResourceDiscount.get(cardToPay.getCardColor());
                    if (discountList != null) {
                        listDiscounted.discountResourceList(discountList);
                    }
                    if (resourceListDiscount != null) {
                        listDiscounted.discountResourceList(resourceListDiscount);
                    }
                }

                if (tempPlayerListAfterControl == null) {
                    if (player.getActionControlSet().payResourceControl(listDiscounted)) {
                        if (canPayMilitaryStrength) {

                            //setto il costo della carta da pagare
                            player.getPlayerActionSet().getPayCard().setListToPay(cardToPay.getCostList().get(listToPay));

                            resetTempList();
                            resetCardToPay();
                            resetListDiscount();
                            return true;
                        }
                    }
                } else {
                    if (player.getActionControlSet().payResourceControl(listDiscounted)) {
                        if (canPayMilitaryStrength) {

                            //setto il costo della carta da pagare scontato dagli effetti e/o bonus
                            player.getPlayerActionSet().getPayCard().setListToPay(listDiscounted);

                            resetTempList();
                            resetCardToPay();
                            resetListDiscount();
                            return true;
                        }
                    }

                }
            } else {
                resetTempList();
                resetCardToPay();
                resetListDiscount();
                return true;
            }
        }
            resetTempList();
            resetCardToPay();
            resetListDiscount();
            return false;

        }

    @Override
    public String getControlStringError() {
        return "Non hai abbastanza risorse per pagare la carta";
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
