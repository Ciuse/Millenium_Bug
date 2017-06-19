package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 19/06/2017.
 */
public class ChoiseActionToDo extends ChoiceType {

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
