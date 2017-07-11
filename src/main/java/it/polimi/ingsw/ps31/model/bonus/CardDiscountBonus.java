package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 *
 * Bonus (e Malus) che aumenta (o diminuisce) il tuo valore del famigliare quando si va a prendere
 * una carta di uno specifico colore.
 * A volte è possibile che oltre allo sconto sulla carta vi è uno sconto sulle risorse da pagare di quella carta
 *
 * @see it.polimi.ingsw.ps31.model.actionControls.PayCardControl
 * @see it.polimi.ingsw.ps31.model.actionControls.DiceValueCardSpaceControl
 */
public class CardDiscountBonus extends Bonus {
    private final int value;
    private final CardColor cardColor;
    private final boolean anyColor;
    private ResourceList resourceListDiscount;

    public CardDiscountBonus(int value, CardColor cardColor, boolean anyColor) {
        super();
        this.value = value;
        this.cardColor = cardColor;
        this.anyColor = anyColor;
    }

    public CardDiscountBonus(int value, CardColor cardColor, boolean anyColor, ResourceList resourceListDiscount) {
        this.value = value;
        this.cardColor = cardColor;
        this.anyColor = anyColor;
        this.resourceListDiscount = resourceListDiscount;
    }

    public int getValue() {
        return value;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    @Override
    public void activate(Player player) {
        if(resourceListDiscount!=null){
            player.getPlayerActionSet().getActionControlSet().getPayCardControl().addCardResourceDiscount(cardColor,anyColor,resourceListDiscount);
        }
        player.getPlayerActionSet().getActionControlSet().getDiceValueCardSpaceControl().addCardDiceBonus(cardColor, value);


    }
}
