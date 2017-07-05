package it.polimi.ingsw.ps31.model.game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.json.CreationJson;
import it.polimi.ingsw.ps31.model.json.JsonFile;
import it.polimi.ingsw.ps31.model.json.JsonGameObject;
import it.polimi.ingsw.ps31.server.Match;
import it.polimi.ingsw.ps31.server.VirtualView;

import java.util.Collections;

/**
 * Created by giulia on 24/05/2017.
 *
 * Classe principale del gioco. Contiene le principali logiche di funzionamento: Attesa dei player, Creazione del file Json, Cicli e scelte principali dell' avvio del gioco
 *
 * @see GameUtility
 * @see InformationFromNetworking
 * @see Match
 * @see CreationJson
 * @see Model
 * @see ModelChoices
 */
public class GameLogic {
    private final GameUtility gameUtility = new GameUtility();
    private final Model model;
    private Match match;
    private final static int PERIODMAXNUMBER = 3;
    private final static int ROUNDMAXNUMBER = 2;
    private final static int ACTIONMAXNUMBER = 4;
    private int playerMaxNumber;
    private int period;
    private int round;
    private int action;


    public GameLogic(InformationFromNetworking informationFromNetworking, Model model, Match match) {
        this.model = model;
        gameUtility.setModel(this.model);
        gameUtility.setInformationFromNetworking(informationFromNetworking);
        this.match = match;
    }

    /**
     * Metodo richiamato dal match il quale si mette in attesa dei vari client che si connettono
     * @param virtualView virtual view del match in corso, la quale verrà settata al model del match in corso
     */
    public void startConnection(VirtualView virtualView) {
        //attesa dei giocatori che si connettono
        playerMaxNumber=gameUtility.getModel().getModelChoices().waitPlayerConnection();
        gameUtility.setPlayerMaxNumber(playerMaxNumber);

        for(int i=0; i<playerMaxNumber; i++){
            gameUtility.createPlayer(gameUtility.getInformationFromNetworking().getPlayerNameList().get(i));
        }

        //invio ai client le informazioni per poter creare le loro view
        match.sendViews(gameUtility.getPlayerMaxNumber());

        model.addVirtualView(virtualView);

    }

    /**
     * Creazione del file .json (se necessaria) e conseguente lettura dell' oggetto della classe JsonGameObject contenente tutti gli oggetti
     * che erano stati serializzati e scritti su file.
     * Gli oggetti letti verranno settati come variabili della classe GameUtility
     */
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
        gameUtility.setCouncilPrivilegeResChoice(jsonObjectReadFromFile.getCouncilPrivilegeResChoice());
        gameUtility.setExcommunicationTilesList(jsonObjectReadFromFile.getExcommunicationTiles());
    }

    /**
     * Contiene tutta la logica di gioco e i cicli principali di funzionamento delle scelte e dei turni
     * I cicli secondari delle fasi di gioco sono contenuti all' interno della classe GameUtility che svolge una funzione di supporto
     * alla logica principale di gioco
     * @see GameUtility
     */
    public void playGame() {
        //chiedo ai player di scegliere(in base all ordine di connessione) il colore che vogliono
        gameUtility.choiseColorPlayer();

        //chiedo ai player di scegliere (in base all ordine di connessione) un personal bonus tiles e li aggiorno
        gameUtility.phaseChoicePersonalBonusTiles();


        GameBoard gameBoard = new GameBoard(gameUtility.getTowerActionSpaceEffectList(), gameUtility.getActionSpaceEffectList(), gameUtility.getFaithTrackExtraValue(), model);
        //setto le aree della game board soggette al variare del numero dei player con cui si gioca
        gameUtility.setGameBoard(gameBoard);

        //aggiorno lo stato iniziali degli stati della view
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

        //mischio la lista di carte
        gameUtility.getDevelopmentCardList().shuffleCardList();
        //mischio i personal bonus tiles
        Collections.shuffle(gameUtility.getPersonalBonusTilesList());
        //creo i deck
        gameUtility.createDeck();
        //Setto le scomuniche con cui si giocherà
        gameUtility.setExcommunicationMatchTiles();
        //Faccio partire il draft dei leader
        gameUtility.leaderCardSetup();

        for (this.period = 1; period <= PERIODMAXNUMBER; period++) {    //inizio periodo
            gameUtility.setDeckTower(period);
            for (this.round = 1; round <= ROUNDMAXNUMBER; round++) {

                gameUtility.resetFamilyMember();    //restituisco a tutti i propri famigliari
                gameUtility.drawCardDeck();
                gameUtility.getGameBoard().rollTheDice();
                gameUtility.setFamilyMemberDiceValue();

                for (this.action = 1; action <= ACTIONMAXNUMBER; action++) {
                    for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                        String string = "Aggiornato stato del gioco";
                        gameUtility.getModel().notifyViews(new MVUpdateState(string, gameUtility.getStateGame(this.period, this.round, playerNumber)));
                        gameUtility.phaseActionGame(playerNumber, action);
                    }

                }
                //sono finite le 16 azioni(massime) del turno e iniziano le 4 azioni(massime) che si sono perse per la scomunica
                for (int playerNumber = 0; playerNumber < playerMaxNumber; playerNumber++) {
                    String string = "Aggiornato stato del gioco";
                    gameUtility.getModel().notifyViews(new MVUpdateState(string, gameUtility.getStateGame(this.period, this.round, playerNumber)));
                    gameUtility.extraPhaseActionGame(playerNumber);
                }
//                //FINE FASE AZIONI  // TODO VERIFICARE CHE SIA MEGLIO DOPO
//                if (round == 2) {
//                    gameUtility.vaticanReport(period);
//                }
                gameUtility.playerOrderFromCouncil(); //ordino i player
                gameUtility.resetLeaderEffect();     //riattivo le abilità una volta per turno dei leader

            }//fine ciclo turno
            gameUtility.vaticanReport(period);

        }//fine ciclo periodo

        //gioco finito e calcolo punteggio finale
        gameUtility.getFinalVictoryPoint();
        gameUtility.militaryTrackWinnerPoint();
        gameUtility.orderVictoryPoint();

        gameUtility.getModel().notifyViews(new MVStringToPrint(null,true,"GRAZIE PER AVER GIOCATO, ALLA PROSSIMA PARTITA"));

        //TODO metodo per stampare a video il vincitore

        gameUtility.choiseColorPlayer(); //TODO RIMUOVEREeeeeeeeeee
    }

    public GameUtility getGameUtility() {
        return gameUtility;
    }
}