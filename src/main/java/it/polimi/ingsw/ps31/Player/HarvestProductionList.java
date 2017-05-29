package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Effect.HarvestEffect;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 28/05/2017.
 */
public abstract class HarvestProductionList {
    private EffectList effectList;
    private  final Player player;

    /* Constructor */
    public HarvestProductionList(Player player, EffectList firstEffect) {

        this.player = player;
        addEffectList(firstEffect);
    }

    /* Class Methods */
    public void addEffect(EffectList effectList)
    {
        addEffectList(effectList);
    }

    public void activate(int diceValue)
    {
        //TODO: implementare
    }

    protected boolean checkEffectList(EffectList effectList, Class<? extends Effect> classToCheck)
    {
        //Controllo che la lista sia di soli harvestEffect
        List<Effect> effectAsList = effectList.getEffectList();
        Iterator<Effect> itr = effectAsList.iterator();
        boolean errorFound = false;
        while(!errorFound && itr.hasNext())
        {
            Effect currentEffect = itr.next();
            if ( !currentEffect.getClass().equals(classToCheck) )
                errorFound = true;
        }

        return errorFound;
    }

    private void addEffectList(EffectList effectListToAdd)
    {
        List<Effect> effectAsList = effectListToAdd.getEffectList();
        for (Effect currentEffect : effectAsList)
            this.effectList.addSpecificEffect(currentEffect);
    }
}
