package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskLeaderCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskPersonalBonusTiles;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskPlayerColor;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.MainFrame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class GuiView extends View implements ActionListener{
    private MainFrame mainFrame;

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
     printMyTiles();
        AskLeaderCard askLeaderCard = new AskLeaderCard(choiceStartLeaderCard,this);
        askLeaderCard.startFrame();
    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {
     AskPersonalBonusTiles askPersonalBonusTiles = new AskPersonalBonusTiles(choicePersonalBonusTiles,this);
     askPersonalBonusTiles.startFrame();
    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {
        AskPlayerColor askPlayerColor = new AskPlayerColor(choiceColor,this);
        askPlayerColor.startFrame();

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
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getPersonalLeaderCardsPanel().setStateViewLeaderCardList(getMyStateViewPlayer().getStateViewLeaderCardList());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getPersonalLeaderCardsPanel().fillLeaderPanel();

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
 public void printMyTiles() {
     System.out.println(getMyStateViewPlayer().getStateViewPersonalBonusTiles());
     mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBonusTilesPanel().printTiles(getMyStateViewPlayer().getStateViewPersonalBonusTiles());
//     mainFrame.repaint();
//     mainFrame.revalidate();
 }

 @Override
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
