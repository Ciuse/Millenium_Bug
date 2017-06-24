package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.*;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class MVChoiceInfoVisitor implements ChoiceVisitor {
    View view;

    public void setView(View view){
        this.view=view;
    }

    @Override
    public void visit(ChoiceListToPay choiceListToPay) {
        view.setCmdInterpreterView(new IntrChoiceListToPay());
        view.askListToPay(choiceListToPay);
    }

    @Override
    public void visit(ChoiceActiveEffect choiceActiveEffect) {
        view.setCmdInterpreterView(new IntrChoiceActiveEffect());
        view.askIfActiveEffect(choiceActiveEffect);
    }

    @Override
    public void visit(ChoiceTowerCardSpace choiceTowerCardSpace) {

    }

    @Override
    public void visit(ChoiceActiveExcommunication choiceActiveExcommunication) {

    }

    @Override
    public void visit(ChoiceFamilyMember choiceFamilyMember) {
        view.setCmdInterpreterView(new IntrChoiceFamilyMember());
        view.askFamilyMember(choiceFamilyMember);
    }

    @Override
    public void visit(ChoiceActionSpace choiceActionSpace) {
        view.setCmdInterpreterView(new IntrChoiceActionSpace());
        view.askActionSpace(choiceActionSpace);
    }

    @Override
    public void visit(ChoiceActionToDo choiceActionToDo) {
        view.setCmdInterpreterView(new IntrChoicePlayerAction());
        view.askActionToDo(choiceActionToDo);
    }

    @Override
    public void visit(ChoiceStartLeaderCard choiceStartLeaderCard) {
        view.setCmdInterpreterView(new IntrChoiceStartLeader());
        view.askStartLeaderToKeep(choiceStartLeaderCard);
    }

    @Override
    public void visit(ChoicePersonalBonusTiles choicePersonalBonusTiles) {

    }

    @Override
    public void visit(ChoiceColor choiceColor) {
        view.setCmdInterpreterView(new IntrChoiceColor());
        view.askPlayerColor(choiceColor);
    }

    @Override
    public void visit(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {

    }

    @Override
    public void visit(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {
        view.setCmdInterpreterView(new IntrChoiceIfSupportChurch());
        view.askIfSupportChurch(choiceIfSupportTheChurch);
    }

    @Override
    public void visit(ChoicePrivilegeResource choicePrivilegeResource) {

    }

    @Override
    public void visit(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {
        view.setCmdInterpreterView(new IntrChoiceServantToPay());
        view.askServantToPay(choiceNumberOfServantsToPay);
    }

    @Override
    public void visit(ChoiceLeaderToActive choiceLeaderToActive) {
        view.setCmdInterpreterView(new IntrChoiceLeaderToActive());
        view.askLeaderToActive(choiceLeaderToActive);
    }

    @Override
    public void visit(ChoiceLeaderToDiscard choiceLeaderToDiscard) {
        view.setCmdInterpreterView(new IntrLeaderToDiscard());
        view.askLeaderToDiscard(choiceLeaderToDiscard);
    }

    @Override
    public void visit(ChoiceTowerActionSpace choiceTowerActionSpace) {

    }
}
