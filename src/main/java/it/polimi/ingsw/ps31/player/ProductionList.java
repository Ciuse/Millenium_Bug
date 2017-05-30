package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.board.Production;
import it.polimi.ingsw.ps31.effect.Effect;
import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.effect.HarvestEffect;
import it.polimi.ingsw.ps31.effect.ProductionEffect;

import java.util.Iterator;
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
