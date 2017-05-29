package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Effect.HarvestEffect;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class ProductionList extends  HarvestProductionList{

    /* Constructor */
    public ProductionList(Player player, EffectList firstProduction) {

       super(player, firstProduction);
    }

    @Override
    public void addEffect (EffectList effectList)
    {
        //Controllo che la lista sia di soli harvestEffect
        List<Effect> effectAsList = effectList.getEffectList();
        Iterator<Effect> itr = effectAsList.iterator();
        boolean errorFound = false;
        while(!errorFound && itr.hasNext())
        {
            Effect currentEffect = itr.next();
            if ( !currentEffect.getClass().equals(HarvestEffect.class) )
                errorFound = true;
        }
        if ( errorFound )
        {
            //TODO: eccezione
        }
        else
            //Aggiungo gli effetti alla lista
            super.addEffect(effectList);
    }

}
