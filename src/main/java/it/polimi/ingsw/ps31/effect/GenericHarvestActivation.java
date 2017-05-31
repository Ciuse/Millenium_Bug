package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Giuseppe on 26/05/2017.
 */
public class GenericHarvestActivation extends Effect {
    private final int basicValue;
    public  GenericHarvestActivation(int basicValue){
        this.basicValue = basicValue;
    }

    public int getBasicValue() {
        return basicValue;
    }

    @Override
    public void activate(Player player) {
        int diceValue = player.getLastUsedFamilyMember().getTotalValue()+basicValue;
        player.getPlayerActionSet().activateHarvest(diceValue);
    }
}
