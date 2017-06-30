package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.stateView.*;
import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.messageMV.MVMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia in 01/06/2017.
 */
public abstract class View extends Observable implements Observer {
    private final PlayerId viewId;
    private final StateViewBoard stateViewBoard;
    private final List<StateViewPlayer> stateViewPlayerList=new ArrayList<>();
    private final List<StateViewPersonalBoard> stateViewPersonalBoardList= new ArrayList<>();
    private final StateViewGame stateViewGame;
    //private ClientNetworkInterface networkInterface;
    protected boolean firstTime=true; // se provo a stampare senza avere tutte le informazioni la prima volta da errore (per alcuni metodi di stampa)
                                    // la prima volta stampo solo se ho già tutto


    public View(PlayerId viewId, int playerMaxNumber) {
        this.viewId = viewId;
        this.stateViewBoard = new StateViewBoard();
        this.stateViewGame = new StateViewGame(playerMaxNumber);
        PlayerId[] playerId=PlayerId.values();
        for (int i=0; i<playerMaxNumber; i++){
            this.stateViewPersonalBoardList.add(new StateViewPersonalBoard(playerId[i]));
            this.stateViewPlayerList.add(new StateViewPlayer(playerId[i]));
        }
    }

    public void addController(Controller controller) {
        this.addObserver(controller);
    }

    public void addController(Observer observer)
    {
        this.addObserver(observer);
    }

    public void notifyController(VCVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }

//    public void sendMessage(VCVisitable message) {
//        networkInterface.sendToServer(message);
//    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("View:update> Update invocato");
        MVMessageVisitor mVMessageVisitor = new MVMessageVisitor();
        System.out.println("View:update> Debug1");
        mVMessageVisitor.setView(this);
        System.out.println("View:update> Debug2");
        MVVisitable message = (MVVisitable) arg;
        System.out.println("View:update> Messaggio ricevuto");
        if (message.isNotifyAll()) {          //se il messaggio riguarda tutti lo accetto
            System.out.println("View:update> Messaggio broadcast: accettato");
            message.accept(mVMessageVisitor);
        } else {
            if (message.getNotifySinglePlayer().equals(this.viewId)) {  //se il messaggio notifica solo un player controllo se è la mia la View di quel Player
                System.out.println("View:update> Messaggio indirizzato a me: accettato");
                message.accept(mVMessageVisitor);
            } else
                System.out.println("ClientMessageHistory:newMessage> Ricevuto nuovo messagio da bufferizzare e notificare");
        }

    }

//    public void setNetworkInterface(ClientNetworkInterface networkInterface)
//    {
//        this.networkInterface = networkInterface;
//    }

    public abstract void askActionSpace(ChoiceActionSpace choiceActionSpace);

    public abstract void AskTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace);

    public abstract void askActionToDo(ChoiceActionToDo choiceActionToDo);

    public abstract void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect);

    public abstract void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard);

    public abstract void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles);

    public abstract void askPlayerColor(ChoiceColor choiceColor);

    public abstract void askFamilyMember(ChoiceFamilyMember choiceFamilyMember);

    public abstract void askIfSupportChurch(ChoiceIfSupportTheChurch choiceIfSupportTheChurch);

    public abstract void askListToPay(ChoiceListToPay choiceListToPay);

    public abstract void askLeaderEffectToCopy(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy);

    public abstract void askLeaderToActive(ChoiceLeaderToActive choiceLeaderToActive);

    public abstract void askLeaderToDiscard(ChoiceLeaderToDiscard choiceLeaderToDiscard);

    public abstract void askServantToPay(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay);

    public abstract void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource);



    public abstract void askVisualizationCommand();


    public final void updateInfoPlayer(StatePlayer stateInfoPlayer) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateInfoPlayer.getPlayerId()))
                viewPlayer.updateState(stateInfoPlayer);
        }
        if(!firstTime)
            printAllPlayer();
    }

    public final void updatePlayerResources(StatePlayerResources statePlayerResources) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(statePlayerResources.getPlayerId()))
                viewPlayer.updateState(statePlayerResources);
        }
        if(!firstTime)
            printPlayerInAction();
    }

   public final void updateAllDevelopmentCard(StateAllDevelopmentCard stateAllDevelopmentCard){
       for (StateDevelopmentCard stateDevelopmentCard :stateAllDevelopmentCard.getStateDevelopmentCardList()
               ) {
           stateViewGame.updateState(stateDevelopmentCard);
       }

   }



    public final void updateAllFamilyMember(StateAllFamilyMember stateAllFamilyMember) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateAllFamilyMember.getIdFamilyMemberList()))
                viewPlayer.updateState(stateAllFamilyMember);
        }
    }

    public final void updateFamilyMember(StateFamilyMember stateFamilyMember) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateFamilyMember.getPlayerId()))
                viewPlayer.updateState(stateFamilyMember);
        }
        if(!firstTime)
          printFamilyMemberInAction();
    }

    public final void updatePersonalBonusTiles(StatePersonalBonusTiles statePersonalBonusTiles){
        for (StateViewPlayer viewPlayer: stateViewPlayerList
             ) {
            if(viewPlayer.getPlayerId().equals(statePersonalBonusTiles.getPlayerId()))
                viewPlayer.updateState(statePersonalBonusTiles);
        }
    }

    public final void updatePersonalBoard(StatePersonalBoard statePersonalBoard) {
        for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                ) {
            if (statePersonalBoard.getPlayerId().equals(viewPersonalBoard.getPlayerId()))
                viewPersonalBoard.updateState(statePersonalBoard);
        }
        if(!firstTime)
            printAllPersonalBoard();
    }

    public final void updateCardBox(StateCardBox stateCardBox) {

        if(stateCardBox.getValue()!=-1) {
            for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                    ) {
                if (viewPersonalBoard.getPlayerId().equals(stateCardBox.getPlayerId()))
                    viewPersonalBoard.updateState(stateCardBox);
            }
        }
        if(stateCardBox.getTowerFloor()!=-1){
            for (StateViewTower tower: getStateViewBoard().getStateViewTowerList()
                 ) {
                tower.updateState(stateCardBox);
//                for (StateViewTowerCardBox stateViewTowerCardBox: tower.getStateViewTowerCardBox()
//                     ) {
//                    if(stateCardBox.getTowerFloor()==stateViewTowerCardBox.getTowerFloor()){
//                        printTowerCardBox(stateViewTowerCardBox);
//                    }
//                }
            }
        }
        if(!firstTime)
        printPersonalBoardInAction();
    }

    public final void updateActionSpace(StateActionSpace stateActionSpace) {
        stateViewBoard.updateState(stateActionSpace);
        printBoardActionSpace();
    }

    public final void updateTower(StateTower stateTower) {
        stateViewBoard.updateState(stateTower);
        printTower();
    }

    public final void updateMarkerDisc(StateMarkerDisc stateMarkerDisc) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateMarkerDisc.getPlayerId())) {
                viewPlayer.updateState(stateMarkerDisc);
            }
        }
    }


    public final void updatePlayerAction(StatePlayerAction statePlayerAction) {
        for (StateViewPlayer viewPlayer : getStateViewPlayerList()
                ) {
            if (viewPlayer.getPlayerId().equals(statePlayerAction.getPlayerId()))
                viewPlayer.updateState(statePlayerAction);
        }
        if(firstTime) {
            setFirstTime(false);        //TODO PROBABILMENTE é DA SPOSTATE ( CMQ LE AZIONI PER ORA DEVONO ESSERE STAMPATE PER ULTIME)
            printTower();
//            printFamilyMemberInAction();
//            printAllPersonalBoard();
//            printPlayerInAction();
//            printAllPlayer();
//            printPlayerAction();
        }

        if (!firstTime) {
            printPlayerAction();
        }

    }

    public final void updateGame(StateGame stateGame) {
        stateViewGame.updateState(stateGame);
        if (!firstTime) {
            printAllPlayer();
            printAllPersonalBoard();
        }
    }
    public final void updateDevelopmentCard(StateDevelopmentCard stateDevelopmentCard) {
        stateViewGame.updateState(stateDevelopmentCard);
    }

    public final void updateLeaderCard(StateLeaderCard stateLeaderCard){
        for (StateViewPlayer stateViewPlayer:stateViewPlayerList
             ) {
           if(stateViewPlayer.getPlayerId().equals(stateLeaderCard.getPlayerId()))
               stateViewPlayer.updateState(stateLeaderCard);
        }
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public abstract void runTerminal();

    public abstract void printTower();

    public abstract void printTowerCardBox(StateViewTowerCardBox stateViewTowerCardBox);

    public abstract void printLastEvent(String string);

    public abstract void printPlayerInAction();

    public abstract void printAllPlayer();

    public abstract void printPlayerAction();

    public abstract void printPersonalBoardInAction();

    public abstract void printAllPersonalBoard();

    public abstract void printFamilyMemberInAction();

    public abstract void printBoardActionSpace();

    public abstract void printDevelopmentCard(int cardId);

    public abstract void printTextBox();

    public abstract void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView);

    public PlayerId getViewId() {
        return viewId;
    }

    public StateViewBoard getStateViewBoard() {
        return stateViewBoard;
    }

    public List<StateViewPlayer> getStateViewPlayerList() {
        return stateViewPlayerList;
    }

    public List<StateViewPersonalBoard> getStateViewPersonalBoardList() {
        return stateViewPersonalBoardList;
    }

    public StateViewGame getStateViewGame() {
        return stateViewGame;
    }

    public StateViewPlayer getMyStateViewPlayer() {
        for (StateViewPlayer viewPlayer : getStateViewPlayerList()
                ) {
            if (viewPlayer.getPlayerId().equals(this.getViewId())) {
                return viewPlayer;
            }
        }
        return null;
    }

    public StateViewPersonalBoard getMyStateViewPersonalBoard() {
        for (StateViewPersonalBoard boardView : getStateViewPersonalBoardList()
                ) {
            if (boardView.getPlayerId().equals(this.getViewId())) {
                return boardView;
            }
        }
        return null;
    }



}