package it.polimi.ingsw.ps31.game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.board.GameBoard;
import it.polimi.ingsw.ps31.card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.constants.PlayerColor;
import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.gameThings.MilitaryStrength;
import it.polimi.ingsw.ps31.gameThings.VictoryPoint;
import it.polimi.ingsw.ps31.json.CreationJson;
import it.polimi.ingsw.ps31.json.JsonFile;
import it.polimi.ingsw.ps31.json.JsonGameObject;
import it.polimi.ingsw.ps31.player.Player;

import java.util.*;

/**
 * Created by giulia on 24/05/2017.
 */
public class StartGame {
    private static GameBoard gameBoard = GameBoard.getInstance();
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
        EffectList[] towerActionSpaceEffectArray = jsonObjectReadFromFile.getTowerActionSpaceEffectArray();
        List<EffectList> actionSpaceEffectList = jsonObjectReadFromFile.getActionSpaceEffectList();
        VictoryPoint[] faithTrackExtraValue = jsonObjectReadFromFile.getFaithTrackExtraValue();
        VictoryPoint[] bonusVictoryPointFromCharacterCard = jsonObjectReadFromFile.getBonusVictoryPointFromCharacterCard();
        VictoryPoint[] bonusVictoryPointFromTerritory = jsonObjectReadFromFile.getBonusVictoryPointFromTerritory();
        MilitaryStrength[] requiredMilitaryStrengthForTerritory = jsonObjectReadFromFile.getRequiredMilitaryStrengthForTerritory();
        VictoryPoint[] bonusVictoryPointFromMilitaryTrack = jsonObjectReadFromFile.getBonusVictoryPointFromMilitaryTrack();
        VictoryPoint bonusVictoryPointFromPlayerResources = jsonObjectReadFromFile.getBonusVictoryPointFromPlayerResources();


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
                gameBoard.getTowers()[towerNum].setDeck(deckList, period);
            }
            for (this.round = 1; round <= ROUNDMAXNUMBER; round++) {
                for (int towerNum = 0; towerNum < gameBoard.getTOWERNUMBER(); towerNum++) {
                    gameBoard.getTowers()[towerNum].drawCardFromDeck();
                }
                gameBoard.rollTheDice();
                for (int action = 1; action <= ACTIONMAXNUMBER; action++) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1, delayAction);
                        if (action == 1 && playerList.get(playerMaxNumber).getFlagExcommunication() == 1) {
                            task1.cancel();
                            timer1.purge();
                            gameBoard.endActionTurn(playerList.get(playerMaxNumber));
                        }
                        if (playerList.get(playerNumber).checkIfOnlyNEUTRALRemained() == true) {
                            gameBoard.getEndActionButton().setActive(true);
                        }
                        //FASE AZIONE DEL GIOCATORE


                    }
                }
                //sono finite le 16 azioni(massime) del turnoe iniziano le 4 azioni(massime) che si sono perse per la scomunica
                for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                    if (playerList.get(playerMaxNumber).getFlagExcommunication() == 1) {
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1, delayAction);
                    }
                }
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if (round == 2) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        if (playerList.get(playerNumber).getResources().getResource("FaithPoint").getValue() < gameBoard.getFaithPointTrack().getTrackCell()[2 + period].getValue()) {
                            gameBoard.getExcommunicationList().get(period).setExcommunicationToPlayer(playerList.get(playerMaxNumber));

                            //regola dell'ultimo turno del terzo periodo (tutti ricevono i punti vittoria )
                            if (period == 3) {
                                int faithPointPlayer = playerList.get(playerNumber).getResources().getResource("FaithPoint").getValue();
                                playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell()[faithPointPlayer].getExtraValue());
                                playerList.get(playerNumber).subResources(playerList.get(playerNumber).getResources().getResource("FaithPoint"));
                            }
                        } else {
                            //chiedo l'intervento della view e una volta ricevuto il messaggio di risposta true (il giocatore vuole spendere i suoi punti fede per evitare la scomunica)
                            int faithPointPlayer = playerList.get(playerNumber).getResources().getResource("FaithPoint").getValue();
                            playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell()[faithPointPlayer].getExtraValue());
                            playerList.get(playerNumber).subResources(playerList.get(playerNumber).getResources().getResource("FaithPoint"));
                        }
                    }
                }
                //aggiungo alla lista dei colori del palazzo del consiglio gli eventuali giocatori che non si sono posizionati in questo spazio azione e poi riordino la lista giocatori
                List<PlayerColor> colorOrder = new ArrayList(gameBoard.getCouncilPalace().getColorOrder());
                for (int i = 0; i < playerList.size(); i++) {
                    if (gameBoard.getCouncilPalace().checkIfPresentColor(playerList.get(i).getColor()) == false) {
                        colorOrder.add(playerList.get(i).getColor());
                    }
                }
                this.orderPlayersListWithColors(colorOrder);
            }//fine ciclo turno
        }//fine ciclo periodo
        //gioco finito ,calcolo punteggio finale


    }

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
    //TODO un metodo che sommi i punti vittoria delle carte viola alla fine del gioco
    public void finalExtraVictoryPoints3() {
        for (int i = 0; i < playerList.size(); i++) {
            for (int k = 0; k < playerList.get(i).getPlayerCardList().getSpecificCardList(CardColor.PURPLE).size(); k++) {
                //playerList.get(i).getPlayerCardList().getSpecificCardList(CardColor.PURPLE).get(k).getPermanentEffectList()
            }
        }

    }
    //aggiungo punti vittoria in base ai punti militari ottenuti a fine partita
    public void finalExtraVictoryPoints4(MilitaryStrength[] bonusVictoryPointFromMilitaryTrack) {
        this.orderMilitaryStrength();
        boolean paritàTrovata = false;
        int contatore=0;
        for(int i=0;i<playerList.size();i++) {
            if (playerList.get(0).getResources().getResource("MilitaryStrength").getValue()== playerList.get(i).getResources().getResource("MilitaryStrength").getValue()) {
                contatore++;
                playerList.get(i).getResources().addResources(bonusVictoryPointFromMilitaryTrack[0]);
            }

        }
        if(contatore>1){
            paritàTrovata=true;
        }
        int contatore2 =0;
        for(int j=1;j<playerList.size();j++){
            if (paritàTrovata==false && playerList.get(1).getResources().getResource("MilitaryStrength").getValue()== playerList.get(j).getResources().getResource("MilitaryStrength").getValue()) {
                contatore2++;
              playerList.get(j).getResources().addResources(bonusVictoryPointFromMilitaryTrack[1]);
            }
        }
        if (contatore2>1){
            paritàTrovata = true;
        }
    }
    public void finalExtraVictoryPoints5(VictoryPoint factor) {
        for (int i=0;i<playerList.size();i++){
            int value = playerList.get(i).getResources().getPlayerResourceAsResourceList().getPhysicalResource();
            int factorValue = factor.getValue();
            value= (value%5)*factorValue;
            VictoryPoint victoryPointToAdd = new VictoryPoint(value);
            playerList.get(i).addResources(victoryPointToAdd);
        }
    }


    // riordina i giocatori in base all'ordine dei colori nel palazzo del concilio
    public void orderPlayersListWithColors(List<PlayerColor> colorList) {
        for (int i = 0; i < colorList.size(); i++) {
            for (int j = 0; j < playerList.size(); j++) { //il for con la j serve solo per fare 4 cicli massimi
                if (!colorList.get(i).equals(playerList.get(i).getColor())) {
                    Player removedPlayer = playerList.remove(i);   //il remove mi fa scalare automaticamente tutti i giocatori verso sinistra
                    playerList.add(removedPlayer);
                }
            }
        }
    }


    // riordino la lista dei giocatori in base a chi ha preso più punti militari
    public void orderMilitaryStrength() {
        Player[] arrayPlayerLists = playerList.toArray(new Player[playerList.size()]);
        for (int i = 0; i < arrayPlayerLists.length-1; i++) {
            for (int j = 0; j < arrayPlayerLists.length; j++) {
                if (arrayPlayerLists[i].getResources().getResource("MilitaryStrength").getValue() <
                        arrayPlayerLists[i+1].getResources().getResource("MilitaryStrength").getValue()) {
                    Player tempPlayer = arrayPlayerLists[i+1];
                    arrayPlayerLists[i+1] = arrayPlayerLists[i];
                    arrayPlayerLists[i] = tempPlayer;
                }
            }
        }
        this.playerList = Arrays.asList(arrayPlayerLists);
    }


}
