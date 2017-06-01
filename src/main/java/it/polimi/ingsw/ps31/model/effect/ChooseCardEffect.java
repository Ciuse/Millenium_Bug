package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChooseCardEffect extends Effect{
    private final List<CardColor> cardColors;
    private final int diceValue;

    public ChooseCardEffect(List<CardColor> cardColors, int diceValue) {
        this.cardColors = cardColors;
        this.diceValue = diceValue;
    }


    @Override
    public void activate(Player player) {

//        ChooseCardAction chooseCardAction = new ChooseCardEffect();
//        chooseCardAction.setDiceCost(diceValue);
//        chooseCardAction.setCardColor(cardColors);
//        chooseCardAction.activate();
    }
}
