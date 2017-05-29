package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.effect.HarvestEffect;

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
