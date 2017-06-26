package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.*;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public  class GuiView extends View implements ActionListener{
    private JButton jButtonChangeCard;
    private GameBoardPanel gameBoardPanel;
    private Frame frame;

    public GuiView(PlayerId viewId, int playerMaxNumber) {
        super(viewId, playerMaxNumber);
    } //TODO TOGLIERE ABSTRACT ED IMPLEMEMTARE TUTTI I METODI

    public void actionPerformed(ActionEvent ev) {

    }


        @Override
    public void askActionSpace(ChoiceActionSpace choiceActionSpace) {

    }

    @Override
    public void AskTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace) {

    }

    @Override
    public void askActionToDo(ChoiceActionToDo choiceActionToDo) {

    }

    @Override
    public void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect) {

    }

    @Override
    public void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard) {

    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {

    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {

    }

    @Override
    public void askFamilyMember(ChoiceFamilyMember choiceFamilyMember) {

    }

    @Override
    public void askIfSupportChurch(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {

    }

    @Override
    public void askListToPay(ChoiceListToPay choiceListToPay) {

    }

    @Override
    public void askLeaderEffectToCopy(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {

    }

    @Override
    public void askLeaderToActive(ChoiceLeaderToActive choiceLeaderToActive) {

    }

    @Override
    public void askLeaderToDiscard(ChoiceLeaderToDiscard choiceLeaderToDiscard) {

    }

    @Override
    public void askServantToPay(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {

    }


    @Override
    public void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource) {

    }

    @Override
    public void askComand() throws IOException {

    }

    @Override
    public void runTerminal() throws IOException {

    }

    @Override
    public void printTower() {

    }

    @Override
    public void printLastEvent(String string) {

    }

    @Override
    public void printPlayerInAction() {

    }

    @Override
    public void printAllPlayer() {

    }

    @Override
    public void printPlayerAction() {

    }

    @Override
    public void printPersonalBoardInAction() {

    }

    @Override
    public void printAllPersonalBoard() {

    }

    @Override
    public void printFamilyMemberInAction() {

    }

    @Override
    public void printBoardActionSpace() {

    }

    @Override
    public void printDevelopmentCard(int cardId) {

    }

    @Override
    public void printTextBox() {

    }

    @Override
    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {

    }




}
