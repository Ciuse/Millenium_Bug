package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.client.view.GuiView;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.PersonalBoard;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateGame;
import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBonusTiles;
import it.polimi.ingsw.ps31.model.stateModel.StateType;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;

import java.util.*;


/**
 * Created by giulia on 19/06/2017.
 */
public class GameUtility extends ModelChoices {
    private List<Player> playerList= new ArrayList<>();
    private Player playerInAction;
    private GameBoard gameBoard;
    private VictoryPoint[] bonusVictoryPointFromTerritory;
    private VictoryPoint[] bonusVictoryPointFromCharacterCard;
    private VictoryPoint[] bonusVictoryPointFromMilitaryTrack;
    private VictoryPoint bonusVictoryPointFromPlayerResources;
    private DevelopmentCardList developmentCardList;
    private List<LeaderCard> leaderCardList;
    private List<LeaderCard> tempLeaderCardList = new ArrayList<>();
    private List<List<EffectList>>towerActionSpaceEffectList;
    private List<EffectList> actionSpaceEffectList;
    private VictoryPoint[] faithTrackExtraValue;
    private List<PointResource[]> personalBoardRequirements;
    private List<ResourceList> initialPlayerResource;
    private List<PersonalBonusTiles> personalBonusTilesList;
    private List<DevelopmentCardDeck> deckList = new ArrayList<>();
    private int playerMaxNumber;
    private static final int Max_Leader_Card = 4;
    private long timerAction;

    public void createDeck(){
        //creazione deck vuoti
        CardColor[] cardColors = CardColor.values();
        for (CardColor cardColor : cardColors) {
            for (int period = 1; period <= 3; period++) {
                deckList.add(new DevelopmentCardDeck(cardColor, period));
            }
        }
        //riempimento dei deck in base al periodo e al colore delle carte

        for (int i = 0; i < developmentCardList.size(); i++) {
            for (DevelopmentCardDeck aDeckList : deckList) {
                if (aDeckList.getCardListSize() < aDeckList.getMaxNumber()
                        && developmentCardList.get(i).getCardColor().equals(aDeckList.getColor())
                        && developmentCardList.get(i).getPeriod() == aDeckList.getPeriod()) {
                    aDeckList.setCard(developmentCardList.get(i));
                    break;
                }
            }
        }

    }

    public void createViews() {
        for (Player player : playerList
                ) {
            for (int i = 0; i < super.getInformationFromNetworking().getPlayerNameList().size(); i++) {
                if (player.getNickname().equals(super.getInformationFromNetworking().getPlayerNameList().get(i))) {
                    if (super.getInformationFromNetworking().getViewChoiceList().get(i).equals(TypeOfView.CLI)) {
                        CmdLineView viewCliPlayer = new CmdLineView(player.getPlayerId(), playerMaxNumber);
                    }//else TODO creare la view gui
                }
            }
        }
    }

    public void phaseActionGame(int playerNumber,int action) {
        if (action == 1 && playerList.get(playerMaxNumber).getFlagTurnExcommunication() == 1) {
            this.endActionTurn(playerList.get(playerMaxNumber));
        }
        if (playerList.get(playerNumber).checkIfOnlyNEUTRALRemained() == true) {
            gameBoard.getEndActionButton().setActive(true);
        }
        //FASE AZIONE DEL GIOCATORE
        this.startActionTurn(playerList.get(playerMaxNumber));
        this.doActionTurn(playerList.get(playerMaxNumber));
        this.endActionTurn(playerList.get(playerMaxNumber));

    }

    public void extraPhaseActionGame() {
            for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                if (playerList.get(playerMaxNumber).getFlagTurnExcommunication() == 1) {
                    this.startActionTurn(playerList.get(playerMaxNumber));
                    this.doActionTurn(playerList.get(playerMaxNumber));
                    this.endActionTurn(playerList.get(playerMaxNumber));

                }
            }
        }



    public void updateStartAllPlayersInformation(){
        for (Player player:playerList
                ) {
            notifyViews(new MVUpdateState("Aggiornato lo stato di ogni player",player.getStateInfoPlayer()));
        }
    }

    public void updateStartAllPlayersResources(){
        for (Player player:playerList
                ) {
            notifyViews(new MVUpdateState("Aggiornato lo stato delle risorse di ogni player",player.getStatePlayerResources()));
        }
    }

    public void updateStartAllPlayersFamilyMember(){
        for (Player player:playerList
                ) {
            notifyViews(new MVUpdateState("Aggiornato lo stato di tutti i family member di un player",player.getStateAllFamilyMember()));
        }
    }

    public void updateStartAllPersonalBoard(){
        for (Player player:playerList
                ) {
            notifyViews(new MVUpdateState("Aggiornato lo stato di ogni personal board",player.getPersonalBoard().getStatePersonalBoard()));
        }
    }

    public void updateStartAllMarkerDisc(){
        for (Player player:playerList
                ) {
            for (MarkerDisc markerDisc:player.getMarkerDiscList()
                 ) {
                notifyViews(new MVUpdateState("Aggiornato lo stato di ogni marker disc di ogni giocatore",markerDisc.getStateMarkerDisc()));
            }

        }
    }

    public void updateStartAllDevelopmentCard(){
        for (DevelopmentCard developmentCard:developmentCardList.getDevelopmentCardList()
                ) {
                notifyViews(new MVUpdateState(null,developmentCard.getStateDevelopmentCard()));
            }
    }



    public void playerOrderFromCouncil(){
        //aggiungo alla lista dei colori del palazzo del consiglio gli eventuali giocatori che non si sono posizionati in questo spazio azione e poi riordino la lista giocatori
        List<PlayerColor> colorOrder = new ArrayList(gameBoard.getCouncilPalace().getColorOrder());
        for (int i = 0; i < playerList.size(); i++) {
            if (gameBoard.getCouncilPalace().checkIfPresentColor(playerList.get(i).getPlayerColor()) == false) {
                colorOrder.add(playerList.get(i).getPlayerColor());
            }
        }
        this.orderPlayersListWithColors(colorOrder);
    }

    public void vaticanReport(int period) {
        for (Player player : playerList
                ) {
            setPlayerInAction(player);

            // il giocatore non ha abbastanza punti fede e prende la scomunica (non viene chiesto niente al giocatore)
            if (player.getPlayerResources().getResourceValue(FaithPoint.class) < gameBoard.getFaithPointTrack().getTrackCell().get(2 + period).getValue()) {
                gameBoard.getExcommunicationTilesList().get(period).setExcommunicationToPlayer(playerList.get(playerMaxNumber));

                //regola dell'ultimo turno del terzo periodo (tutti ricevono i punti vittoria )
                if (period == 3) {
                    int faithPointPlayer = player.getPlayerResources().getResourceValue(FaithPoint.class);
                    player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                    player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                }
            } else {
                //mostro il sostegno alla chiesa
                //chiedo l'intervento della view e una volta ricevuto il messaggio di risposta true (il giocatore vuole spendere i suoi punti fede per evitare la scomunica)
                String string = player.getPlayerId() + ": vuoi spendere tutti i tuoi punti fede per evitare la scomunica?";
                notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceIfSupportTheChurch()));
                boolean supportTheChurch = super.waitSupportTheChurch();
                if (supportTheChurch) {
                    int faithPointPlayer = player.getPlayerResources().getResourceValue(FaithPoint.class);
                    player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                    player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                    for (LeaderCard leaderCard : player.getLeaderCardList()
                            ) {
                        if (leaderCard.getPermanentAbility() != null
                                && leaderCard.getPermanentAbility().getBonus() != null
                                && leaderCard.getPermanentAbility().getBonus().getExtraResourceOfVaticanReport() != null) {
                            player.addResources(leaderCard.getPermanentAbility().getBonus().getExtraResourceOfVaticanReport());
                        }
                    }
                } else {
                    //il giocatore ha deciso di non mostrare il suo sostegno alla chiesa
                    gameBoard.getExcommunicationTilesList().get(period).setExcommunicationToPlayer(playerList.get(playerMaxNumber));
                    if (period == 3) {
                        int faithPointPlayer = player.getPlayerResources().getResourceValue(FaithPoint.class);
                        player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                        player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                    }
                }
            }
        }
    }
    

    public void drawCardDeck(){
        for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
            gameBoard.getTowers().get(towerNum).drawCardFromDeck();
        }
    }

    public void setDeckTower(int period){
        for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
            gameBoard.getTowers().get(towerNum).setDeck(deckList, period);
        }
    }


    public void createTimerAction(){
        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {

                GameUtility.super.setStateEndTurn();
                endActionTurn(playerInAction);
                timer1.cancel();
            }
        };
        timer1.schedule(task1, timerAction);
    }

    public void startActionTurn(Player player) {
        resetPlayerAction();            //riattivo le azioni che i player hanno usato il turno prima
        setPlayerInAction(player);
        String string1 = player.getPlayerId().toString()+": INIZIO FASE AZIONE";
        notifyViews(new MVStringToPrint(null,true,string1));
        String string2 = player.getPlayerId().toString()+": Aggiornato Stato Azioni";
        notifyViews(new MVUpdateState(string2,player.getStatePlayerAction()));
    }

    public void doActionTurn(Player player) {
        super.setStateActionGame();
        while (super.getStateModelChoices().equals("StateActionGame")) {
            this.createTimerAction();
            super.getLastModelStateForControl().setStateForControl(player.getStatePlayerAction());
            String string = player.getNickname() + ": Scegli l'azione";
            Action actionToDo =super.waitActionToDo();
            for (Action action:player.getPlayerActionSet().getActionList()
                 ) {
                if(actionToDo.getClass().equals(action.getClass())){
                    super.setStateActionGame();
                    action.activate();
                }
                else{
                    String string1 = "Non ho trovato l azione da eseguire";
                    notifyViews(new MVStringToPrint(null,true,string1));
                }
            }
        }
    }

    public void endActionTurn(Player player) {//TODO IMPLEMENTARLO
        super.setStateEndTurn();
    }

    public void leaderCardSetup() {
        Collections.shuffle(leaderCardList);
        tempLeaderCardList=leaderCardList;
        for (int k = 0; k < Max_Leader_Card; k++) {
            List<LeaderCard> temp1 = new ArrayList<>();
            List<LeaderCard> temp2 = new ArrayList<>();
            List<LeaderCard> temp3 = new ArrayList<>();
            List<LeaderCard> temp4 = new ArrayList<>();
            List<List<LeaderCard>> listList = new ArrayList<>();
            listList.add(temp1);
            listList.add(temp2);
            listList.add(temp3);
            listList.add(temp4);
            int j = 0;
            for (Player player : playerList
                    ) {
                List<Integer> leaderCardId = new ArrayList<>();
                List<String> leaderCardString = new ArrayList<>();
                for (int i = 0; i < Max_Leader_Card - k; i++) {
                    listList.get(j).add(tempLeaderCardList.get(i));
                    leaderCardId.add(tempLeaderCardList.get(j).getLeaderId());
                    leaderCardString.add(tempLeaderCardList.get(j).getName());
                    j++;
                }
                TempModelStateForLeaderChoice tempModelStateForLeaderChoice = new TempModelStateForLeaderChoice();
                tempModelStateForLeaderChoice.addPlayerPossibleChoide(player.getPlayerId(), leaderCardId);
                String string = "SCEGLI CARTA LEADER: ";
                super.setTempModelStateForLeaderChoice(tempModelStateForLeaderChoice);
                super.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceStartLeaderCard(leaderCardId, leaderCardString)));
            }
            super.waitAllInitialLeaderCardChosen();
            tempLeaderCardList.clear();
            tempLeaderCardList.addAll(temp2);
            tempLeaderCardList.addAll(temp3);
            tempLeaderCardList.addAll(temp4);
            tempLeaderCardList.addAll(temp1);
        }
    }
     public void choiseColorPlayer(){
         List<PlayerColor> playerColorList = new ArrayList<>();
         playerColorList.add(PlayerColor.GREEN);
         playerColorList.add(PlayerColor.BLUE);
         playerColorList.add(PlayerColor.RED);
         playerColorList.add(PlayerColor.YELLOW);
         for (Player player:playerList
                 ) {
             String string = player.getPlayerId()+": scegli il tuo colore";
             notifyViews(new MVAskChoice(player.getPlayerId(),string,new ChoiceColor(playerColorList)));
             PlayerColor playerColor = super.waitPlayerColorChosen();
             int i=0;
                 for (PlayerColor color : playerColorList
                         ) {
                     if(color.equals(playerColor))
                         player.setPlayerColor(playerColorList.remove(i));

                     i++;
                 }
         }
     }

    public void createPlayer(String name){
        PlayerId[] playerId = PlayerId.values();
        PersonalBoard personalBoard = new PersonalBoard(personalBoardRequirements, playerId[playerList.size()]);
        playerList.add(new Player(initialPlayerResource.get(playerList.size()), playerId[playerList.size()], name,personalBoard));
    }

    public void phaseChoicePersonalBonusTiles(){
        for (Player player: playerList
             ) {
            String string = player.getPlayerId()+": Scegli il tuo personal bonus tiles";
            List<StatePersonalBonusTiles> statePersonalBonusTiles = new ArrayList<>();
            for (PersonalBonusTiles personalBonusTiles : personalBonusTilesList
                    ) {
                statePersonalBonusTiles.add(personalBonusTiles.getStatePersonalBonusTiles());
            }
            notifyViews(new MVAskChoice(player.getPlayerId(),string,new ChoicePersonalBonusTiles(statePersonalBonusTiles)));
            PersonalBonusTiles personalBonusTiles = super.waitPersonalBonusTilesChosen();
            personalBonusTiles.setPlayerId(player.getPlayerId());
            player.setPersonalBonusTiles(removePersonalBonusTiles(personalBonusTiles));
            notifyViews(new MVUpdateState("Aggiornato lo stato del personalBonusTiles",personalBonusTiles.getStatePersonalBonusTiles()));

        }
    }




    /*Metoci per Riordinare la Lista dei Player*/
    // riordina i giocatori in base all'ordine dei colori nel palazzo del concilio
    public void orderPlayersListWithColors(List<PlayerColor> colorList) {
        for (int i = 0; i < colorList.size(); i++) {
            for (int j = 0; j < playerList.size(); j++) { //il for con la j serve solo per fare 4 cicli massimi
                if (!colorList.get(i).equals(playerList.get(i).getPlayerColor())) {
                    Player removedPlayer = playerList.remove(i);   //il remove mi fa scalare automaticamente tutti i giocatori verso sinistra
                    playerList.add(removedPlayer);
                }
            }
        }
    }
    // riordino la lista dei giocatori in base a chi ha preso più punti militari
    public List<Player> orderMilitaryStrength() {
        Player[] arrayPlayerLists = playerList.toArray(new Player[playerList.size()]);
        for (int i = 0; i < arrayPlayerLists.length-1; i++) {
            for (int j = 0; j < arrayPlayerLists.length; j++) {
                if (arrayPlayerLists[i].getPlayerResources().getResourceValue(MilitaryStrength.class) <
                        arrayPlayerLists[i+1].getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                    Player tempPlayer = arrayPlayerLists[i+1];
                    arrayPlayerLists[i+1] = arrayPlayerLists[i];
                    arrayPlayerLists[i] = tempPlayer;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(arrayPlayerLists));
    }
    //riordino i giocatori in base ai punti vittoria finale ottenuti alla fine della partita
    public void orderVictoryPoint(){
        Player[] arrayPlayerLists = playerList.toArray(new Player[playerList.size()]);
        for (int i = 0; i < arrayPlayerLists.length-1; i++) {
            for (int j = 0; j < arrayPlayerLists.length; j++) {
                if (arrayPlayerLists[i].getPlayerResources().getResourceValue(VictoryPoint.class) <
                        arrayPlayerLists[i+1].getPlayerResources().getResourceValue(VictoryPoint.class)) {
                    Player tempPlayer = arrayPlayerLists[i+1];
                    arrayPlayerLists[i+1] = arrayPlayerLists[i];
                    arrayPlayerLists[i] = tempPlayer;
                }
            }
        }
        this.playerList = Arrays.asList(arrayPlayerLists);
    }


    /*Metodi per calcolare i punti vittoria alla fine del gioco */
    public void finalExtraVictoryPoints1(Player player) {
        if (player.getPlayerCardList().countCardGreen() > 1) {
            VictoryPoint victoryPointToAdd = bonusVictoryPointFromTerritory[player.getPlayerCardList().countCardGreen() - 1];
            player.addResources(victoryPointToAdd);
        }
    }
    public void finalExtraVictoryPoints2(Player player) {
        if (player.getPlayerCardList().countCardBlue() > 1) {
            VictoryPoint victoryPointToAdd = bonusVictoryPointFromCharacterCard[player.getPlayerCardList().countCardBlue() - 1];
            player.addResources(victoryPointToAdd);
        }
    }
    public void finalExtraVictoryPoints3(Player player) {
        player.activateFinalEffects();
    }
    public void finalExtraVictoryPoints4(Player player) {
        int value = player.getPlayerResources().getPlayerResourceList().getPhysicalResource();
        int factorValue = bonusVictoryPointFromPlayerResources.getValue();
        value = (value / 5) * factorValue;
        VictoryPoint victoryPointToAdd = new VictoryPoint(value);
        player.addResources(victoryPointToAdd);
    }

    public void getFinalVictoryPoint() {
        boolean thirdPeriodExcomunication = false;
        for (Player player : playerList
                ) {
            for (ExcommunicationTiles excommunicationTiles : player.getExcommunicationTiles()
                    ) {
                if (excommunicationTiles.isEndGame()==true) {            //se il giocatore ha una scomunica del terzo periodo allora dovrà sottrarre i punti vittoria diversamente
                    if (excommunicationTiles.getPermanentMalus().getCardColor() != null) {
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.GREEN)) {
                            finalExtraVictoryPoints2(player);
                            finalExtraVictoryPoints3(player);
                            thirdPeriodExcomunication = true;
                        }
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.PURPLE)) {
                            finalExtraVictoryPoints1(player);
                            finalExtraVictoryPoints2(player);
                            thirdPeriodExcomunication = true;
                        }
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.BLUE)) {
                            finalExtraVictoryPoints1(player);
                            finalExtraVictoryPoints3(player);
                            thirdPeriodExcomunication = true;
                        }
                    }
                    if (excommunicationTiles.getPermanentMalus().getPointResource() != null) {  // il giocatore ha una scomunica che sottrae punti vittoria in base ad altri punti
                        if (excommunicationTiles.getPermanentMalus().getPointResource().getClass().equals(VictoryPoint.class)) {
                            int value = player.getPlayerResources().getPlayerResourceList().getSpecificResource(VictoryPoint.class).getValue();
                            value = (value / 5) * excommunicationTiles.getPermanentMalus().getPointResource().getValue();
                            VictoryPoint victoryPointToSub = new VictoryPoint(value);
                            player.subResources(victoryPointToSub);
                        }
                        if (excommunicationTiles.getPermanentMalus().getPointResource().getClass().equals(MilitaryStrength.class)) {
                            int value = player.getPlayerResources().getPlayerResourceList().getSpecificResource(MilitaryStrength.class).getValue();
                            value = (value) * excommunicationTiles.getPermanentMalus().getPointResource().getValue();
                            VictoryPoint victoryPointToSub = new VictoryPoint(value);
                            player.subResources(victoryPointToSub);
                        }

                    }
                    if (excommunicationTiles.getPermanentMalus().getResourceList() != null) {// il giocatore ha una scomunica che in base ai costi di legno e pietra delle carte gialle
                        int costToPay = 0;
                        for (Resource resource:excommunicationTiles.getPermanentMalus().getResourceList().getResourceList()
                                ) {
                            for (DevelopmentCard developmentCard : player.getColorCardList(excommunicationTiles.getPermanentMalus().getCardColorForCostCard()).getDevelopmentCardList()
                                    ) {
                                for (ResourceList costList : developmentCard.getCostList()
                                        ) {
                                    if (costList.getSpecificResource(resource.getClass()) != null) {
                                        costToPay = costToPay + costList.getSpecificResource(resource.getClass()).getValue();
                                    }
                                }
                            }
                        }
                        VictoryPoint victoryPointToSub = new VictoryPoint(costToPay);
                        player.subResources(victoryPointToSub);
                    }
                    if (excommunicationTiles.getPermanentMalus().getString().equals("LostFinalVictoryPointFromPlayerResources")) {
                        int value = player.getPlayerResources().getPlayerResourceList().getPhysicalResource();
                        VictoryPoint victoryPointToSub = new VictoryPoint(value);
                        player.subResources(victoryPointToSub);

                    }
                }
            }
            if (thirdPeriodExcomunication == false) {
                finalExtraVictoryPoints1(player);
                finalExtraVictoryPoints2(player);
                finalExtraVictoryPoints3(player);
            }
            finalExtraVictoryPoints4(player);

            super.notifyViews(new MVUpdateState("Aggiornato lo stato delle risorse finali",player.getStatePlayerResources()));
        }
    }

    public void setFamilyMemberDiceValue() {
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getFamilyMembers().size(); j++) {
                aPlayerList.getFamilyMembers().get(j).setDiceValue(gameBoard.getSpecificDice(aPlayerList.getFamilyMembers().get(j).getDiceColor()).getValue());
            }
        }

    }

    public void resetFamilyMember() {
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getFamilyMembers().size(); j++) {
                aPlayerList.getFamilyMembers().get(j).resetFamilyMember();
            }
        }
    }

    public void resetLeaderEffect(){
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getLeaderCardList().size(); j++) {
                aPlayerList.getLeaderCardList().get(j).setUsedEffect1(false);
            }
        }
    }

    public void resetPlayerAction(){
        for (Player player: playerList
             ) {
            player.getPlayerActionSet().resetUsedAction();
        }
    }
    public void militaryTrackWinnerPoint() {
        List<Player> tempPlayerList = new ArrayList<>(orderMilitaryStrength());
        boolean paritàTrovata = false;
        int contatore=0;
        for(int i=0;i<tempPlayerList.size();i++) {
            if (tempPlayerList.get(0).getPlayerResources().getResourceValue(MilitaryStrength.class)
                    == tempPlayerList.get(i).getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                contatore++;
                tempPlayerList.get(i).getPlayerResources().addResources(bonusVictoryPointFromMilitaryTrack[0]);
            }
        }
        if(contatore>1){
            paritàTrovata=true;
        }
        int contatore2 =0;
        for(int j=1;j<tempPlayerList.size();j++){
            if (paritàTrovata==false
                    && tempPlayerList.get(1).getPlayerResources().getResourceValue(MilitaryStrength.class)
                    == tempPlayerList.get(j).getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                contatore2++;
                tempPlayerList.get(j).getPlayerResources().addResources(bonusVictoryPointFromMilitaryTrack[1]);
            }
        }
        if (contatore2>1){
            paritàTrovata = true;
        }
    }

    public StateGame getStateGame(int period, int round, int playerNumber){
        StateGame stateGame = new StateGame(period,round,playerList.get(playerNumber).getPlayerId());
        return stateGame;
    }


    public void setInformationFromNetworking(InformationFromNetworking informationFromNetworking) {
        super.setInformationFromNetworking(informationFromNetworking);
    }

    public List<DevelopmentCardDeck> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<DevelopmentCardDeck> deckList) {
        this.deckList = deckList;
    }

    public int getPlayerMaxNumber() {
        return playerMaxNumber;
    }

    public void setPlayerMaxNumber(int playerMaxNumber) {
        this.playerMaxNumber = playerMaxNumber;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public VictoryPoint[] getBonusVictoryPointFromTerritory() {
        return bonusVictoryPointFromTerritory;
    }

    public VictoryPoint[] getBonusVictoryPointFromCharacterCard() {
        return bonusVictoryPointFromCharacterCard;
    }

    public VictoryPoint[] getBonusVictoryPointFromMilitaryTrack() {
        return bonusVictoryPointFromMilitaryTrack;
    }

    public VictoryPoint getBonusVictoryPointFromPlayerResources() {
        return bonusVictoryPointFromPlayerResources;
    }

    public DevelopmentCardList getDevelopmentCardList() {
        return developmentCardList;
    }

    public List<List<EffectList>> getTowerActionSpaceEffectList() {
        return towerActionSpaceEffectList;
    }

    public List<EffectList> getActionSpaceEffectList() {
        return actionSpaceEffectList;
    }

    public VictoryPoint[] getFaithTrackExtraValue() {
        return faithTrackExtraValue;
    }

    public List<PointResource[]> getPersonalBoardRequirements() {
        return personalBoardRequirements;
    }

    public List<ResourceList> getInitialPlayerResource() {
        return initialPlayerResource;
    }

    public List<PersonalBonusTiles> getPersonalBonusTilesList() {
        return personalBonusTilesList;
    }

    public void setBonusVictoryPointFromTerritory(VictoryPoint[] bonusVictoryPointFromTerritory) {
        this.bonusVictoryPointFromTerritory = bonusVictoryPointFromTerritory;
    }

    public void setBonusVictoryPointFromCharacterCard(VictoryPoint[] bonusVictoryPointFromCharacterCard) {
        this.bonusVictoryPointFromCharacterCard = bonusVictoryPointFromCharacterCard;
    }

    public void setBonusVictoryPointFromMilitaryTrack(VictoryPoint[] bonusVictoryPointFromMilitaryTrack) {
        this.bonusVictoryPointFromMilitaryTrack = bonusVictoryPointFromMilitaryTrack;
    }

    public void setBonusVictoryPointFromPlayerResources(VictoryPoint bonusVictoryPointFromPlayerResources) {
        this.bonusVictoryPointFromPlayerResources = bonusVictoryPointFromPlayerResources;
    }

    public void setDevelopmentCardList(DevelopmentCardList developmentCardList) {
        this.developmentCardList = developmentCardList;
    }

    public void setTowerActionSpaceEffectList(List<List<EffectList>> towerActionSpaceEffectList) {
        this.towerActionSpaceEffectList = towerActionSpaceEffectList;
    }

    public void setActionSpaceEffectList(List<EffectList> actionSpaceEffectList) {
        this.actionSpaceEffectList = actionSpaceEffectList;
    }

    public void setFaithTrackExtraValue(VictoryPoint[] faithTrackExtraValue) {
        this.faithTrackExtraValue = faithTrackExtraValue;
    }

    public void setPersonalBoardRequirements(List<PointResource[]> personalBoardRequirements) {
        this.personalBoardRequirements = personalBoardRequirements;
    }

    public void setInitialPlayerResource(List<ResourceList> initialPlayerResource) {
        this.initialPlayerResource = initialPlayerResource;
    }

    public void setPersonalBonusTilesList(List<PersonalBonusTiles> personalBonusTilesList) {
        this.personalBonusTilesList = personalBonusTilesList;
    }

    public Player getPlayerInAction() {
        return playerInAction;
    }

    public void setPlayerInAction(Player playerInAction) {
        this.playerInAction = playerInAction;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        if(playerMaxNumber>=3){
            this.gameBoard.add3PlayerActionSpace(actionSpaceEffectList);
        }
        if(playerMaxNumber==4)
        {
            this.gameBoard.add4PlayerMarketSpace(actionSpaceEffectList);
        }
    }


    public void setTimerConnection(long timerConnection) {
        super.setTimerConnection(timerConnection);
    }
    public InformationFromNetworking getInformationFromNetworking() {
        return super.getInformationFromNetworking();
    }
    public long getTimerAction() {
        return timerAction;
    }

    public void setTimerAction(long timerAction) {
        this.timerAction = timerAction;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<LeaderCard> getLeaderCardList() {
        return leaderCardList;
    }

    public void setLeaderCardList(List<LeaderCard> leaderCardList) {
        this.leaderCardList = leaderCardList;
    }

    public List<LeaderCard> getTempLeaderCardList() {
        return tempLeaderCardList;
    }

    public void setTempLeaderCardList(List<LeaderCard> tempLeaderCardList) {
        this.tempLeaderCardList = tempLeaderCardList;
    }

    public PersonalBonusTiles removePersonalBonusTiles(PersonalBonusTiles personalBonusTiles){
        int i=0;
        for (PersonalBonusTiles tiles:this.personalBonusTilesList
             ) {
            if(tiles.equals(personalBonusTiles))
            return this.personalBonusTilesList.remove(i);

            i++;
        }
        return null;
    }



}
