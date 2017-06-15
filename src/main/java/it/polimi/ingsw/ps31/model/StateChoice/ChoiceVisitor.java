package it.polimi.ingsw.ps31.model.StateChoice;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public interface ChoiceVisitor {
    public void visit(ChoiceInt choiceInt);
    void visit(ChoiceActiveEffect choiceActiveEffect);
    void visit(ChoiceTowerCardSpace choiceTowerCardSpace);
    void visit(ChoiceActiveExcommunication choiceActiveExcommunication);
    void visit(ChoiceFamilyMember choiceFamilyMember);
    void visit(ChoiceActionSpace choiceActionSpace);
}
