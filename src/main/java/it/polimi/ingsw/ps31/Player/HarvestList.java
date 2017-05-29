package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Effect.HarvestEffect;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 */
public class HarvestList extends HarvestProductionList{

    /* Constructor */
    public HarvestList(Player player, EffectList firstHarvest) {
        super(player, firstHarvest);
    }

   @Override
   public void addEffect (EffectList effectList)
   {

       if ( checkEffectList(effectList, HarvestEffect.class) )
       {
           //Aggiungo gli effetti alla lista
           super.addEffect(effectList);
       }
       else
       {
           //TODO: eccezione
       }

   }


}
