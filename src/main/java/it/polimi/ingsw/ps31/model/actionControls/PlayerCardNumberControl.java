package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 25/05/2017.
 */
public class PlayerCardNumberControl extends Control {
    private final static int MAX_CARD_NUMBER = 6;
    private CardColor cardColor = null;

    /* Constructor */
    public PlayerCardNumberControl(Player player) {
        super(player);
    }



    /* Setters & Getters */
    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public String getControlStringError() {
        return "Non puoi mettere un'altra carta " +this.cardColor.toString()+" nella tua personal board ";
    }
    /* Resetters */
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

        boolean ret = this.player.getPlayerCardList().getSpecificCardList(cardColor).size() < MAX_CARD_NUMBER;
        resetCardColor();

        return ret;
    }
}
