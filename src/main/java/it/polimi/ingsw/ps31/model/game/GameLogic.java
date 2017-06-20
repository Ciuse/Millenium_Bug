package it.polimi.ingsw.ps31.model.game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.json.CreationJson;
import it.polimi.ingsw.ps31.model.json.JsonFile;
import it.polimi.ingsw.ps31.model.json.JsonGameObject;

import java.util.*;

/**
 * Created by giulia on 24/05/2017.
 */
public class GameLogic {
    private final GameUtility gameUtility = new GameUtility();
    private final static int PERIODMAXNUMBER = 3;
    private final static int ROUNDMAXNUMBER = 2;
    private final static int ACTIONMAXNUMBER = 4;
    int playerMaxNumber;
    private int period;
    private int round;
    private int action;


    public void playGame() {
        CreationJson creationJson = new CreationJson();
        creationJson.createJsonFile();          //Creazione file json se non è già presente
        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("card.json");         //lettura file json
        JsonGameObject jsonObjectReadFromFile = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);        //salvataggio stringa json letta

        //salvo tutti gli oggetti letti dal file json
        gameUtility.setDevelopmentCardList(jsonObjectReadFromFile.getDevelopementCardList());
        gameUtility.setTowerActionSpaceEffectList(jsonObjectReadFromFile.getTowerActionSpaceEffectList());
        gameUtility.setActionSpaceEffectList(jsonObjectReadFromFile.getActionSpaceEffectList());
        gameUtility.setFaithTrackExtraValue(jsonObjectReadFromFile.getFaithTrackExtraValue());
        gameUtility.setBonusVictoryPointFromCharacterCard(jsonObjectReadFromFile.getBonusVictoryPointFromCharacterCard());
        gameUtility.setBonusVictoryPointFromTerritory(jsonObjectReadFromFile.getBonusVictoryPointFromTerritory());
        gameUtility.setBonusVictoryPointFromMilitaryTrack(jsonObjectReadFromFile.getBonusVictoryPointFromMilitaryTrack());
        gameUtility.setBonusVictoryPointFromPlayerResources(jsonObjectReadFromFile.getBonusVictoryPointFromPlayerResources());
        gameUtility.setPersonalBoardRequirements(jsonObjectReadFromFile.getPointResourceRequired());
        gameUtility.setInitialPlayerResource(jsonObjectReadFromFile.getInitialResourcePlayer());
        gameUtility.setPersonalBonusTilesList(jsonObjectReadFromFile.getPersonalBonusTilesList());
        gameUtility.setTimerAction(jsonObjectReadFromFile.getPlayerActionTimer());
        gameUtility.setTimerConnection(jsonObjectReadFromFile.getPlayerConnectionTimer());
        gameUtility.setLeaderCardList(jsonObjectReadFromFile.getLeaderCardList());

        //parte di connessione

        gameUtility.createTimerConnection();

        //viene invocato dopo lo scadere del tempo dopo che si sono connessi i primi 2 giocatori
        playerMaxNumber = gameUtility.getPlayerList().size();
        gameUtility.setPlayerMaxNumber(playerMaxNumber);


        //va fatto dopo aver saputo in quanti si gioca
        GameBoard gameBoard = new GameBoard(jsonObjectReadFromFile.getTowerActionSpaceEffectList(), jsonObjectReadFromFile.getActionSpaceEffectList(), jsonObjectReadFromFile.getFaithTrackExtraValue());
        gameUtility.setGameBoard(gameBoard);


        gameUtility.getDevelopmentCardList().shuffleCardList();  //mischio la lista di carte
        Collections.shuffle(gameUtility.getPersonalBonusTilesList()); //mischio i personal bonus tiles

        gameUtility.createDeck();

        for (this.period = 1; period <= PERIODMAXNUMBER; period++) {                               //inizio periodo
            gameUtility.setDeckTower(period);
            for (this.round = 1; round <= ROUNDMAXNUMBER; round++) {
                gameUtility.drawCardDeck();
                gameUtility.resetFamilyMember();
                gameUtility.getGameBoard().rollTheDice();
                gameUtility.setFamilyMemberDiceValue();

                for (this.action = 1; action <= ACTIONMAXNUMBER; action++) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        String string = "Aggiornato stato del gioco";
                        gameUtility.notifyViews(new MVUpdateState(string,gameUtility.getStateGame(this.period,this.round,playerNumber)));
                        gameUtility.phaseActionGame(playerNumber,action);
                    }
                }
                //sono finite le 16 azioni(massime) del turno e iniziano le 4 azioni(massime) che si sono perse per la scomunica
                gameUtility.extraPhaseActionGame();
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if (round == 2) {
                    gameUtility.vaticanReport(period);
                }
                gameUtility.playerOrderFromCouncil();
            }//fine ciclo turno
        }//fine ciclo periodo
        //gioco finito e calcolo punteggio finale
        gameUtility.getFinalVictoryPoint();
        gameUtility.militaryTrackWinnerPoint();
        gameUtility.orderVictoryPoint();
        //TODO metodo per stampare a video il vincitore


    }

}