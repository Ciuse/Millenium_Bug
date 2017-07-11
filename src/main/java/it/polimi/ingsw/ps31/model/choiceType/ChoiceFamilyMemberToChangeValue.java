package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 03/07/2017.
 *
 * Scelta per cambiare il valore a un family member
 *
 * @see it.polimi.ingsw.ps31.client.view.View
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
