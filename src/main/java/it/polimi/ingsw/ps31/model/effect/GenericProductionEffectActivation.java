package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 26/05/2017.
 */
public class GenericProductionEffectActivation extends Effect {
    private final int basicValue;

    public GenericProductionEffectActivation(int cardId, int basicValue) {
        super(cardId);
        this.basicValue = basicValue;
    }

    public GenericProductionEffectActivation(int basicValue) {
        this.basicValue = basicValue;
    }

    public int getBasicValue() {
        return basicValue;
    }

    @Override
    public void activate(Player player) {
        int diceValue = player.getLastUsedFamilyMember().getTotalValue()+basicValue;
        player.getPlayerActionSet().activateProduction(diceValue);
    }
    public String getNameString(){
        return "ActProduction";
    }

}
