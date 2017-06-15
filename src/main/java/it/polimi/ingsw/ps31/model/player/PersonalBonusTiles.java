package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.HarvestEffect;
import it.polimi.ingsw.ps31.model.effect.ProductionEffect;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBonusTiles {
    private final HarvestEffect harvestEffect ;
    private final ProductionEffect productionEffect;


    public PersonalBonusTiles(HarvestEffect harvestEffect, ProductionEffect productionEffect) {
        this.harvestEffect = harvestEffect;
        this.productionEffect = productionEffect;
    }

    public HarvestEffect getHarvestEffect() {
        return harvestEffect;
    }

    public ProductionEffect getProductionEffect() {
        return productionEffect;
    }
}



