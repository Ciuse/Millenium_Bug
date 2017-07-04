package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 03/07/2017.
 */
public class ChoiceFamilyMemberToChangeValue extends ChoiceType {
    private final int newValue;

    public ChoiceFamilyMemberToChangeValue(int newValue) {
        this.newValue = newValue;
    }

    public int getNewValue() {
        return newValue;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
