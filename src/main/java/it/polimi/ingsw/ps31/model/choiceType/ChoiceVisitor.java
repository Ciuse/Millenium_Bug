package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public interface ChoiceVisitor {
    public void visit(ChoiceListToPay choiceListToPay);
    void visit(ChoiceActiveEffect choiceActiveEffect);
    void visit(ChoiceTowerCardSpace choiceTowerCardSpace);
    void visit(ChoiceActiveExcommunication choiceActiveExcommunication);
    void visit(ChoiceFamilyMember choiceFamilyMember);
    void visit(ChoiceActionSpace choiceActionSpace);
    void visit(ChoiceActionToDo choiceActionToDo);
    void visit(ChoiceStartLeaderCard choiceStartLeaderCard);
    void visit(ChoicePersonalBonusTiles choicePersonalBonusTiles);
    void visit(ChoiceColor choiceColor);
    void visit(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy);
    void visit(ChoiceIfSupportTheChurch choiceIfSupportTheChurch);
    void visit(ChoicePrivilegeResource choicePrivilegeResource);
    void visit(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay);
    void visit(ChoiceLeaderToActive choiceLeaderToActive);
    void visit(ChoiceLeaderToDiscard choiceLeaderToDiscard);
    void visit(ChoiceTowerActionSpace choiceTowerActionSpace);
}
