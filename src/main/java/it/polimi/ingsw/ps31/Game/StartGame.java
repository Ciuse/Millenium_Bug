package it.polimi.ingsw.ps31.Game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.Board.FaithPointTrack;
import it.polimi.ingsw.ps31.Board.GameBoard;
import it.polimi.ingsw.ps31.Card.DevelopmentCard;
import it.polimi.ingsw.ps31.Card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.Card.DevelopmentCardList;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.PlayerColor;
import it.polimi.ingsw.ps31.Json.CreationCard;
import it.polimi.ingsw.ps31.Json.JsonFile;
import it.polimi.ingsw.ps31.Json.JsonGameObject;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
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

        CreationCard.createCardList();

        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("Card.json");
        JsonGameObject jsonObjectReadFromFile = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);
        List<DevelopmentCard> developmentCardList=jsonObjectReadFromFile.getCardList();
        List<DevelopmentCardDeck> deckList = new ArrayList<>();
        CardColor[] cardColors= {CardColor.GREEN,CardColor.YELLOW,CardColor.PURPLE,CardColor.BLUE};
        for(int i=0;i<cardColors.length;i++) {
            for (int period = 1; period <= 3; period++) {
                deckList.add(new DevelopmentCardDeck(cardColors[i], period));
            }
        }

        for(int j=0;j<deckList.size();j++){
            System.out.println(deckList.get(j).getCardListSize());
        }

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

        for(int j=0;j<deckList.size();j++){
            System.out.println(deckList.get(j).getCardListSize());
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

        for (int period=1;period<=PERIODMAXNUMBER;period++){

            for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){
                gameBoard.getTowers()[towerNum].setDeck(deckList,period);
                }
            for(int round=1;round<=ROUNDMAXNUMBER;round++){
                for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){
                    gameBoard.getTowers()[towerNum].drawCardFromDeck();
                }
                gameBoard.rollTheDice();
                for(int action=1;action<=ACTIONMAXNUMBER;action++){
                    for(int playerNumber=0;playerNumber<playerMaxNumber;playerNumber++){
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1,delayAction);
                        if(action==1&&playerList.get(playerMaxNumber).getFlagExcommunication()==1){
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
                //sono finite le 16 azioni possibili del turno (caso massimo)
                for(int playerNumber=0;playerNumber<playerMaxNumber;playerNumber++){
                    if(playerList.get(playerMaxNumber).getFlagExcommunication()==1){
                        gameBoard.startActionTurn(playerList.get(playerMaxNumber));
                        //RICEVO CONFERMA RISPOSTA -> INIZIO TIMER
                        timer1.schedule(task1,delayAction);
                    }
                }
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if(round==2) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        if (playerList.get(playerNumber).getResources().getResource("FaithPoints").getValue() < gameBoard.getFaithPointTrack().getTrackCell()[2 + period].getValue()) {
                            gameBoard.getExcommunicationList().get(period).setExcommunicationToPlayer(playerList.get(playerMaxNumber));
                            //regola dell'ultimo turno del terzo periodo (tutti ricevono i punti vittoria )
                            if (period == 3) {
                                int faithPointPlayer = playerList.get(playerNumber).getResources().getResource("FaithPoints").getValue();
                                playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell()[faithPointPlayer].getExtraValue());
                                playerList.get(playerNumber).subResources(playerList.get(playerNumber).getResources().getResource("FaithPoints"));
                            }
                        } else {
                            //chiedo l'intervento della view e una volta ricevuto il messaggio di risposta true (il giocatore vuole spendere i suoi punti fede per evitare la scomunica)
                            int faithPointPlayer = playerList.get(playerNumber).getResources().getResource("FaithPoints").getValue();
                            playerList.get(playerNumber).addResources(gameBoard.getFaithPointTrack().getTrackCell()[faithPointPlayer].getExtraValue());
                            playerList.get(playerNumber).subResources(playerList.get(playerNumber).getResources().getResource("FaithPoints"));
                        }
                    }
                }
                //fine di ogni turno
                List colorOrder = new ArrayList(gameBoard.getCouncilPalace().getColorOrder()); //ho ottenuto la lista dei colori in ordine di arrivo nel palazzo del concilio
                List<PlayerColor> colorsOfAllPlayers = new ArrayList<>();        //lista di tutti i colori dei giocatori in gioco
                List<PlayerColor> newOrderOfColors = new ArrayList<>();
                List<Player> newPlayerList = new ArrayList<>();
                for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                    colorsOfAllPlayers.add(playerNumber,playerList.get(playerNumber).getColor());
                }
                for(int i=0;i<colorOrder.size();i++){
                    for(int j=0;j<colorsOfAllPlayers.size();j++){
                        if(colorOrder.get(i).equals(colorsOfAllPlayers.get(j))){
                            int k=0;
                            newOrderOfColors.add(k,colorsOfAllPlayers.get(j));
                            k++;
                            int n=k; //una volta che ho aggiunto alla nuova lista di colori i colori presenti nel palazzo del concilio in ordine, devo aggiungere quelli in più
                        }

                    }

                }




            }
       }
    }
}
