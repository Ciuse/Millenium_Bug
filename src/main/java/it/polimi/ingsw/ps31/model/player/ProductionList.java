package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceIfActiveEffect;
import it.polimi.ingsw.ps31.model.effect.ProductionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class ProductionList extends  HarvestProductionList{
    private List<ProductionEffect> effectList= new ArrayList<>();

    /* Constructor */
    public ProductionList(Player player, ProductionEffect firstProduction) {
        super(player);
        this.addEffect(firstProduction);
    }

    /* Class Methods */

    public void addEffect (ProductionEffect effectToAdd)
    {
        this.effectList.add(effectToAdd);
    }

    public List<ProductionEffect> getEffectList() {
        return effectList;
    }

    @Override
    public void activate(int diceValue)
    {
        for(ProductionEffect currentEffect : effectList)
        {
            //non chiedo nulla, questo tipo di effetto va attivato per forza
            if (diceValue >= currentEffect.getProductionActionValue()){
                if(currentEffect.getGetResourceEffect()!=null) {
                    currentEffect.getGetResourceEffect().activate(super.getPlayer());
                }
                //non chiedo nulla, questo tipo di effetto va attivato per forza
                if(currentEffect.getGetResourceEffectFromCard()!=null){
                    currentEffect.getGetResourceEffectFromCard().activate(super.getPlayer());
                }
                //chiedo al player se vuoile attivare questo tipo di effetto o no
                if(currentEffect.getChangeResourceEffect()!=null){
                    String string =" Vuoi attivare questo effetto?";
                    super.getPlayer().getModel().notifyViews(new MVAskChoice(super.getPlayer().getPlayerId(),string,new ChoiceIfActiveEffect(currentEffect.getChangeResourceEffect().getCardId())));
                    boolean choice = super.getPlayer().getModel().getModelChoices().waitActiveEffect();
                    if(choice) {
                        currentEffect.getChangeResourceEffect().activate(super.getPlayer());
                    }
                }
            }
            super.getPlayer().addTempResoucesToPlayerResources();
        }

    }

}
