package it.polimi.ingsw.ps31.model.stateChoice;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class ChoiceActiveEffect extends ChoiceType {
    int cardIdEffect;

    public ChoiceActiveEffect(int cardIdEffect) {
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
