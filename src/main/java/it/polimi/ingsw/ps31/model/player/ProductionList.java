package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.ProductionEffect;

import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class ProductionList extends  HarvestProductionList{
    private List<ProductionEffect> effectList;

    /* Constructor */
    public ProductionList(Player player, List<ProductionEffect> firstProduction) {
        super(player);
        this.addEffectList(firstProduction);
    }

    /* Class Methods */
    private void addEffectList(List<ProductionEffect> effectListToAdd)
    {
        this.effectList.addAll(effectListToAdd);
    }

    private void addEffect (ProductionEffect effectToAdd)
    {
        this.effectList.add(effectToAdd);
    }

    @Override
    public void activate(int diceValue)
    {
        for(ProductionEffect currentEffect : effectList)
        {
            if (currentEffect.getProductionActionValue() > diceValue)
                currentEffect.getGetResource().activate(super.getPlayer());
        }
    }
}
