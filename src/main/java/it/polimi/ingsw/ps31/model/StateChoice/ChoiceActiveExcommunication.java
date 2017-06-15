package it.polimi.ingsw.ps31.model.StateChoice;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class ChoiceActiveExcommunication implements ChoiceVisitable {
    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
