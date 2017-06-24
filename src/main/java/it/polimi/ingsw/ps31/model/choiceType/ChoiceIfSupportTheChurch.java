package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 20/06/2017.
 */
public class ChoiceIfSupportTheChurch extends ChoiceType {


    public ChoiceIfSupportTheChurch() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
