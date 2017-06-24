package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.HarvestEffect;
import it.polimi.ingsw.ps31.model.effect.ProductionEffect;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;
import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBonusTiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBonusTiles {
    private PlayerId playerId;
    private final int personalBonusTilesId;
    private final HarvestEffect harvestEffect ;
    private final ProductionEffect productionEffect;


    public PersonalBonusTiles(int personalBonusTilesId, HarvestEffect harvestEffect, ProductionEffect productionEffect) {
        this.personalBonusTilesId = personalBonusTilesId;
        this.harvestEffect = harvestEffect;
        this.productionEffect = productionEffect;
    }

    public HarvestEffect getHarvestEffect() {
        return harvestEffect;
    }

    public ProductionEffect getProductionEffect() {
        return productionEffect;
    }

    public int getPersonalBonusTilesId() {
        return personalBonusTilesId;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public StatePersonalBonusTiles getStatePersonalBonusTiles(){
        List<StateEffect> stateEffectList = new ArrayList<>();
        stateEffectList.add(new StateEffect(harvestEffect));
        stateEffectList.add(new StateEffect(productionEffect));
        StatePersonalBonusTiles statePersonalBonusTiles = new StatePersonalBonusTiles(playerId, personalBonusTilesId, stateEffectList);
        return statePersonalBonusTiles;
    }
}



