package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrChoiseActiveEffect;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class MVChoiceInfoVisitor implements ChoiceVisitor {
    View view;

    public void setView(View view){
        this.view=view;
    }

    @Override
    public void visit(ChoiceInt choiceInt) {

    }

    @Override
    public void visit(ChoiceActiveEffect choiceActiveEffect) {
        view.setCmdInterpreterView(new IntrChoiseActiveEffect());
//        view.askPlayer(choiceActiveEffect.cardIdEffect);
    }

    @Override
    public void visit(ChoiceTowerCardSpace choiceTowerCardSpace) {

    }

    @Override
    public void visit(ChoiceActiveExcommunication choiceActiveExcommunication) {

    }

    @Override
    public void visit(ChoiceFamilyMember choiceFamilyMember) {

    }

    @Override
    public void visit(ChoiceActionSpace choiceActionSpace) {

    }

    @Override
    public void visit(ChoiseActionToDo choiseActionToDo) {
        view.askChoicePlayerAction(choiseActionToDo);
    }

    @Override
       public void visit(ChoiceStartLeaderCard choiceStartLeaderCard) {
        view.askChoiceLeader(choiceStartLeaderCard);
    }

    @Override
    public void visit(ChoicePersonalBonusTiles choicePersonalBonusTiles) {

    }

    @Override
    public void visit(ChoiceColor choiceColor) {

    }

    @Override
    public void visit(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {

    }

    @Override
    public void visit(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {

    }

    @Override
    public void visit(ChoicePrivilegeResource choicePrivilegeResource) {

    }

    @Override
    public void visit(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {

    }
}
