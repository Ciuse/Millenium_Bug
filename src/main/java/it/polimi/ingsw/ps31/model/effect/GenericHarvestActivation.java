package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Giuseppe on 26/05/2017.
 */
public class GenericHarvestActivation extends Effect { //è l'effetto generico presente sul tabellone
    private final int basicValue;

    public  GenericHarvestActivation(int basicValue){
        this.basicValue = basicValue;
    }

    public int getBasicValue() {
        return basicValue;
    }

    @Override
    public int getDiceValue() {
        return 0;
    }

    @Override
    public CardColor getCardColor() {
        return null;
    }

    @Override
    public Effect getGetResource() {
        return null;
    }

    @Override
    public GetResourceEffect getGetResourceEffect() {
        return null;
    }

    @Override
    public String resourceDiscountString() {
        return null;
    }

    @Override
    public void activate(Player player) {
        int diceValue = player.getLastUsedFamilyMember().getTotalValue()+basicValue;
        player.getPlayerActionSet().activateHarvest(diceValue);
    }
    public String nameString(){
        return "ActHarvest";
    }

    @Override
    public List<String> resourcesToPayString() {
        return null;
    }

    @Override
    public List<String> resourcesToGainString() {
        return null;
    }

    @Override
    public String resourceToGainString() {
        return null;
    }

    @Override
    public String requiredResourceString() {
        return null;
    }
}
