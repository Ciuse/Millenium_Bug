package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.Model;
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
import it.polimi.ingsw.ps31.model.json.JsonGameObject;

import java.util.*;


/**
 * Created by giulia on 19/06/2017.
 *
 * Classe di supporto a Game Logic contiene:
 * I riferimenti agli oggetti letti da Json
 * Metodi per far fare le scelte iniziali ai giocatori
 * Metodi per gestire le varie fasi di un azione
 * Metodi per la creazione o il settaggio di alcuni elementi di gioco come deck o scomuniche
 * Metodi per riordinare i player
 * La gestione del timer delle fasi azioni
 *
 * @see Player
 * @see GameBoard
 * @see JsonGameObject
 * @see Model
 * @see Timer
 *
 */
public class GameUtility {
    private List<Player> playerList = new ArrayList<>();
    private Player playerInAction;
    private GameBoard gameBoard;
    private VictoryPoint[] bonusVictoryPointFromTerritory;
    private VictoryPoint[] bonusVictoryPointFromCharacterCard;
    private VictoryPoint[] bonusVictoryPointFromMilitaryTrack;
    private VictoryPoint bonusVictoryPointFromPlayerResources;
    private DevelopmentCardList developmentCardList;
    private List<ExcommunicationTiles> excommunicationTilesList;
    private List<LeaderCard> leaderCardList;
    private List<List<EffectList>> towerActionSpaceEffectList;
    private List<EffectList> actionSpaceEffectList;
    private VictoryPoint[] faithTrackExtraValue;
    private List<PointResource[]> personalBoardRequirements;
    private List<ResourceList> initialPlayerResource;
    private List<PersonalBonusTiles> personalBonusTilesList;
    private List<ResourceList> councilPrivilegeResChoice;
    private List<DevelopmentCardDeck> deckList = new ArrayList<>();
    private int playerMaxNumber;
    private static final int Max_Leader_Card = 4;
    private long timerAction;
    private Timer timer;
    private TimerTask timerTask;
    private Model model;

    public GameUtility() {
    }


    /*metodi che riguardano il funzionamento delle principali fasi del gioco e della loro logica interna */

    public void choiseColorPlayer() {
        List<PlayerColor> playerColorList = new ArrayList<>();
        playerColorList.add(PlayerColor.GREEN);
        playerColorList.add(PlayerColor.BLUE);
        playerColorList.add(PlayerColor.RED);
        playerColorList.add(PlayerColor.YELLOW);
        for (Player player : playerList
                ) {
            setPlayerInAction(player);
            String string = player.getPlayerId() + ": scegli il tuo colore";
            model.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceColor(playerColorList)));
            PlayerColor playerColor = model.getModelChoices().waitPlayerColorChosen();
            int i = 0;
            PlayerColor found = null;
            for (PlayerColor color : playerColorList
                    ) {
                if (color.equals(playerColor)) {
                    player.setPlayerColor(color);
                    found = color;
                }
                i++;
            }
            playerColorList.remove(found);
        }
    }

    public void phaseChoicePersonalBonusTiles() {
        for (Player player : playerList
                ) {
            setPlayerInAction(player);
            String string = player.getPlayerId() + ": Scegli il tuo personal bonus tiles";
            List<StatePersonalBonusTiles> statePersonalBonusTiles = new ArrayList<>();
            for (PersonalBonusTiles personalBonusTiles : personalBonusTilesList
                    ) {
                statePersonalBonusTiles.add(personalBonusTiles.getStatePersonalBonusTiles());
            }
            model.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoicePersonalBonusTiles(statePersonalBonusTiles)));
            PersonalBonusTiles personalBonusTiles = model.getModelChoices().waitPersonalBonusTilesChosen();
            personalBonusTiles.setPlayerId(player.getPlayerId());
            player.setPersonalBonusTiles(removePersonalBonusTiles(personalBonusTiles));
            model.notifyViews(new MVUpdateState("Aggiornato lo stato del personalBonusTiles", personalBonusTiles.getStatePersonalBonusTiles()));
        }
    }

    public void leaderCardSetup() {
        Collections.shuffle(leaderCardList);
        for (int k = 0; k < Max_Leader_Card; k++) {
            List<List<LeaderCard>> listList = model.getModelChoices().getTempModelStateForLeaderChoice().getListList();
            int j = 0;
            for (Player player : playerList
                    ) {
                if (k == 0) {
                    listList.add(new ArrayList<>());
                }
                List<Integer> leaderCardId = new ArrayList<>();
                List<String> leaderCardString = new ArrayList<>();
                for (int i = 0; i < Max_Leader_Card - k; i++) {
                    if (k == 0) {
                        listList.get(j).add(leaderCardList.get((j * 4) + i));
                    }
                    leaderCardId.add(model.getModelChoices().getTempModelStateForLeaderChoice().getListList().get(j).get(i).getLeaderId());
                    leaderCardString.add(model.getModelChoices().getTempModelStateForLeaderChoice().getListList().get(j).get(i).getName());
                }
                model.getModelChoices().getTempModelStateForLeaderChoice().addPlayerPossibleChoise(player.getPlayerId(), leaderCardId);
                String string = "SCEGLI CARTA LEADER: ";
                model.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceStartLeaderCard(leaderCardId, leaderCardString)));
                j++;
            }
            model.getModelChoices().waitAllInitialLeaderCardChosen(playerMaxNumber);


            //prendo l ultima l ista e la sposto in testa per simulare il fatto che ogni player passa le sue carte al player alla sua sinistra

            List<LeaderCard> listToMove = model.getModelChoices().getTempModelStateForLeaderChoice().getListList().get(model.getModelChoices().getTempModelStateForLeaderChoice().getListList().size() - 1);
            model.getModelChoices().getTempModelStateForLeaderChoice().getListList().remove(listToMove);
            model.getModelChoices().getTempModelStateForLeaderChoice().getListList().add(0, listToMove);


        }
        for (Player player : playerList
                ) {
            for (LeaderCard leader : player.getLeaderCardList()
                    ) {
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato leader card", leader.getStateLeaderCard()));
            }

        }
    }

    public void phaseActionGame(int playerNumber, int action) {
        if (action == 1 && playerList.get(playerNumber).getFlagTurnExcommunication() == 1) {
            this.endActionTurn(playerList.get(playerNumber));
        }
        if (playerList.get(playerNumber).checkIfOnlyNEUTRALRemained()) {
            playerList.get(playerNumber).getPlayerActionSet().getActiveEndButton().setActive(true);
        }
        this.startActionTurn(playerList.get(playerNumber));
        this.doActionTurn(playerList.get(playerNumber));
        this.endActionTurn(playerList.get(playerNumber));
    }

    public void extraPhaseActionGame(int playerNumber) {
        if (playerList.get(playerNumber).getFlagTurnExcommunication() == 1) {
            if (playerList.get(playerNumber).checkIfOnlyNEUTRALRemained()) {
                playerList.get(playerNumber).getPlayerActionSet().getActiveEndButton().setActive(true);
            }
            this.startActionTurn(playerList.get(playerNumber));
            this.doActionTurn(playerList.get(playerNumber));
            this.endActionTurn(playerList.get(playerNumber));

        }
    }

    public void startActionTurn(Player player) {
        resetPlayerAction();            //riattivo le azioni che i player hanno usato il turno prima
        setPlayerInAction(player);
        player.setWannaEndTurn(false);
        String string1 = player.getNickname() + ": INIZIO FASE AZIONE";
        model.notifyViews(new MVStringToPrint(null, true, string1));
        String string2 = player.getPlayerId().toString() + ": Aggiornato Stato Azioni";
        model.notifyViews(new MVUpdateState(string2, player.getStatePlayerAction()));
    }

    public void doActionTurn(Player player) {
        model.getModelChoices().setStateActionGame();
        while (model.getModelChoices().getStateModelChoices().equals("StateActionGame") && !player.isWannaEndTurn()) {
            cancelTimer();
            createTimerAction();
            model.getModelChoices().getLastModelStateForControl().setStateForControl(player.getStatePlayerAction());
            String string = player.getNickname() + ": Qaule azione tra quelle che hai disponibili vuoi eseguire?";
            model.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceActionToDo()));
            Action actionToDo = model.getModelChoices().waitActionToDo();
            if (model.getModelChoices().getStateModelChoices().equals("StateActionGame")) {
                for (Action action : player.getPlayerActionSet().getActionList()
                        ) {
                    if (actionToDo != null) {
                        if (actionToDo.getClass().equals(action.getClass())) {
                            action.activate();
                            player.addTempResoucesToPlayerResources();

                            String string2 = player.getPlayerId().toString() + ": Aggiornato Stato Azioni";
                            model.notifyViews(new MVUpdateState(string2, player.getStatePlayerAction()));
                            model.getModelChoices().setStateActionGame();
                        }
                    } else {
                        String string1 = "Non ho trovato l azione da eseguire";
                        model.notifyViews(new MVStringToPrint(null, true, string1));
                    }
                }
            }
        }
        cancelTimer();
    }

    public void endActionTurn(Player player) {//TODO IMPLEMENTARLO

        String string1 = player.getNickname() + ": FINE FASE AZIONE";
        model.notifyViews(new MVStringToPrint(null, true, string1));
        model.getModelChoices().setStateEndTurn();
    }

    public void vaticanReport(int period) {
        for (Player player : playerList
                ) {
            setPlayerInAction(player);

            // il giocatore non ha abbastanza punti fede e prende la scomunica (non viene chiesto niente al giocatore)
            if (player.getPlayerResources().getResourceValue(FaithPoint.class) < gameBoard.getFaithPointTrack().getTrackCell().get(2 + period).getValue()) {
                for (ExcommunicationTiles tiles : gameBoard.getExcommunicationTilesList()
                        ) {
                    if (tiles.getPeriod() == period) {
                        tiles.setExcommunicationToPlayer(player);
                    }
                }

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
                model.notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceIfSupportTheChurch()));
                Boolean supportTheChurch = model.getModelChoices().waitSupportTheChurch();
                if (supportTheChurch == null) {
                    supportTheChurch = false;
                }
                if (supportTheChurch) {
                    int faithPointPlayer = player.getPlayerResources().getResourceValue(FaithPoint.class);
                    if (faithPointPlayer >= 15) {
                        player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(14).getExtraValue());
                        player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                    } else {
                        player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                        player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                    }
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
                    for (ExcommunicationTiles tiles : gameBoard.getExcommunicationTilesList()
                            ) {
                        if (tiles.getPeriod() == period) {
                            tiles.setExcommunicationToPlayer(player);
                        }
                    }
                    if (period == 3) {
                        int faithPointPlayer = player.getPlayerResources().getResourceValue(FaithPoint.class);
                        player.addResources(gameBoard.getFaithPointTrack().getTrackCell().get(faithPointPlayer).getExtraValue());
                        player.subResources(player.getPlayerResources().getSpecificResource(FaithPoint.class));
                    }
                }
            }
            model.notifyViews(new MVUpdateState("Aggiornato lo stato player resources", player.getStatePlayerResources()));
        }

    }


    /* metodi di aggiornamento degli stati della view */

    public void updateStartAllPlayersInformation() {
        for (Player player : playerList
                ) {
            model.notifyViews(new MVUpdateState("Aggiornato lo stato del player", player.getStateInfoPlayer()));
        }
    }

    public void updateStartAllPlayersResources() {
        for (Player player : playerList
                ) {
            model.notifyViews(new MVUpdateState("Aggiornato lo stato delle player resources", player.getStatePlayerResources()));
        }
    }

    public void updateStartAllPlayersFamilyMember() {
        for (Player player : playerList
                ) {
            model.notifyViews(new MVUpdateState("Aggiornato lo stato di tutti i family member di un player", player.getStateAllFamilyMember()));
        }
    }

    public void updateStartAllPersonalBoard() {
        for (Player player : playerList
                ) {
            model.notifyViews(new MVUpdateState("Aggiornato lo stato della personal board del player", player.getPersonalBoard().getStatePersonalBoard()));
        }
    }

    public void updateStartAllMarkerDisc() {
        for (Player player : playerList
                ) {
            for (MarkerDisc markerDisc : player.getMarkerDiscList()
                    ) {
                model.notifyViews(new MVUpdateState("Aggiornato lo stato di ogni marker disc del player", markerDisc.getStateMarkerDisc()));
            }

        }
    }

    public void updateStartAllDevelopmentCard() {
        model.notifyViews(new MVUpdateState(null, developmentCardList.getStateAllCard()));
    }


    /*metodi di supporto che riguardano elementi fisici del gioco */

    public void createDeck() {
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

    public void drawCardDeck() {
        for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
            gameBoard.getTowers().get(towerNum).drawCardFromDeck();
        }
    }

    public void setDeckTower(int period) {
        for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
            gameBoard.getTowers().get(towerNum).setDeck(deckList, period);
        }
    }

    public void setMarkerDisc() {
        for (Player player : playerList
                ) {
            player.getSpecificMarkerDisc(FaithPoint.class).setTrackCell(gameBoard.getFaithPointTrack().getTrackCell().get(0));
            player.getSpecificMarkerDisc(MilitaryStrength.class).setTrackCell(gameBoard.getMilitaryPointTrack().getTrackCell().get(0));
            player.getSpecificMarkerDisc(VictoryPoint.class).setTrackCell(gameBoard.getVictoryPointTrack().getTrackCell().get(0));
        }
    }

    public void setExcommunicationMatchTiles() {
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        List<ExcommunicationTiles> excommToUse = new ArrayList<>();
        Collections.shuffle(excommunicationTilesList);
        for (ExcommunicationTiles excom : excommunicationTilesList
                ) {
            if (!found1) {
                if (excom.getPeriod() == 1) {
                    found1 = true;
                    excommToUse.add(excom);
                    model.notifyViews(new MVUpdateState("Aggiornato stato scomuniche", excom.getStateExcommunication()));
                }
            }
        }

        for (ExcommunicationTiles excom : excommunicationTilesList
                ) {
            if (!found2) {
                if (excom.getPeriod() == 2) {
                    found2 = true;
                    excommToUse.add(excom);
                    model.notifyViews(new MVUpdateState("Aggiornato stato scomuniche", excom.getStateExcommunication()));
                }
            }
        }
        for (ExcommunicationTiles excom : excommunicationTilesList
                ) {
            if (!found3) {
                if (excom.getPeriod() == 3) {
                    found3 = true;
                    excommToUse.add(excom);
                    model.notifyViews(new MVUpdateState("Aggiornato stato scomuniche", excom.getStateExcommunication()));
                }
            }
        }
        gameBoard.setExcommunicationTilesList(excommToUse);

    }

    public void setFamilyMemberDiceValue() {
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getFamilyMembers().size(); j++) {
                aPlayerList.getFamilyMembers().get(j).setDiceValue(gameBoard.getSpecificDice(aPlayerList.getFamilyMembers().get(j).getDiceColor()).getValue());
            }
        }
        updateStartAllPlayersFamilyMember();
    }

    public void resetFamilyMember() {
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getFamilyMembers().size(); j++) {
                aPlayerList.getFamilyMembers().get(j).resetFamilyMember();
            }
        }
    }

    public void resetLeaderEffect() {
        for (Player aPlayerList : playerList) {
            for (int j = 0; j < aPlayerList.getLeaderCardList().size(); j++) {
                aPlayerList.getLeaderCardList().get(j).setUsedEffect1(false);
            }
        }
    }

    public void resetPlayerAction() {
        for (Player player : playerList
                ) {
            player.getPlayerActionSet().resetUsedAction();
        }
    }




    /*metodi di creazione oggetti vari */

    public void createPlayer(String name) {
        PlayerId[] playerId = PlayerId.values();
        PersonalBoard personalBoard = new PersonalBoard(personalBoardRequirements, playerId[playerList.size()], model);
        playerList.add(new Player(model, playerId[playerList.size()], initialPlayerResource.get(playerList.size()), name, personalBoard));
    }

    public void createTimerAction() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {

                model.getModelChoices().setStateEndTurn();
                endActionTurn(playerInAction);
                timer.cancel();
            }
        };
        timer.schedule(timerTask, timerAction);
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }


    /*Metoci per Riordinare la Lista dei Player*/

    public void playerOrderFromCouncil() { // riordina i giocatori in base all'ordine dei colori nel palazzo del concilio

        //aggiungo alla lista dei colori del palazzo del consiglio gli eventuali giocatori che non si sono posizionati in questo spazio azione e poi riordino la lista giocatori
        List<PlayerColor> colorOrder = new ArrayList(gameBoard.getCouncilPalace().getColorOrder());
        for (Player aPlayerList : playerList) {
            if (!gameBoard.getCouncilPalace().checkIfPresentColor(aPlayerList.getPlayerColor())) {
                colorOrder.add(aPlayerList.getPlayerColor());
            }
        }
        this.orderPlayersListWithColors(colorOrder);
    }

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

    public List<Player> orderMilitaryStrength() {     // riordino la lista dei giocatori in base a chi ha preso più punti militari

        Player[] arrayPlayerLists = playerList.toArray(new Player[playerList.size()]);
        for (int i = 0; i < arrayPlayerLists.length - 1; i++) {
            for (int j = 0; j < arrayPlayerLists.length; j++) {
                if (arrayPlayerLists[i].getPlayerResources().getResourceValue(MilitaryStrength.class) <
                        arrayPlayerLists[i + 1].getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                    Player tempPlayer = arrayPlayerLists[i + 1];
                    arrayPlayerLists[i + 1] = arrayPlayerLists[i];
                    arrayPlayerLists[i] = tempPlayer;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(arrayPlayerLists));
    }

    public void orderVictoryPoint() {     //riordino i giocatori in base ai punti vittoria finale ottenuti alla fine della partita

        Player[] arrayPlayerLists = playerList.toArray(new Player[playerList.size()]);
        for (int i = 0; i < arrayPlayerLists.length - 1; i++) {
            for (int j = 0; j < arrayPlayerLists.length; j++) {
                if (arrayPlayerLists[i].getPlayerResources().getResourceValue(VictoryPoint.class) <
                        arrayPlayerLists[i + 1].getPlayerResources().getResourceValue(VictoryPoint.class)) {
                    Player tempPlayer = arrayPlayerLists[i + 1];
                    arrayPlayerLists[i + 1] = arrayPlayerLists[i];
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
        int value = player.getPlayerResources().getPhysicalResource();
        int factorValue = bonusVictoryPointFromPlayerResources.getValue();
        value = (value / 5) * factorValue;
        VictoryPoint victoryPointToAdd = new VictoryPoint(value);
        player.addResources(victoryPointToAdd);
    }

    public void militaryTrackWinnerPoint() {
        List<Player> tempPlayerList = new ArrayList<>(orderMilitaryStrength());
        boolean paritàTrovata = false;
        int contatore = 0;
        for (int i = 0; i < tempPlayerList.size(); i++) {
            if (tempPlayerList.get(0).getPlayerResources().getResourceValue(MilitaryStrength.class)
                    == tempPlayerList.get(i).getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                contatore++;
                tempPlayerList.get(i).getPlayerResources().addSpecificResource(bonusVictoryPointFromMilitaryTrack[0]);
            }
        }
        if (contatore > 1) {
            paritàTrovata = true;
        }
        int contatore2 = 0;
        for (int j = 1; j < tempPlayerList.size(); j++) {
            if (paritàTrovata == false
                    && tempPlayerList.get(1).getPlayerResources().getResourceValue(MilitaryStrength.class)
                    == tempPlayerList.get(j).getPlayerResources().getResourceValue(MilitaryStrength.class)) {
                contatore2++;
                tempPlayerList.get(j).getPlayerResources().addSpecificResource(bonusVictoryPointFromMilitaryTrack[1]);
            }
        }
        if (contatore2 > 1) {
            paritàTrovata = true;
        }
    }

    /* metodo principale per attribuire i punti alla fine del gioco in base alle scomuniche possedute dai player */

    public void getFinalVictoryPoint() {
        boolean thirdPeriodExcomunication = false;
        for (Player player : playerList
                ) {
            for (ExcommunicationTiles excommunicationTiles : player.getExcommunicationTiles()
                    ) {
                if (excommunicationTiles.isEndGame()) {            //se il giocatore ha una scomunica del terzo periodo allora dovrà sottrarre i punti vittoria diversamente
                    if (excommunicationTiles.getPermanentMalus().getCardColor() != null) {
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.GREEN)) {
                            if (!player.getPersonalBoard()
                                    .getPlayerCardList()
                                    .getDevelopmentCardList()
                                    .isEmpty()) {
                                finalExtraVictoryPoints2(player);
                                finalExtraVictoryPoints3(player);
                                thirdPeriodExcomunication = true;
                            }
                        }
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.PURPLE)) {
                            if (!player.getPersonalBoard().getPlayerCardList().getDevelopmentCardList().isEmpty()) {
                                finalExtraVictoryPoints1(player);
                                finalExtraVictoryPoints2(player);
                                thirdPeriodExcomunication = true;
                            }
                        }
                        if (excommunicationTiles.getPermanentMalus().getCardColor().equals(CardColor.BLUE)) {
                            if (!player.getPersonalBoard().getPlayerCardList().getDevelopmentCardList().isEmpty()) {
                                finalExtraVictoryPoints1(player);
                                finalExtraVictoryPoints3(player);
                                thirdPeriodExcomunication = true;
                            }
                        }
                    }
                    if (excommunicationTiles.getPermanentMalus().getPointResource() != null) {  // il giocatore ha una scomunica che sottrae punti vittoria in base ad altri punti
                        if (excommunicationTiles.getPermanentMalus().getPointResource().getClass().equals(VictoryPoint.class)) {
                            int value = player.getPlayerResources().getSpecificResource(VictoryPoint.class).getValue();
                            value = (value / 5) * excommunicationTiles.getPermanentMalus().getPointResource().getValue();
                            VictoryPoint victoryPointToSub = new VictoryPoint(value);
                            player.subResources(victoryPointToSub);
                        }
                        if (excommunicationTiles.getPermanentMalus().getPointResource().getClass().equals(MilitaryStrength.class)) {
                            int value = player.getPlayerResources().getSpecificResource(MilitaryStrength.class).getValue();
                            value = (value) * excommunicationTiles.getPermanentMalus().getPointResource().getValue();
                            VictoryPoint victoryPointToSub = new VictoryPoint(value);
                            player.subResources(victoryPointToSub);
                        }

                    }
                    if (excommunicationTiles.getPermanentMalus().getResourceList() != null) {// il giocatore ha una scomunica che sottrae punti vittoria in base ai costi di legno e pietra delle carte gialle
                        int costToPay = 0;
                        if (!player.getPersonalBoard().getPlayerCardList().getDevelopmentCardList().isEmpty()) {
                            for (Resource resource : excommunicationTiles.getPermanentMalus().getResourceList().getListOfResource()
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
                        }
                        VictoryPoint victoryPointToSub = new VictoryPoint(costToPay);
                        player.subResources(victoryPointToSub);
                    }
                    if (excommunicationTiles.getPermanentMalus().getString() != null) {
                        if (excommunicationTiles.getPermanentMalus().getString().equals("LostFinalVictoryPointFromPlayerResources")) {

                            int value = player.getPlayerResources().getPhysicalResource();
                            VictoryPoint victoryPointToSub = new VictoryPoint(value);
                            player.subResources(victoryPointToSub);
                        }
                    }
                }
            }
            if (!thirdPeriodExcomunication) {
                if (!player.getPersonalBoard().getPlayerCardList().getDevelopmentCardList().isEmpty()) {
                    finalExtraVictoryPoints1(player);
                    finalExtraVictoryPoints2(player);
                    finalExtraVictoryPoints3(player);
                }
            }
            finalExtraVictoryPoints4(player);

            model.notifyViews(new MVUpdateState("Aggiornato lo stato delle risorse finali", player.getStatePlayerResources()));
        }
    }


    /* metodi getter e setter */

    public void setInformationFromNetworking(InformationFromNetworking informationFromNetworking) {
        model.
                getModelChoices().
                setInformationFromNetworking(
                        informationFromNetworking);
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

    public List<ResourceList> getCouncilPrivilegeResChoice() {
        return councilPrivilegeResChoice;
    }

    public void setCouncilPrivilegeResChoice(List<ResourceList> councilPrivilegeResChoice) {
        this.councilPrivilegeResChoice = councilPrivilegeResChoice;
    }

    public Player getPlayerInAction() {
        return playerInAction;
    }

    public void setPlayerInAction(Player playerInAction) {
        this.playerInAction = playerInAction;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        if (playerMaxNumber >= 3) {
            this.gameBoard.add3PlayerActionSpace(actionSpaceEffectList);
        }
        if (playerMaxNumber == 4) {
            this.gameBoard.add4PlayerMarketSpace(actionSpaceEffectList);
        }
        this.gameBoard.setMarketId();
        setMarkerDisc();
    }

    public void setTimerConnection(long timerConnection) {
        model.getModelChoices().setTimerConnection(timerConnection);
    }

    public InformationFromNetworking getInformationFromNetworking() {
        return model.getModelChoices().getInformationFromNetworking();
    }

    public List<ExcommunicationTiles> getExcommunicationTilesList() {
        return excommunicationTilesList;
    }

    public void setExcommunicationTilesList(List<ExcommunicationTiles> excommunicationTilesList) {
        this.excommunicationTilesList = excommunicationTilesList;
    }

    public long getTimerAction() {
        return timerAction;
    }

    public void setTimerAction(long timerAction) {
        this.timerAction = timerAction;
    }

    public List<LeaderCard> getLeaderCardList() {
        return leaderCardList;
    }

    public void setLeaderCardList(List<LeaderCard> leaderCardList) {
        this.leaderCardList = leaderCardList;
    }

    public StateGame getStateGame(int period, int round, int playerNumber) {
        StateGame stateGame = new StateGame(period, round, playerList.get(playerNumber).getPlayerId());
        return stateGame;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public PersonalBonusTiles removePersonalBonusTiles(PersonalBonusTiles personalBonusTiles) {
        int i = 0;
        for (PersonalBonusTiles tiles : this.personalBonusTilesList
                ) {
            if (tiles.equals(personalBonusTiles))
                return this.personalBonusTilesList.remove(i);

            i++;
        }
        return null;
    }


}


