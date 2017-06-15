package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.ProductionEffect;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class ProductionList extends  HarvestProductionList{
    private List<ProductionEffect> effectList;

    /* Constructor */
    public ProductionList(Player player, ProductionEffect firstProduction) {
        super(player);
        this.addEffect(firstProduction);
    }

    /* Class Methods */

    private void addEffect (ProductionEffect effectToAdd)
    {
        this.effectList.add(effectToAdd);
    }

    public List<ProductionEffect> getEffectList() {
        return effectList;
    }

    @Override
    public void activate(int diceValue)
    {
        ResourceList tempResourceToPayList = new ResourceList();
        ResourceList tempResourceToGainList = new ResourceList();
        ResourceList tempResourcePlayerList = new ResourceList(super.getPlayer().getPlayerResources().getPlayerResourceList().getResourceList());

        for(ProductionEffect currentEffect : effectList)
        {
            if (currentEffect.getProductionActionValue() > diceValue){
                if(currentEffect.getGetResourceEffect()!=null) {
                    currentEffect.getGetResourceEffect().activate(super.getPlayer());
                }
                if(currentEffect.getGetResourceEffectFromCard()!=null){
                    currentEffect.getGetResourceEffectFromCard().activate(super.getPlayer());
                }
                if(currentEffect.getChangeResourceEffect()!=null){
                    super.notifyViews(new ChoiceActiveEffect(currentEffect.getChangeResourceEffect().getCardId()));
                    boolean choice = super.waitActiveEffect();
                    if(choice==true) {
                        currentEffect.getChangeResourceEffect().activate(super.getPlayer());
                    }
                }
            }
            super.getPlayer().addTempResoucesToPlayerResources();
        }

    }

}
