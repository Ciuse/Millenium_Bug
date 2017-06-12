package it.polimi.ingsw.ps31.model.game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PersonalBoard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.json.CreationJson;
import it.polimi.ingsw.ps31.model.json.JsonFile;
import it.polimi.ingsw.ps31.model.json.JsonGameObject;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.*;

/**
 * Created by giulia on 24/05/2017.
 */
public class StartGame {
    private final GameBoard gameBoard = new GameBoard();
    private final static int PERIODMAXNUMBER = 3;
    private final static int ROUNDMAXNUMBER = 2;
    private final static int ACTIONMAXNUMBER = 4;
    private int period;
    private int round;
    private List<Player> playerList;

    public void playGame() {
        CreationJson creationJson = new CreationJson();
        creationJson.createJsonFile();          //Creazione file json se non è già presente
        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("card.json");         //lettura file json
        JsonGameObject jsonObjectReadFromFile = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);        //salvataggio stringa json letta

        //salvo tutti gli oggetti letti dal file json
        DevelopmentCardList developmentCardList = jsonObjectReadFromFile.getDevelopementCardList();
        List<List<EffectList>>towerActionSpaceEffectList = jsonObjectReadFromFile.getTowerActionSpaceEffectList();
        List<EffectList> actionSpaceEffectList = jsonObjectReadFromFile.getActionSpaceEffectList();
        VictoryPoint[] faithTrackExtraValue = jsonObjectReadFromFile.getFaithTrackExtraValue();
        VictoryPoint[] bonusVictoryPointFromCharacterCard = jsonObjectReadFromFile.getBonusVictoryPointFromCharacterCard();
        VictoryPoint[] bonusVictoryPointFromTerritory = jsonObjectReadFromFile.getBonusVictoryPointFromTerritory();
        List<PointResource[]> personalBoardRequirements = jsonObjectReadFromFile.getPointResourceRequired();
        VictoryPoint[] bonusVictoryPointFromMilitaryTrack = jsonObjectReadFromFile.getBonusVictoryPointFromMilitaryTrack();
        VictoryPoint bonusVictoryPointFromPlayerResources = jsonObjectReadFromFile.getBonusVictoryPointFromPlayerResources();
        List<ResourceList> initialPlayerResource = jsonObjectReadFromFile.getInitialResourcePlayer();

        gameBoard.initializateGameBoard(towerActionSpaceEffectList,actionSpaceEffectList,faithTrackExtraValue);

        developmentCardList.shuffleCardList();  //mischio la lista di carte le

        //creazione deck vuoti
        List<DevelopmentCardDeck> deckList = new ArrayList<>();
        CardColor[] cardColors = {CardColor.GREEN, CardColor.YELLOW, CardColor.PURPLE, CardColor.BLUE};
        for (int i = 0; i < cardColors.length; i++) {
            for (period = 1; period <= 3; period++) {
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








        long delayAction = 120000;

        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {

                System.out.println("tempo scaduto per la tua azione ");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("tempo scaduto");
            }
        };


        timer1.schedule(task2, 120000);

        //viene invocato dopo lo scadere del tempo dopo che si sono connessi i primi 2 giocatori
        int playerMaxNumber = playerList.size();

        for (this.period = 1; period <= PERIODMAXNUMBER; period++) {                               //inizio periodo

            for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
                gameBoard.getTowers().get(towerNum).setDeck(deckList, period);
            }
            for (this.round = 1; round <= ROUNDMAXNUMBER; round++) {
                for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
                    gameBoard.getTowers().get(towerNum).drawCardFromDeck();
                }
                resetFamilyMember();
                gameBoard.rollTheDice();
                setFamilyMemberDiceValue();
                for (int action = 1; action <= ACTIONMAXNUMBER; action++) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1, delayAction);
                        if (action == 1 && playerList.get(playerMaxNumber).getFlagTurnExcommunication() == 1) {
                            task1.cancel();
                            timer1.purge();
                            gameBoard.endActionTurn(playerList.get(playerMaxNumber));
                        }
                       if (playerList.get(playerNumber).checkIfOnlyNEUTRALRemained() == true) {
                            gameBoard.getEndActionButton().setActive(true);
                        }
                        //FASE AZIONE DEL GIOCATORE
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));

                    }
                }
                //sono finite le 16 azioni(massime) del turno e iniziano le 4 azioni(massime) che si sono perse per la scomunica
                for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                    if (playerList.get(playerMaxNumber).getFlagTurnExcommunication() == 1) {
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1, delayAction);
                    }
                }
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if (round == 2) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        if (playerList.get(playerNumber).getPlayerResources().getResourceValue(FaithPoint.class)< gameBoard.getFaithPointTrack().getTrackCell().get(2 + period).getValue()) {
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
                //aggiungo alla lista dei colori del palazzo del consiglio gli eventuali giocatori che non si sono posizionati in questo spazio azione e poi riordino la lista giocatori
                List<PlayerColor> colorOrder = new ArrayList(gameBoard.getCouncilPalace().getColorOrder());
                for (int i = 0; i < playerList.size(); i++) {
                    if (gameBoard.getCouncilPalace().checkIfPresentColor(playerList.get(i).getPlayerColor()) == false) {
                        colorOrder.add(playerList.get(i).getPlayerColor());
                    }
                }
                this.orderPlayersListWithColors(colorOrder);
            }//fine ciclo turno
        }//fine ciclo periodo
        //gioco finito ,calcolo punteggio finale
        this.finalExtraVictoryPoints1(bonusVictoryPointFromTerritory);
        this.finalExtraVictoryPoints2(bonusVictoryPointFromCharacterCard);
        this.finalExtraVictoryPoints3();
        this.finalExtraVictoryPoints4(bonusVictoryPointFromMilitaryTrack);
        this.finalExtraVictoryPoints5(bonusVictoryPointFromPlayerResources);
        this.orderVictoryPoint();
        //TODO metodo per stampare a video il vincitore


    }

    /*Metodi per calcolare i punti vittoria alla fine del gioco */


    public void finalExtraVictoryPoints1(VictoryPoint[] bonusVictoryPointFromTerritory) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayerCardList().countCardGreen() > 1) {
                VictoryPoint victoryPointToAdd = bonusVictoryPointFromTerritory[playerList.get(i).getPlayerCardList().countCardGreen() - 1];
                playerList.get(i).addResources(victoryPointToAdd);
            }
        }
    }
    public void finalExtraVictoryPoints2(VictoryPoint[] bonusVictoryPointFromCharacter) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayerCardList().countCardBlue() > 1) {
                VictoryPoint victoryPointToAdd = bonusVictoryPointFromCharacter[playerList.get(i).getPlayerCardList().countCardBlue() - 1];
                playerList.get(i).addResources(victoryPointToAdd);
            }
        }
    }
    public void finalExtraVictoryPoints3() {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).activateFinalEffects();
        }

    }
    public void finalExtraVictoryPoints4(VictoryPoint[] bonusVictoryPointFromMilitaryTrack) {
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
    public void finalExtraVictoryPoints5(VictoryPoint factor) {
        for (int i=0;i<playerList.size();i++){
            int value = playerList.get(i).getPlayerResources().getPlayerResourceList().getPhysicalResource();
            int factorValue = factor.getValue();
            value= (value%5)*factorValue;
            VictoryPoint victoryPointToAdd = new VictoryPoint(value);
            playerList.get(i).addResources(victoryPointToAdd);
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

    //la view deve richiedere il nome e il colore che vuole essere
    //creazione familymembers in base al colore che il player ha scelto ,la personal board, la lista delle risorse iniziali infine il player

    public Player createPlayer(String name,PlayerColor playerColor,List<ResourceList> listOfResourceList,List<PointResource[]>personalBoardRequirements){
        PlayerId[] playerId = {PlayerId.ONE,PlayerId.TWO,PlayerId.THREE,PlayerId.FOUR};
        PersonalBoard personalBoard = new PersonalBoard(personalBoardRequirements, playerId[playerList.size()]);
        Player playerCreated = new Player(playerColor,listOfResourceList.get(playerList.size()), playerId[playerList.size()], name,personalBoard);
        return playerCreated;
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
}
