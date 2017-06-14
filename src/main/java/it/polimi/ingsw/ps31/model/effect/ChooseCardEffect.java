package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChooseCardEffect extends Effect{
    private CardColor cardColor = null;
    private boolean anyColor=false;
    private final int diceValue;

    public ChooseCardEffect(CardColor cardColor, int diceValue, boolean anyColor) {
        this.anyColor=anyColor;
        this.cardColor = cardColor;
        this.diceValue = diceValue;
    }


    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().chooseCard(this.cardColor,this.diceValue,0,this.anyColor,null);
    }
    public String nameString(){
        return "ChooseCard";
    }

    public boolean isAnyColor() {
        return anyColor;
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

    @Override
    public int getBasicValue() {
        return 0;
    }

    public int getDiceValue() {
        return diceValue;
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
}
