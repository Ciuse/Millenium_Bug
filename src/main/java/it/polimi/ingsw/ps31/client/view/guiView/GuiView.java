package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskStartLeaderCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskStartPersonalBonusTiles;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.AskStartPlayerColor;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.MainFrame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation;
import it.polimi.ingsw.ps31.messages.messageVC.VCCouncilPrivilegeChoice;
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
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("SELEZIONA UNA CASELLA DELLA TORRE SU CUI VUOI PIAZZARE IL TUO FAMILY MEMBER");
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().setSendNextClick(true);
    }

    @Override
    public void askActionToDo(ChoiceActionToDo choiceActionToDo) {

        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUALE AZIONE VUOI FARE?");
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getChoosenButtonPanel().setEnabledActions(getMyStateViewPlayer().getStringPlayerAction());

    }

    @Override
    public void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect) {

    }

    @Override
    public void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard) {
     printMyTiles();
        AskStartLeaderCard askStartLeaderCard = new AskStartLeaderCard(choiceStartLeaderCard,this);
        askStartLeaderCard.startFrame();
    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {
     AskStartPersonalBonusTiles askStartPersonalBonusTiles = new AskStartPersonalBonusTiles(choicePersonalBonusTiles,this);
     askStartPersonalBonusTiles.startFrame();
    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {
        AskStartPlayerColor askStartPlayerColor = new AskStartPlayerColor(choiceColor,this);
        askStartPlayerColor.startFrame();

    }

    @Override
    public void askFamilyMember(ChoiceFamilyMember choiceFamilyMember) {
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUALE FAMILY MEMBER VUOI USARE?");
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjFamilyMemberPanel().getButtonsFamilyMemberPanel().setEnabledFamilyMember();
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
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().setActiveLeader(true);
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().enableLeader();
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getButtonOpenLeaderCard().doClick();
    }

    @Override
    public void askLeaderToDiscard(ChoiceLeaderToDiscard choiceLeaderToDiscard) {
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().setDiscardLeader(true);
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().enableLeader();
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getButtonOpenLeaderCard().doClick();

    }

    @Override
    public void askServantToPay(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUANTI SERVITORI VUOI PAGARE PER AUMENTARE IL VALORE DEL TUO FAMILY MEMBER?");

    }


    @Override
    public void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource) {
        //TODO ERA SOLO DI PROVA, IMPLEMENTARLO BENE
        notifyController(new VCCouncilPrivilegeChoice(getViewId(), ViewStaticInformation.getResourceListFromCouncilPrivilege().get(0)));
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
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().setStateViewLeaderCardList(getMyStateViewPlayer().getStateViewLeaderCardList());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjLeaderCardPanel().getLeaderCardsOpenedPanel().fillLeaderPanel();

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
