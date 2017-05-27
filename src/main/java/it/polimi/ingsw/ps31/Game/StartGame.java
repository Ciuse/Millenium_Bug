package it.polimi.ingsw.ps31.Game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.Board.GameBoard;
import it.polimi.ingsw.ps31.Card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.Card.DevelopmentCardList;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.PlayerColor;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.GameThings.VictoryPoint;
import it.polimi.ingsw.ps31.Json.CreationJson;
import it.polimi.ingsw.ps31.Json.JsonFile;
import it.polimi.ingsw.ps31.Json.JsonGameObject;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by giulia on 24/05/2017.
 */
public class StartGame {
    private static GameBoard gameBoard;
    private final static int PERIODMAXNUMBER = 3;
    private final static int ROUNDMAXNUMBER = 2;
    private final static int ACTIONMAXNUMBER = 4;
    private int period;
    private int round;
    private List<Player> playerList;

    public void playGame(){
        CreationJson creationJson =new CreationJson();
        creationJson.createJsonFile();          //Creazione file json se non è già presente
        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("Card.json");         //lettura file json
        JsonGameObject jsonObjectReadFromFile = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);        //salvataggio stringa json letta

        //salvo tutti gli oggetti letti dal file json
        DevelopmentCardList developmentCardList=jsonObjectReadFromFile.getCardList();
        EffectList[] towerActionSpaceEffecArray= jsonObjectReadFromFile.getTowerActionSpaceEffecArray();
        List<EffectList> actionSpaceEffectList = jsonObjectReadFromFile.getActionSpaceEffectList();
        int[] faithTrackExtraValue=jsonObjectReadFromFile.getFaithTrackExtraValue();
        int[] bonusVictoryPointFromCharacterCard=jsonObjectReadFromFile.getBonusVictoryPointFromCharacterCard();
        int[] bonusVictoryPointFromTerritory=jsonObjectReadFromFile.getBonusVictoryPointFromTerritory();
        int[] requiredMilitaryStrengthForTerritory=jsonObjectReadFromFile.getRequiredMilitaryStrengthForTerritory();
        int bonusVictoryPointFromPlayerResources=jsonObjectReadFromFile.getBonusVictoryPointFromPlayerResources();

        //creazione deck vuoti
        List<DevelopmentCardDeck> deckList = new ArrayList<>();
        CardColor[] cardColors= {CardColor.GREEN,CardColor.YELLOW,CardColor.PURPLE,CardColor.BLUE};
        for(int i=0;i<cardColors.length;i++) {
            for (int period = 1; period <= 3; period++) {
                deckList.add(new DevelopmentCardDeck(cardColors[i], period));
            }
        }

        //riempimento dei deck in base al periodo e al colore delle carte
        for(int i=0;i<developmentCardList.size();i++){
            for(int j=0;j<deckList.size();j++){
                if(deckList.get(j).getCardListSize()<deckList.get(j).getMaxNumber()
                        &&developmentCardList.get(i).getCardColor().equals(deckList.get(j).getColor())
                        &&developmentCardList.get(i).getPeriod()==deckList.get(j).getPeriod()){
                    deckList.get(j).setCard(developmentCardList.get(i));
                    break;
                }
            }
        }

        long delayAction=120000;

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

        timer1.schedule(task2,120000);

        //viene invocato dopo lo scadere del tempo dopo che si sono connessi i primi 2 giocatori
        int playerMaxNumber= playerList.size();

        for (this.period=1;period<=PERIODMAXNUMBER;period++){                               //inizio periodo

            for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){            //associo i deck alle torri
                gameBoard.getTowers()[towerNum].setDeck(deckList,period);
                }
            for(this.round=1;round<=ROUNDMAXNUMBER;round++){                               //pesco le carte dai deck e le metto nelle torre
                for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){
                    gameBoard.getTowers()[towerNum].drawCardFromDeck();
                }
                gameBoard.rollTheDice();                                                    //tiro i dadi
                for(int action=1;action<=ACTIONMAXNUMBER;action++){                         // inizio fase azioni
                    for(int playerNumber=0;playerNumber<playerMaxNumber;playerNumber++){
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1,delayAction);
                        if(action==1&&playerList.get(playerMaxNumber).getFlagExcommunication()==1){ //se il giocatore ha la scomunica del turno perde la sua prima azione
                            task1.cancel();
                            timer1.purge();
                            gameBoard.endActionTurn(playerList.get(playerMaxNumber));
                        }
                        if(playerList.get(playerNumber).checkIfOnlyNEUTRALRemained()==true){
                            gameBoard.getEndActionButton().setActive(true);
                        }
                        //FASE AZIONE DEL GIOCATORE


                    }
                }
                //sono finite le 16 azioni(massime) del turnoe iniziano le 4 azioni(massime) che si sono perse per la scomunica
                for(int playerNumber=0;playerNumber<playerMaxNumber;playerNumber++){
                    if(playerList.get(playerMaxNumber).getFlagExcommunication()==1){
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1,delayAction);
                    }
                }
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if(round==2) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {        //rapporto con il vaticano TODO magari spostarlo sotto e attivarlo e basata per chiarezza
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
                for(int i=0;i<playerList.size();i++){
                    if(gameBoard.getCouncilPalace().checkIfPresentColor(playerList.get(i).getColor())==false){
                        colorOrder.add(playerList.get(i).getColor());
                    }
                }
                this.orderPlayersListWithColors(colorOrder);
            }//fine ciclo turno
        }//fine ciclo periodo
        //gioco finito ,calcolo punteggio finale


    }
    public void finalExtraVictoryPoints1 (int[] bonusVictoryPointFromTerritory){
        for (int i=0;i<playerList.size();i++){
            if(playerList.get(i).getPlayerCardList().countCardGreen()>1) {
                int value = bonusVictoryPointFromTerritory[playerList.get(i).getPlayerCardList().countCardGreen()-1];
                VictoryPoint victoryPointToAdd = new VictoryPoint(value);
                playerList.get(i).addResources(victoryPointToAdd);
            }
        }
    }
    public void finalExtraVictoryPoints2 (int[] bonusVictoryPointFromCharacter){
        for (int i=0;i<playerList.size();i++){
            if(playerList.get(i).getPlayerCardList().countCardBlue()>1) {
                int value = bonusVictoryPointFromCharacter[playerList.get(i).getPlayerCardList().countCardBlue() - 1];
                VictoryPoint victoryPointToAdd = new VictoryPoint(value);
                playerList.get(i).addResources(victoryPointToAdd);
            }
        }
    }
    //aggiungo punti vittoria in base ai punti militari ottenuti a fine partita
    public void finalExtraVictoryPoints3(){
        this.orderMilitaryStrength();
        if(playerList.get(0).getResources().getResource("MilitaryStrength").getValue()!=playerList.get(1).getResources().getResource("MilitaryStrength").getValue()){
        VictoryPoint victoryPointToAdd1 = new VictoryPoint(5);
        VictoryPoint victoryPointToAdd2 = new VictoryPoint(2);
        playerList.get(0).addResources(victoryPointToAdd1);
        playerList.get(1).addResources(victoryPointToAdd2);
        }else {
            VictoryPoint victoryPointToAdd1 = new VictoryPoint(5);
            VictoryPoint victoryPointToAdd2 = new VictoryPoint(5);
            playerList.get(0).addResources(victoryPointToAdd1);
            playerList.get(1).addResources(victoryPointToAdd2);
        }

    }
    //TODO un metodo che sommi i punti vittoria delle carte viola alla fine del gioco
    public void finalExtraVictoryPoints4(){
        for(int i=0;i<playerList.size();i++){
            for(int k=0; k<playerList.get(i).getPlayerCardList().getSpecificCardList(CardColor.PURPLE).size();k++){
               // VictoryPoint(playerList.get(i).getPlayerCardList().getSpecificCardList(CardColor.PURPLE).get(k).getPermanentEffectList().getActionSpaceEffectList().get(0).activate(playerList.get(i)));
            }
        }

    }
    // riordina i giocatori in base all'ordine dei colori nel palazzo del concilio
    public void orderPlayersListWithColors (List<PlayerColor> colorList){
        for (int i=0;i<colorList.size();i++){
            for (int j=0;j<playerList.size();j++){ //il for con la j serve solo per fare 4 cicli massimi
                if(!colorList.get(i).equals(playerList.get(i).getColor())){
                    Player removedPlayer = playerList.remove(i);   //il remove mi fa scalare automaticamente tutti i giocatori verso sinistra
                    playerList.add(removedPlayer);
                }
            }
        }
    }
    //riordino la lista dei giocatori in base a chi ha preso più punti militari
    public void orderMilitaryStrength(){
        for(int i=0;i<playerList.size();i++){
            for(int j=0;j<playerList.size();j++){
                if(playerList.get(i).getResources().getResource("MilitaryStrength").getValue()!=playerList.get(i).getResources().getResource("MilitaryStrength").getValue()){
                    Player removedPlayer = playerList.remove(i);
                    playerList.add(removedPlayer);
                }
            }
        }
    }


}
