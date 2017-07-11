package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 19/06/2017.
 *
 * Scelta riguardante quale azione si vuole fare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceActionToDo extends ChoiceType {

    public ChoiceActionToDo() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
