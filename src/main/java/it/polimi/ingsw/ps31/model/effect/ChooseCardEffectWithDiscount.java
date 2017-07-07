package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto che ti permette di scegliere una carta di un certo valore massimoe un certo colore (o qualsiasi) con anche uno sconto in risorse
 * @see ChooseCardEffect
 */
public class ChooseCardEffectWithDiscount extends ChooseCardEffect {
    private final ResourceList resourcesDiscount; //la carta che devo prendere ha uno sconto sulle risorse
    public ChooseCardEffectWithDiscount(int cardId,CardColor cardColor, int diceValue, ResourceList resourcesDiscount,boolean anyColor) {
        super(cardId,cardColor, diceValue, anyColor);
        this.resourcesDiscount = resourcesDiscount;
    }

    /**
     * attiva l'azione di scegli carta settando anche il parametro delle risorse da scontare
     * @see it.polimi.ingsw.ps31.model.actions.ActionChooseCard
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().chooseCard(super.getCardColor(),super.getDiceValue(),super.isAnyColor(),this.resourcesDiscount);
    }
    public String getNameString(){
        return "ChooseCard+D";
    }

    public String resourceDiscountString() {
        return resourcesDiscount.toString();
    }
}
