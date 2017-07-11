package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 22/06/2017.
 *
 * Scelta di quale leader si vuole scartare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceLeaderToDiscard extends ChoiceType {

    public ChoiceLeaderToDiscard() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
