package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;

import java.util.List;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateViewEffect {
    private String nameEffect = null;
    private List<String> resourceToPayList = null;
    private List<String> resourceToGainList = null;
    private String resourceToGain = null;
    private CardColor cardColor = null;
    private boolean anyColor = false;
    private int diceValue = -1;
    private int basicValue = +10; //numero che non ha significato come valore base di un effetto produzione/raccolto
    private String resourceDiscount = null;
    private String requiredResource = null;
    private StateEffect stateEffect1 = null;
    private StateEffect stateEffect2 = null;
    private StateEffect stateEffect3 = null;


    public StateViewEffect(String nameEffect, String resourceToGain,int basicValue) {
        this.nameEffect = nameEffect;
        this.resourceToGain = resourceToGain;
        this.basicValue = basicValue;
    }

    public StateViewEffect() {
    }

    public String getNameEffect() {
        return nameEffect;
    }

    public List<String> getResourceToPayList() {
        return resourceToPayList;
    }

    public List<String> getResourceToGainList() {
        return resourceToGainList;
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

    public StateEffect getStateEffect3() {
        return stateEffect3;
    }

    public boolean isAnyColor() {
        return anyColor;
    }

    public void updateState(StateEffect stateEffect){

        if(stateEffect.getNameEffect()!=null){
            this.nameEffect = stateEffect.getNameEffect();
        }
        if(stateEffect.getBasicValue()!=+10){
            this.basicValue = stateEffect.getBasicValue();
        }
        if(stateEffect.getCardColor()!=null){
            this.cardColor = stateEffect.getCardColor();
        }
        if(stateEffect.isAnyColor()!=false){
            this.anyColor = stateEffect.isAnyColor();
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
        if(stateEffect.getResourceToGainList()!=null){
            this.resourceToGainList = stateEffect.getResourceToGainList();
        }
        if(stateEffect.getResourceToPayList()!=null){
            this.resourceToPayList = stateEffect.getResourceToPayList();
        }
        if(stateEffect.getStateEffect1()!=null){
            this.stateEffect1 = stateEffect.getStateEffect1();
        }
        if(stateEffect.getStateEffect2()!=null){
            this.stateEffect2 = stateEffect.getStateEffect2();
        }
        if(stateEffect.getStateEffect3()!=null){
            this.stateEffect3 = stateEffect.getStateEffect3();
        }
    }
}
