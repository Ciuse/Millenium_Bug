package it.polimi.ingsw.ps31.model.game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.json.CreationJson;
import it.polimi.ingsw.ps31.model.json.JsonFile;
import it.polimi.ingsw.ps31.model.json.JsonGameObject;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.VirtualView;

import java.util.Collections;

/**
 * Created by giulia on 24/05/2017.
 */
public class GameLogic {
    //classe che contiene il metodo principale con i cicli di funzionamento del gioco / la lettura degli oggeti dal file json / l attesa di connessione dei player
    private final GameUtility gameUtility = new GameUtility();
    private final static int PERIODMAXNUMBER = 3;
    private final static int ROUNDMAXNUMBER = 2;
    private final static int ACTIONMAXNUMBER = 4;
    private final Model model;
    private int playerMaxNumber;
    private int period;
    private int round;
    private int action;
    private boolean started;
    private Match match;


    public GameLogic(InformationFromNetworking informationFromNetworking, Model model, Match match) {
        this.model = model;

        gameUtility.setModel(this.model);
        gameUtility.setInformationFromNetworking(informationFromNetworking);
        this.match = match;
    }

    public void startConnection(VirtualView virtualView) {
        //parte di connessione
        playerMaxNumber=gameUtility.getModel().getModelChoices().waitPlayerConnection();  //mi metto in attesa dei giocatori che si connettano
        gameUtility.setPlayerMaxNumber(playerMaxNumber);

        for(int i=0; i<playerMaxNumber; i++){
            gameUtility.createPlayer(gameUtility.getInformationFromNetworking().getPlayerNameList().get(i));
        }

        match.sendViews(gameUtility.getPlayerMaxNumber());
        System.out.println("GameLogic:startConnection> View inviate. Aggiungo la virtualView al model");
        model.addVirtualView(virtualView);

    }

    public void createJson(){
        CreationJson creationJson = new CreationJson();
        creationJson.createJsonFile();          //Creazione file json se non è già presente
        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("JsonObject.json");         //lettura file json
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
    }

    public void playGame() {

        System.out.println("GameLogic:playGame> Tutte le impostazioni correttamente caricate. Faccio partire il gioco");


        gameUtility.choiseColorPlayer(); //chiedo ai player (in base all ordine di connessione) il colore che vogliono

        gameUtility.phaseChoicePersonalBonusTiles(); //chiedo ai player di scegliere (in base all ordine di connessione) un personal bonus tiles e li aggiorno


        //aggiorno lo stato iniziali degli stati della view

        GameBoard gameBoard = new GameBoard(gameUtility.getTowerActionSpaceEffectList(), gameUtility.getActionSpaceEffectList(), gameUtility.getFaithTrackExtraValue(), model);
        gameUtility.setGameBoard(gameBoard);

        //viene fatto dopo aver saputo in quanto si gioca (per istanziare le aree da 3 e/o 4 giocatori)

        System.out.println("GameLogic:playGame> invio stato giocatori");
        gameUtility.updateStartAllPlayersInformation();
        System.out.println("GameLogic:playGame> invio risorse giocatori");
        gameUtility.updateStartAllPlayersResources();
        System.out.println("GameLogic:playGame> invio personal board");
        gameUtility.updateStartAllPersonalBoard();
        System.out.println("GameLogic:playGame> invio marker disc");
        gameUtility.updateStartAllMarkerDisc();
        System.out.println("GameLogic:playGame> invio carte sviluppo");
        gameUtility.updateStartAllDevelopmentCard();



        gameUtility.getDevelopmentCardList().shuffleCardList();  //mischio la lista di carte
        Collections.shuffle(gameUtility.getPersonalBonusTilesList()); //mischio i personal bonus tiles

        gameUtility.createDeck();

        for (this.period = 1; period <= PERIODMAXNUMBER; period++) {                               //inizio periodo
            gameUtility.setDeckTower(period);
            for (this.round = 1; round <= ROUNDMAXNUMBER; round++) {
                gameUtility.drawCardDeck();
                gameUtility.resetFamilyMember();        //restituisco a tutti i propri famigliari
                gameUtility.getGameBoard().rollTheDice();
                gameUtility.setFamilyMemberDiceValue();

                for (this.action = 1; action <= ACTIONMAXNUMBER; action++) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        String string = "Aggiornato stato del gioco";
                        gameUtility.getModel().notifyViews(new MVUpdateState(string,gameUtility.getStateGame(this.period,this.round,playerNumber)));
                        gameUtility.phaseActionGame(playerNumber,action);
                    }
                }
                //sono finite le 16 azioni(massime) del turno e iniziano le 4 azioni(massime) che si sono perse per la scomunica
                gameUtility.extraPhaseActionGame();
                //SOLITE COSE DA FARE DOPO LA FINE DELLA FASE AZIONI
                if (round == 2) {
                    gameUtility.vaticanReport(period);
                }
                gameUtility.playerOrderFromCouncil();       //ordino i player
                gameUtility.resetLeaderEffect();            //riattivo le abilità una volta per turno dei leader
            }//fine ciclo turno
        }//fine ciclo periodo
        //gioco finito e calcolo punteggio finale
        gameUtility.getFinalVictoryPoint();
        gameUtility.militaryTrackWinnerPoint();
        gameUtility.orderVictoryPoint();

        //TODO metodo per stampare a video il vincitore

    }

    public GameUtility getGameUtility() {
        return gameUtility;
    }
}