package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.game.InformationFromNetworking;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * Created by giulia on 15/06/2017.
 * Classe che gestisce tutti i metpdo di "Wait" del model per attendere le risposte da parte degli utenti o per attendere che tutti gli utenti si siano connessi
 * Inoltre gestisce anche il timer per lo scadere del tempo delle connessioni
 * @see Timer
 * @see InformationFromNetworking
 * @see Model
 * @see Controller
 */
public class ModelChoices {
    /**
     *Riferimento a una classe utile al controller per controllare alcune scelte
     */
    private LastModelStateForControl lastModelStateForControl=new LastModelStateForControl();
    /**
     *Riferimento alla classe utile al controller per poter controllare il draft iniziale dei leader
     */
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice=new TempModelStateForLeaderChoice();
    private int leaderChoosenCounter=0;
    private int listToPay =-1;
    private TowerCardSpace towerCardSpaceChosen=null;
    private Boolean activeEffect = null ;
    private Boolean supportTheChurch=null;
    private PersonalBonusTiles personalBonusTilesChosen=null;
    private PlayerColor playerColorChosen=null;
    private LeaderCard leaderCardChosen=null;
    private ResourceList resourceChosenFromPrivilege;
    private int numberOfServantsToPay;
    private FamilyMember familyMemberChosen;
    private ActionSpace actionSpaceChosen;
    private Action actionToDo=null;
    private InformationFromNetworking informationFromNetworking;
    private String stateModelChoices="StateDefault";
    private long timerConnection;
    private Timer timer1 = new Timer();

    public ModelChoices() {

    }

    /**
     *  I metodi di wait permettono al model di mettersi in atteesa di una risposta prima di continuare l esecuzione delle azioni
     *  sarà il controller a settare le varie risposte provenienti dalle view, ovviamente prima assicurandosi della leggitimità delle risposte
     *
     *  @see Thread: il thread del model esegue delle sleep ad ogni ciclo per evitare un eccessivo uso della cpu durante i cicli di attesa
     */
    public synchronized int waitIntListToPay(){
        setListToPay(-1);
        setStateChoice();
        while(listToPay ==-1 && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return listToPay;
    }

    public synchronized void waitAllInitialLeaderCardChosen(int playerMaxNumber) {
        setListToPay(leaderChoosenCounter=0);
        setStateChoice();
        while (leaderChoosenCounter<playerMaxNumber && this.stateModelChoices.equals("StateChoice")) {  //aspetto 20 secondi per far scegliere a tutti il leader
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized LeaderCard waitLeaderCardChosen(){
        setLeaderCardChosen(null);
        setStateChoice();
        while(leaderCardChosen==null && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return leaderCardChosen;
    }

    public synchronized TowerCardSpace waitTowerCardChosen(){
        setTowerCardSpaceChosen(null);
        setStateChoice();
        while(towerCardSpaceChosen==null && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return towerCardSpaceChosen;
    }

    public synchronized ActionSpace waitActionSpaceChosen(){
        setActionSpaceChosen(null);
        setStateChoice();
        while(actionSpaceChosen==null&& this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return actionSpaceChosen;
    }

    public synchronized Boolean waitActiveEffect(){
        setActiveEffect(null);
        setStateChoice();
        while(activeEffect==null&& this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return activeEffect;
    }

    public synchronized ResourceList waitResourceChosenFromPrivilege(){
        setResourceChosenFromPrivilege(null);
        setStateChoice();
        while(resourceChosenFromPrivilege ==null&& this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return resourceChosenFromPrivilege;
    }

    public synchronized PersonalBonusTiles waitPersonalBonusTilesChosen(){
        setPersonalBonusTilesChosen(null);
        setStateChoice();
        while(personalBonusTilesChosen==null && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return personalBonusTilesChosen;
    }

    public synchronized int waitNumberOfServantsToPay(){
        setNumberOfServantsToPay(-1);
        setStateChoice();
        while(numberOfServantsToPay==-1 && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return numberOfServantsToPay;
    }

    public synchronized Boolean waitSupportTheChurch(){
        setSupportTheChurch(null);
        setStateChoice();
        while(supportTheChurch==null && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return supportTheChurch;
    }

    public synchronized PlayerColor waitPlayerColorChosen(){
        setPlayerColorChosen(null);
        setStateChoice();
        while(playerColorChosen==null && this.stateModelChoices.equals("StateChoice")){

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return playerColorChosen;
    }

    public synchronized FamilyMember waitFamilyMemberChosen(){
        setFamilyMemberChosen(null);
        setStateChoice();
        while(familyMemberChosen==null && this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return familyMemberChosen;
    }

    public synchronized Action waitActionToDo(){
        setActionToDo(null);
        setStateChoice();
        while(actionToDo==null&& this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(actionToDo!=null){
            setStateActionGame();
        }
        return actionToDo;
    }

    public void setActionSpaceChosen(ActionSpace actionSpaceChosen) {
        this.actionSpaceChosen = actionSpaceChosen;
    }

    public void setFamilyMemberChosen(FamilyMember familyMemberChosen) {
        this.familyMemberChosen = familyMemberChosen;
    }

    public int waitPlayerConnection(){

        boolean timerStarted=false;
        setStateConnection();
        while(informationFromNetworking.getPlayerNameList().size()<3 && stateModelChoices.equals("StateConnection")){     //continuo a ciclare finchè non si connettono 4 player o il tempo scade
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(informationFromNetworking.getPlayerNameList().size()>=2&&!timerStarted){
                createTimerConnection();
                timerStarted=true;
            }
        }

        timer1.purge();
        timer1.cancel();

        return informationFromNetworking.getPlayerNameList().size();


    }


    public void createTimerConnection() {


        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {

                System.out.println("tempo scaduto per connetterti ");
                setStateDefault();
                timer1.cancel();
            }
        };
        timer1.schedule(task1, timerConnection);
    }


    public void setLeaderCardChosen(LeaderCard leaderCardChosen) {
        this.leaderCardChosen = leaderCardChosen;
    }

    public void setListToPay(int choice){
        this.listToPay =choice;
    }

    public LastModelStateForControl getLastModelStateForControl() {
        return lastModelStateForControl;
    }

    public void setNumberOfServantsToPay(int numberOfServantsToPay) {
        this.numberOfServantsToPay = numberOfServantsToPay;
    }

    public void setActiveEffect(Boolean activeEffect) {
        this.activeEffect = activeEffect;
    }

    public void setSupportTheChurch(Boolean supportTheChurch) {
        this.supportTheChurch = supportTheChurch;
    }

    public void setActionToDo(Action actionToDo) {
        this.actionToDo = actionToDo;
    }

    public void setLastModelStateForControl(LastModelStateForControl lastModelStateForControl) {
        this.lastModelStateForControl = lastModelStateForControl;
    }

    public void setInformationFromNetworking(InformationFromNetworking informationFromNetworking) {
        this.informationFromNetworking = informationFromNetworking;
    }

    public void setTimerConnection(long timerConnection) {
        this.timerConnection = timerConnection;
    }

    public void setPersonalBonusTilesChosen(PersonalBonusTiles personalBonusTilesChosen) {
        this.personalBonusTilesChosen = personalBonusTilesChosen;
    }

    public InformationFromNetworking getInformationFromNetworking() {
        return informationFromNetworking;
    }

    public void setPlayerColorChosen(PlayerColor playerColorChosen) {
        this.playerColorChosen = playerColorChosen;
    }

    public void setTowerCardSpaceChosen(TowerCardSpace choice){
        this.towerCardSpaceChosen=choice;
    }

    public TempModelStateForLeaderChoice getTempModelStateForLeaderChoice() {
        return tempModelStateForLeaderChoice;
    }

    public void setTempModelStateForLeaderChoice(TempModelStateForLeaderChoice tempModelStateForLeaderChoice) {
        this.tempModelStateForLeaderChoice = tempModelStateForLeaderChoice;
    }

    public void setResourceChosenFromPrivilege(ResourceList resourceChosenFromPrivilege) {
        this.resourceChosenFromPrivilege = resourceChosenFromPrivilege;
    }

    public void incrementLeaderChoosenCounter() {
        this.leaderChoosenCounter++;
    }

/* stati del model choices per le attese delle scelte*/

    public void setStateChoice(){
        this.stateModelChoices="StateChoice";
    }

    public void setStateDefault(){
        this.stateModelChoices="StateDefault";
    }

    public void setStateEndTurn(){
        this.stateModelChoices="StateEndTurn";
    }

    public void setStateActionGame(){
        this.stateModelChoices="StateActionGame";
    }

    public void setStateConnection(){
        this.stateModelChoices="StateConnection";
    }

    public String getStateModelChoices() {
        return stateModelChoices;
    }


}
