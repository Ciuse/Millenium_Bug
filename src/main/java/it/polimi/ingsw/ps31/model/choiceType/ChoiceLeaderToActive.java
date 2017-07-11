package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 22/06/2017.
 *
 * Scelta di quale leader si vuole attivare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceLeaderToActive extends ChoiceType {

    public ChoiceLeaderToActive() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
