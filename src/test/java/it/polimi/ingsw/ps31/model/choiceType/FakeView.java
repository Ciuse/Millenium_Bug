package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateActionSpace;

import java.util.List;

/**
 * Created by Francesco on 10/07/2017.
 */
public class FakeView extends View {
    private int state;
    private List<Integer> effects;

    public FakeView(PlayerId viewId, int playerMaxNumber, List<Integer> effects) {
        super(viewId, playerMaxNumber);
        state = 0;
        this.effects = effects;
    }

    @Override
    public void askActionSpace(ChoiceActionSpace choiceActionSpace) {
        effects.add(1);
    }

    @Override
    public void askTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace) {
        effects.add(2);
    }

    @Override
    public void askActionToDo(ChoiceActionToDo choiceActionToDo) {
        effects.add(3);
    }

    @Override
    public void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect) {
        effects.add(4);
    }

    @Override
    public void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard) {
        effects.add(5);
    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {
        effects.add(6);
    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {
        effects.add(7);
    }

    @Override
    public void askFamilyMember(ChoiceFamilyMember choiceFamilyMember) {
        effects.add(8);
    }

    @Override
    public void askIfSupportChurch(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {
        effects.add(9);
    }

    @Override
    public void askListToPay(ChoiceListToPay choiceListToPay) {
        effects.add(10);
    }

    @Override
    public void askLeaderEffectToCopy(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {
        effects.add(11);
    }

    @Override
    public void askLeaderToActive(ChoiceLeaderToActive choiceLeaderToActive) {
        effects.add(12);
    }

    @Override
    public void askLeaderToDiscard(ChoiceLeaderToDiscard choiceLeaderToDiscard) {
        effects.add(13);
    }

    @Override
    public void askServantToPay(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {
        effects.add(14);
    }

    @Override
    public void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource) {
        effects.add(15);
    }

    @Override
    public void askFamilyToChangeValue(ChoiceFamilyMemberToChangeValue choiceFamilyMemberToChangeValue) {
        effects.add(16);
    }

    @Override
    public void askVisualizationCommand() {
        effects.add(17);
    }

    @Override
    public void runTerminal() {
        effects.add(18);
    }

    @Override
    public void printTower() {
        effects.add(19);
    }

    @Override
    public void printTowerCardBox(StateViewTowerCardBox stateViewTowerCardBox) {
        effects.add(20);
    }

    @Override
    public void printLastEvent(String string) {
        effects.add(21);
    }

    @Override
    public void printLastState(String string) {
        effects.add(22);
    }

    @Override
    public void printPlayerInAction() {
        effects.add(23);
    }

    @Override
    public void printAllPlayer() {
        effects.add(24);
    }

    @Override
    public void printPlayerAction() {
        effects.add(25);
    }

    @Override
    public void printPersonalBoardInAction() {
        effects.add(26);
    }

    @Override
    public void printAllPersonalBoard() {
        effects.add(27);
    }

    @Override
    public void printFamilyMemberInAction() {
        effects.add(28);
    }

    @Override
    public void printBoardActionSpace() {
        effects.add(29);
    }

    @Override
    public void printSingleBoardActionSpace(StateActionSpace actionSpace) {
        effects.add(30);
    }

    @Override
    public void printSingleTowerActionSpace(StateActionSpace actionSpace) {
        effects.add(31);
    }

    @Override
    public void printDevelopmentCard(int cardId) {
        effects.add(32);
    }

    @Override
    public void printTextBox() {
        effects.add(33);
    }

    @Override
    public void printMyFamilyMembersOnPlayerPanel() {
        effects.add(34);
    }

    @Override
    public void printMyTiles() {
        effects.add(35);
    }

    @Override
    public void printExcommunications() {
        effects.add(36);
    }

    @Override
    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {
        effects.add(37);
    }
}
