package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.choiceType.ChoiseActionToDo;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.StateGame;
import it.polimi.ingsw.ps31.model.stateModel.StatePlayerAction;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.PersonalBoard;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.*;

/**
 * Created by giulia on 19/06/2017.
 */
public class GameUtility extends ModelChoices {
    private List<Player> playerList;
    private GameBoard gameBoard;
    private VictoryPoint[] bonusVictoryPointFromTerritory;
    private VictoryPoint[] bonusVictoryPointFromCharacterCard;
    private VictoryPoint[] bonusVictoryPointFromMilitaryTrack;
    private VictoryPoint bonusVictoryPointFromPlayerResources;
    private DevelopmentCardList developmentCardList;
    private List<List<EffectList>>towerActionSpaceEffectList;
    private List<EffectList> actionSpaceEffectList;
    private VictoryPoint[] faithTrackExtraValue;
    private List<PointResource[]> personalBoardRequirements;
    private List<ResourceList> initialPlayerResource;
    private List<PersonalBonusTiles> personalBonusTilesList;
    private List<DevelopmentCardDeck> deckList = new ArrayList<>();
    private int playerMaxNumber;
    private long timerConnection= 120000;
    private long timerAction = 120000;


    public void createDeck(){
        //creazione deck vuoti
        CardColor[] cardColors = CardColor.values();
        for (int i = 0; i < cardColors.length; i++) {
            for (int period = 1; period <= 3; period++) {
                deckList.add(new DevelopmentCardDeck(cardColors[i], period));
            }
        }
        //riempimento dei deck in base al periodo e al colore delle carte

        for (int i = 0; i < developmentCardList.size(); i++) {
            for (int j = 0; j < deckList.size(); j++) {
                if (deckList.get(j).getCardListSize() < deckList.get(j).getMaxNumber()
                        && developmentCardList.get(i).getCardColor().equals(deckList.get(j).getColor())
                        && developmentCardList.get(i).getPeriod() == deckList.get(j).getPeriod()) {
                    deckList.get(j).setCard(developmentCardList.get(i));
                    break;
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

    public void extraPhaseActionGame(){
        for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
            if (playerList.get(playerMaxNumber).getFlagTurnExcommunication() == 1) {
                this.startActionTurn(playerList.get(playerMaxNumber));
                this.doActionTurn(playerList.get(playerMaxNumber));
                this.endActionTurn(playerList.get(playerMaxNumber));

            }
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

    public void vaticanReport(int period){
        for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
            // il giocatore non ha abbastanza punti fede e prende la scomunica (non viene chiesto niente al giocatore)
            if (playerList.get(playerNumber).getPlayerResources().getResourceValue(FaithPoint.class) < gameBoard.getFaithPointTrack().getTrackCell().get(2 + period).getValue()) {
                gameBoard.getExcommunicationTilesList().get(period).setExcommunicationToPlayer(playerList.get(playerMaxNumber));

                //regola dell'ultimo turno del terzo periodo (tutti ricevono i punti vittoria )
                if (period == 3) {
                    int faithPointPlayer = playerList.get(playerNumber).getPlayerResources().getResourceValue(FaithPoint.class);
                    playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                    playerList.get(playerNumber).subResources(playerList.get(playerNumber).getPlayerResources().getSpecificResource(FaithPoint.class));
                }
            } else {
                //chiedo l'intervento della view e una volta ricevuto il messaggio di risposta true (il giocatore vuole spendere i suoi punti fede per evitare la scomunica)
                int faithPointPlayer = playerList.get(playerNumber).getPlayerResources().getResourceValue(FaithPoint.class);
                playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                playerList.get(playerNumber).subResources(playerList.get(playerNumber).getPlayerResources().getSpecificResource(FaithPoint.class));
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

    public void createTimerConnection() {

        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {

                System.out.println("tempo scaduto per connetterti ");
                timer1.cancel();
            }
        };
        timer1.schedule(task1, timerConnection);
    }

    public void createTimerAction(){
        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {

                System.out.println("tempo scaduto per connetterti ");
                timer1.cancel();
            }
        };
        timer1.schedule(task1, timerAction);
    }

    public void startActionTurn(Player player) {
        String string1 = player.getPlayerId().toString()+": INIZIO FASE AZIONE";
        notifyViews(new MVStringToPrint(null,true,string1));
        String string2 = player.getPlayerId().toString()+": Aggiornato Stato Azioni";
        notifyViews(new MVUpdateState(string2,player.getStatePlayerAction()));
    }

    public void doActionTurn(Player player){
        this.createTimerAction();
        super.getLastModelStateForControl().setStateForControl(player.getStatePlayerAction());
        String string = player.getNickname()+": Scegli l'azione";
        notifyViews(new MVAskChoice(player.getPlayerId(),string, new ChoiseActionToDo()));
    }

    public void endActionTurn(Player player) {//TODO IMPLEMENTARLO

    }

    public Player createPlayer(String name, PlayerColor playerColor, List<ResourceList> listOfResourceList, List<PointResource[]>personalBoardRequirements, List<PersonalBonusTiles> personalBonusTilesList){
        //la view deve richiedere il nome e il colore che vuole essere
        //creazione familymembers in base al colore che il player ha scelto ,la personal board, la lista delle risorse iniziali infine il player

        PlayerId[] playerId = PlayerId.values();
        PersonalBoard personalBoard = new PersonalBoard(personalBoardRequirements, playerId[playerList.size()]);
        Player playerCreated = new Player(playerColor,listOfResourceList.get(playerList.size()), playerId[playerList.size()], name,personalBoard,personalBonusTilesList.get(playerList.size()));

        return playerCreated;
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
        }
    }

    public void setFamilyMemberDiceValue() {
        for (int i = 0; i < playerList.size(); i++) {
            for (int j = 0; j < playerList.get(i).getFamilyMembers().size(); j++) {
                playerList.get(i).getFamilyMembers().get(j).setDiceValue(gameBoard.getSpecificDice(playerList.get(i).getFamilyMembers().get(j).getDiceColor()).getValue());
            }
        }

    }

    public void resetFamilyMember() {
        for (int i = 0; i < playerList.size(); i++) {
            for (int j = 0; j < playerList.get(i).getFamilyMembers().size(); j++) {
                playerList.get(i).getFamilyMembers().get(j).resetFamilyMember();
            }
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

    public StateGame getStateGame(int period,int round,int playerNumber){
        StateGame stateGame = new StateGame(period,round,playerList.get(playerNumber).getPlayerId());
        return stateGame;
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

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

}