package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateEffect;
import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateViewEffect {
    private String nameEffect = null;
    private String resourceToPay = null;
    private String resourceToGain = null;
    private CardColor cardColor = null;
    private int diceValue = -1;
    private int basicValue = -1;
    private String resourceDiscount = null;
    private String requiredResource = null;
    private StateEffect stateEffect1 = null;
    private StateEffect stateEffect2 = null;

    public String getNameEffect() {
        return nameEffect;
    }

    public String getResourceToPay() {
        return resourceToPay;
    }

    public String getResourceToGain() {
        return resourceToGain;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public int getBasicValue() {
        return basicValue;
    }

    public String getResourceDiscount() {
        return resourceDiscount;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public StateEffect getStateEffect1() {
        return stateEffect1;
    }

    public StateEffect getStateEffect2() {
        return stateEffect2;
    }

    public void updateState(StateEffect stateEffect){
        if(stateEffect.getNameEffect()!=null){
            this.nameEffect = stateEffect.getNameEffect();
        }
        if(stateEffect.getBasicValue()!=-1){
            this.basicValue = stateEffect.getBasicValue();
        }
        if(stateEffect.getCardColor()!=null){
            this.cardColor = stateEffect.getCardColor();
        }
        if(stateEffect.getDiceValue()!=-1){
            this.diceValue = stateEffect.getDiceValue();
        }
        if(stateEffect.getRequiredResource()!=null){
            this.requiredResource = stateEffect.getRequiredResource();
        }
        if(stateEffect.getResourceDiscount()!=null){
            this.resourceDiscount = stateEffect.getResourceDiscount();
        }
        if(stateEffect.getResourceToGain()!=null){
            this.resourceToGain = stateEffect.getResourceToGain();
        }
        if(stateEffect.getResourceToPay()!=null){
            this.resourceToPay = stateEffect.getResourceToPay();
        }
        if(stateEffect.getStateEffect1()!=null){
            this.stateEffect1 = stateEffect.getStateEffect1();
        }
        if(stateEffect.getStateEffect2()!=null){
            this.stateEffect2 = stateEffect.getStateEffect2();
        }
    }
}
