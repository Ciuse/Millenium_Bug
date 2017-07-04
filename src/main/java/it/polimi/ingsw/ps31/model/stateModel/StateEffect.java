package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.Effect;

import java.util.List;


/**
 * Created by giulia on 13/06/2017.
 */
public class StateEffect extends StateType {
    private int cardId =-1;
    private String nameEffect = null;
    private String resourceToGain = null;
    private List<String> resourceToGainList =null;
    private List<String> resourceToPayList =null;
    private CardColor cardColor = null;
    private boolean anyColor = false;
    private int diceValue = -1;
    private int basicValue = -1;
    private String resourceDiscount = null;
    private String requiredResource = null;
    private StateEffect stateEffect1 = null;
    private StateEffect stateEffect2 = null;
    private StateEffect stateEffect3 = null;
    private String bonusName=null;

    public StateEffect(Effect effect) {
        if(effect!=null) {
            this.cardId = effect.getCardId();
            this.nameEffect = effect.getNameString();
            this.basicValue = effect.getBasicValue();
            this.resourceToGainList =effect.getResourceToGainListString();
            this.resourceToPayList =effect.getResourceToPayListString();
            this.resourceToGain = effect.getResourceToGainString();
            this.diceValue = effect.getDiceValue();
            this.cardColor = effect.getCardColor();
            this.anyColor=effect.isAnyColor();
            this.requiredResource = effect.getRequiredResourceString();
            this.resourceDiscount = effect.resourceDiscountString();
            if(effect.getGetResourceEffect()!=null){
                this.stateEffect1 = new StateEffect(effect.getGetResourceEffect());
            }
            if (effect.getChangeResourceEffect()!=null){
                this.stateEffect2 = new StateEffect(effect.getChangeResourceEffect());
            }
            if(effect.getGetResourceEffectFromCard()!=null){
                this.stateEffect3 = new StateEffect(effect.getGetResourceEffectFromCard());
            }
            if(effect.getBonus()!=null){
                bonusName=effect.getBonus().getName();
            }
        }

    }

    public int getCardId() {
        return cardId;
    }

    public String getNameEffect() {
        return nameEffect;
    }

    public List<String> getResourceToGainList() {
        return resourceToGainList;
    }

    public List<String> getResourceToPayList() {
        return resourceToPayList;
    }

    public String getResourceToGain() {
        return resourceToGain;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public boolean isAnyColor() {
        return anyColor;
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

    public StateEffect getStateEffect3() {
        return stateEffect3;
    }

    public String getBonusName() {
        return bonusName;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
