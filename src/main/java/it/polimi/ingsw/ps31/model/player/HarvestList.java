package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.HarvestEffect;

import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class HarvestList extends HarvestProductionList{
    private List<HarvestEffect> effectList;

    /* Constructor */
    public HarvestList(Player player, List<HarvestEffect> firstHarvest) {
        super(player);
        this.addEffectList(firstHarvest);
    }

    /* Class Methods */
    private void addEffectList(List<HarvestEffect> effectListToAdd)
    {
        this.effectList.addAll(effectListToAdd);
    }

    private void addEffect (HarvestEffect effectToAdd)
    {
        this.effectList.add(effectToAdd);
    }

    @Override
    public void activate(int diceValue)
    {
        for(HarvestEffect currentEffect : effectList)
        {
            if (currentEffect.getHarvestActionValue() > diceValue)
                currentEffect.getGetResourceEffect().activate(super.getPlayer());
        }
    }
}
