package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.*;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.MainFrame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.messages.messageVC.*;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateActionSpace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getResourceListFromCouncilPrivilege;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class GuiView extends View implements ActionListener {
    private MainFrame mainFrame;

    public GuiView(PlayerId viewId, int playerMaxNumber) {
        super(viewId, playerMaxNumber);
        this.mainFrame = new MainFrame(this);
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
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().setSendClickBoard(true);
        //attivo bottoni board
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().changeButtonBoardState(true);
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getCouncilPanel().getActionSpaceCouncilPanel().setEnabled(true);
    }

    @Override
    public void askTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("SELEZIONA UNA CASELLA DELLA TORRE SU CUI VUOI PIAZZARE IL TUO FAMILY MEMBER");
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().setSendNextClick(true);
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getChosenActionButtonPanel().getButton6().setEnabled(true);
        //disabilito bottoni board
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().changeButtonBoardState(false);
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getCouncilPanel().getActionSpaceCouncilPanel().setEnabled(false);
    }

    @Override
    public void askActionToDo(ChoiceActionToDo choiceActionToDo) {

//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUALE AZIONE VUOI FARE?");
        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getChosenActionButtonPanel().setEnabledActions(getMyStateViewPlayer().getStringPlayerAction());

        //disabilito bottoni board
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().changeButtonBoardState(false);
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getCouncilPanel().getActionSpaceCouncilPanel().setEnabled(false);
    }


    @Override
    public void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect) {

//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("SELEZIONA SE ATTIVARE O NO L'EFFETTO DELLA CARTA "+choiceIfActiveEffect.getCardIdEffect());
        AskIfActiveEffect askIfActiveEffect = new AskIfActiveEffect(mainFrame);
        notifyController(new VCActiveEffectChoice(getViewId(), askIfActiveEffect.getInput(choiceIfActiveEffect)));
    }

    @Override
    public void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard) {
        printMyTiles();
        AskStartLeaderCard askStartLeaderCard = new AskStartLeaderCard(choiceStartLeaderCard, this);
        askStartLeaderCard.startFrame();
    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {
        AskStartPersonalBonusTiles askStartPersonalBonusTiles = new AskStartPersonalBonusTiles(choicePersonalBonusTiles, this);
        askStartPersonalBonusTiles.startFrame();
    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {
        AskStartPlayerColor askStartPlayerColor = new AskStartPlayerColor(choiceColor, this);
        askStartPlayerColor.startFrame();

    }

    @Override
    public void askFamilyMember(ChoiceFamilyMember choiceFamilyMember) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUALE FAMILY MEMBER VUOI USARE?");
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjFamilyMemberPanel().getButtonsFamilyMemberPanel().setEnabledFamilyMember();
    }

    @Override
    public void askIfSupportChurch(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("FASE RAPPORTI CON IL VATICANO");
        AskIfSupportChurch askListToPay = new AskIfSupportChurch(mainFrame);
        notifyController(new VCSupportTheChurchChoice(getViewId(), askListToPay.getInput()));
    }

    @Override
    public void askListToPay(ChoiceListToPay choiceListToPay) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("SELEZIONA IL PRIVILEGIO DEL CONSIGLIO DA OTTENERE");
        AskListToPay askListToPay = new AskListToPay(mainFrame);
        int choiceNumber = askListToPay.getInput(choiceListToPay.getCardId());
        notifyController(new VCListToPayChoice(getViewId(), choiceNumber));
    }

    @Override
    public void askLeaderEffectToCopy(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {

//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("INSERISCI L'ID DEL LEADER GIOCATO DA UN ALTRO GIOCATORE DA COPIARE");
        AskLeaderToCopy askLeaderToCopy = new AskLeaderToCopy(mainFrame);
        Integer choiceNumber = new Integer(askLeaderToCopy.getInput());
        notifyController(new VCLeaderToCopyChoice(getViewId(), choiceLeaderEffectToCopy.getLeaderCardId(), choiceNumber));
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
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("QUANTI SERVITORI VUOI PAGARE PER AUMENTARE IL VALORE DEL TUO FAMILY MEMBER?");
        AskServantsToPay askServantsToPay = new AskServantsToPay(mainFrame);
        notifyController(new VCServantToPayChoice(getViewId(), new Integer(askServantsToPay.getInput(getMyStateViewPlayer()))));
    }


    @Override
    public void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("SELEZIONA IL PRIVILEGIO DEL CONSIGLIO DA OTTENERE");
        AskPrivilegeToChoice askPrivilegeToChoice = new AskPrivilegeToChoice(mainFrame);
        int choiceNumber = askPrivilegeToChoice.getInput(choicePrivilegeResource.getResourceListToChoice());
        notifyController(new VCCouncilPrivilegeChoice(getViewId(), getResourceListFromCouncilPrivilege().get(choiceNumber)));
    }

    @Override
    public void askFamilyToChangeValue(ChoiceFamilyMemberToChangeValue choiceFamilyMemberToChangeValue) {
//        mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("A QUALE FAMILY VUOI AUMENTARE IL VALORE DI: "+choiceFamilyMemberToChangeValue.getNewValue());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjFamilyMemberPanel().getButtonsFamilyMemberPanel().setEnabledFamilyMember();
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
        if (!firstTime) {
            string = string.toUpperCase();
            mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString(string);
            mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getQuestionsToPlayerPanel().getAskActionPanel().setString("-------------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }

    @Override
    public void printLastState(String string) {

    }

    @Override
    public void printPlayerInAction() {
        this.printMyPhysicalResource();
        this.printMyPointResource();
        this.printDiceValue();
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
    public void printExcommunications() {
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getCouncilPanel().getExcommunicationPanel().printExcommunication(getStateViewGame().getStateViewExcommunicationList());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjExcommunication().fillExcomm(getMyStateViewPlayer().getStateViewExcommunicationList());
    }

    @Override
    public void printPersonalBoardInAction() {
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getCardYellow().setStateViewPersonalCardBoxList(getMyStateViewPersonalBoard().getStateViewPersonalCardBoxListYellow());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getCardYellow().fillDevelopmentCardPanel();
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getCardGreen().setStateViewPersonalCardBoxList(getMyStateViewPersonalBoard().getStateViewPersonalCardBoxListGreen());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getCardGreen().fillDevelopmentCardPanel();
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getExtraCardPanel().getPurpleCardPanel().setStateViewPersonalCardBoxList(getMyStateViewPersonalBoard().getStateViewPersonalCardBoxListPurple());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getExtraCardPanel().getPurpleCardPanel().fillDevelopmentCardPanel();
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getExtraCardPanel().getBlueCardPanel().setStateViewPersonalCardBoxList(getMyStateViewPersonalBoard().getStateViewPersonalCardBoxListBlue());
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getExtraCardPanel().getBlueCardPanel().fillDevelopmentCardPanel();

    }

    @Override
    public void printAllPersonalBoard() {
        int i=0;
        for (StateViewPersonalBoard stateViewPersonalBoard: super.getStateViewPersonalBoardList()
             ) {
            if(!super.getStateViewGame().getPlayerIdInAction().equals(stateViewPersonalBoard.getPlayerId())){
                mainFrame.getBackgroundMainFramePanel().getUtilityPanel().getOtherChosenPlayerPanel().getOtherPlayersPanel().fillOtherPlayers(stateViewPersonalBoard);
            }
        }
    }

    public void printMyPhysicalResource() {
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getPlayerResourcesPanel().setStringPhysicalResources(super.getMyStateViewPlayer().getPlayerResources());
    }

    public void printMyPointResource() {
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjPersonalBoardPanel().getPlayerResourcesPanel().setStringPointResources(super.getMyStateViewPlayer().getPlayerResources());

    }

    @Override
    public void printFamilyMemberInAction() {
        mainFrame.getBackgroundMainFramePanel().getPlayerPanel().getjFamilyMemberPanel().getButtonsFamilyMemberPanel().setString(super.getMyStateViewPlayer().getStateViewFamilyMemberList());

    }

    public void printDiceValue(){
        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().getDicesPanel().printDice(getMyStateViewPlayer().getStateViewFamilyMemberList());
    }

    @Override
    public void printBoardActionSpace() {

    }

    @Override
    public void printMyTiles() {
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
    public void printSingleBoardActionSpace(StateActionSpace actionSpace) {
        if (actionSpace.getNumberOfActionSpace() == 17) {
            mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getCouncilPanel().getActionSpaceCouncilPanel().printFamilyMemberOnBottomBoard(actionSpace.getStateFamilyMemberList());
        }
        if (actionSpace.getNumberOfActionSpace() >= 18 && actionSpace.getNumberOfActionSpace() <= 21) {
            for (int i = 0; i < 4; i++) {
                if(actionSpace.getNumberOfActionSpace()==(i+18)){
                    mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().getActionSpaceBoardButtons()[i].printFamilyMemberOnBottomBoard(actionSpace.getStateFamilyMemberList());

                }
            }
        }
        if (actionSpace.getNumberOfActionSpace() >= 22 && actionSpace.getNumberOfActionSpace() <= 25) {
            for (int i = 0; i < 4; i++) {
                if (actionSpace.getNumberOfActionSpace() == (i + 22)) {
                    mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().getMarketActionSpacePanel().getMarketActionSpace()[i].printFamilyMemberOnBottomBoard(actionSpace.getStateFamilyMemberList());
                }
            }
        }
    }

    @Override
    public void printSingleTowerActionSpace(StateActionSpace actionSpace) {
        if (actionSpace.getNumberOfActionSpace() >= 1 && actionSpace.getNumberOfActionSpace() <= 16) {
            int k=1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(k==actionSpace.getNumberOfActionSpace()) {
                        mainFrame.getBackgroundMainFramePanel().getGameBoardPanel().getTopBoardPanel().getTowerPanel().getTowerCardAndActionSpacePanels()[i][j].getSingleSmallActionSpacePanel().printFamilyMemberOnTopBoard(actionSpace.getStateFamilyMemberList());
                    }
                    k++;
                }
            }
        }
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