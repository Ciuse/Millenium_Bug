package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 * Scelta se attivare o no un effetto di una carta che impone una perdita di risorse
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceIfActiveEffect extends ChoiceType {
    private int cardIdEffect;

    public ChoiceIfActiveEffect(int cardIdEffect) {
        this.cardIdEffect = cardIdEffect;
    }

    public int getCardIdEffect() {
        return cardIdEffect;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
