package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 * Scelta su quale family member usare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceFamilyMember extends ChoiceType {

    public ChoiceFamilyMember() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
