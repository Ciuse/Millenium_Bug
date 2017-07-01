package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.HarvestEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class HarvestList extends HarvestProductionList{
    private List<HarvestEffect> effectList = new ArrayList<>();

    /* Constructor */
    public HarvestList(Player player, HarvestEffect firstHarvest) {
        super(player);
        this.addEffect(firstHarvest);
    }

    /* Class Methods */
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
        super.getPlayer().addTempResoucesToPlayerResources();
    }
}
