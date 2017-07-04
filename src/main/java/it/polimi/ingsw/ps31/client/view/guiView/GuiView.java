package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskPlayerColorFrame;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.MainFrame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class GuiView extends View implements ActionListener{
    private MainFrame mainFrame;
    private AskPlayerColorFrame askPlayerColorFrame;

    public GuiView(PlayerId viewId, int playerMaxNumber) {
      super(viewId, playerMaxNumber);
        this.mainFrame=new MainFrame(this);
        SwingUtilities.invokeLater(new Runnable() {
           @Override
            public void run() {
               runTerminal();
            }
        });

   }



    public void actionPerformed(ActionEvent ev) {

    }


        @Override
    public void askActionSpace(ChoiceActionSpace choiceActionSpace) {
    }

    @Override
    public void askTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace) {
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().setSendNextClick(true);
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
        if(choiceColor.getPlayerColorList().size()==2){
            askPlayerColorFrame.getButtons()[0].setBackground(Color.RED);
            askPlayerColorFrame.getButtons()[0].setEnabled(true);
            askPlayerColorFrame.getButtons()[1].setBackground(Color.GREEN);
            askPlayerColorFrame.getButtons()[1].setEnabled(true);
            askPlayerColorFrame.startMainFrame();
        }
        if(choiceColor.getPlayerColorList().size()==3){
            askPlayerColorFrame.getButtons()[0].setBackground(Color.RED);
            askPlayerColorFrame.getButtons()[0].setEnabled(true);
            askPlayerColorFrame.getButtons()[1].setBackground(Color.GREEN);
            askPlayerColorFrame.getButtons()[1].setEnabled(true);
            askPlayerColorFrame.getButtons()[2].setBackground(Color.YELLOW);
            askPlayerColorFrame.getButtons()[2].setEnabled(true);
            askPlayerColorFrame.startMainFrame();
        }
        if(choiceColor.getPlayerColorList().size()==4){
            askPlayerColorFrame.getButtons()[0].setBackground(Color.RED);
            askPlayerColorFrame.getButtons()[0].setEnabled(true);
            askPlayerColorFrame.getButtons()[1].setBackground(Color.GREEN);
            askPlayerColorFrame.getButtons()[1].setEnabled(true);
            askPlayerColorFrame.getButtons()[2].setBackground(Color.YELLOW);
            askPlayerColorFrame.getButtons()[2].setEnabled(true);
            askPlayerColorFrame.getButtons()[3].setBackground(Color.BLUE);
            askPlayerColorFrame.getButtons()[3].setEnabled(true);
            askPlayerColorFrame.startMainFrame();
        }



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
    public void askFamilyToChangeValue(ChoiceFamilyMemberToChangeValue choiceFamilyMemberToChangeValue) {
        
    }

    @Override
    public void askVisualizationCommand() {

    }


    @Override
    public void runTerminal() {
        mainFrame.startMainFrame();

    }

    @Override
    public void printTower() {
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().printTower(getStateViewBoard());
    }

    @Override
    public void printTowerCardBox(StateViewTowerCardBox stateViewTowerCardBox) {
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().printSingleCardBox(stateViewTowerCardBox);

    }

    @Override
    public void printLastEvent(String string) {

    }

    @Override
    public void printLastState(String string) {

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

    public void printMyFamilyMembersOnPlayerPanel() {
                for (StateViewFamilyMember family : super.getMyStateViewPlayer().getStateViewFamilyMemberList()
                        ) {
                    mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjFamilyMemberPanel().getButtonsFamilyMemberPanel().printMyFamilyMembersOnPlayerPanel(family);
                }
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
