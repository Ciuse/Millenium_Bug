package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.Effect;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateEffect extends StateInfo{
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


    public StateEffect(Effect effect) {
        if(effect!=null) {
            this.nameEffect = effect.nameString();
            this.resourceToPay = effect.resourcesToPayString().toString();
            this.basicValue = effect.getBasicValue();
            this.resourceToGain = effect.resourceToGainString().toString();
            this.diceValue = effect.getDiceValue();
            this.cardColor = effect.getCardColor();
            this.requiredResource = effect.requiredResourceString();
            this.resourceDiscount = effect.resourceDiscountString();
            if(effect.getGetResourceEffect()!=null){
                this.stateEffect1 = new StateEffect(effect.getGetResourceEffect());
            }
            if (effect.getGetResource()!=null){
                this.stateEffect2 = new StateEffect(effect.getGetResource());
            }
        }

    }

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

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
