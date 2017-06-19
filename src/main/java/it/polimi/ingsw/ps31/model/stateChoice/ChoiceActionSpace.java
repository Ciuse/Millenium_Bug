package it.polimi.ingsw.ps31.model.stateChoice;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class ChoiceActionSpace extends ChoiceType {
    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
