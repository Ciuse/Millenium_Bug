package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 25/05/2017.
 */
public class PlayerCardNumberControl extends Control {
    private final int MAX_CARD_NUMBER = 6;
    private CardColor cardColor = null;

    /* Constructor */
    public PlayerCardNumberControl(Player player) {
        super(player);
    }

    /*Getters & Setters */
    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public void resetCardColor() {
        this.cardColor = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if (this.cardColor == null)
        {
            //TODO: gestire
            return false;
        }

        boolean ret = (this.player.getPlayerCardList().getSpecificCardList(cardColor).size() < MAX_CARD_NUMBER);
        resetCardColor();

        return ret;
    }
}
