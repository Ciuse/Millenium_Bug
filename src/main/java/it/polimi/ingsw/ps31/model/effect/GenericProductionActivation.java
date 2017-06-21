package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 26/05/2017.
 */
public class GenericProductionActivation extends Effect {
    private final int basicValue;

    public GenericProductionActivation(int cardId,int basicValue) {
        super(cardId);
        this.basicValue = basicValue;
    }

    public GenericProductionActivation(int basicValue) {
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
    public String nameString(){
        return "ActProduction";
    }

}
