package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 * Scelta di quale casella della torre occupare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceTowerCardSpace extends ChoiceType {

    public ChoiceTowerCardSpace() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
