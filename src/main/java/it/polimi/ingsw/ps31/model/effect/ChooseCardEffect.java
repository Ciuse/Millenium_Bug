package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto che ti permette di scegliere una carta di un certo valore massimoe un certo colore (o qualsiasi)
 */
public class ChooseCardEffect extends Effect{
    private CardColor cardColor = null;
    private boolean anyColor=false;
    private final int diceValue;

    public ChooseCardEffect(int cardId,CardColor cardColor, int diceValue, boolean anyColor) {
        super(cardId);
        this.anyColor=anyColor;
        this.cardColor = cardColor;
        this.diceValue = diceValue;
    }

    /**
     * attiva l'azione di scegli carta
     * @see it.polimi.ingsw.ps31.model.actions.ActionChooseCard
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().chooseCard(this.cardColor,this.diceValue,this.anyColor);
    }

    public String getNameString(){
        return "ChooseCard";
    }

    public boolean isAnyColor() {
        return anyColor;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public CardColor getCardColor() {
        return this.cardColor;
    }
}
