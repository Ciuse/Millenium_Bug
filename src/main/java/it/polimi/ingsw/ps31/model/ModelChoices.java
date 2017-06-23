package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
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
 */
public class ModelChoices extends Model {
    private LastModelStateForControl lastModelStateForControl=new LastModelStateForControl();
    private int intChosen=-1;
    private TowerCardSpace towerCardSpaceChosen=null;
    private DevelopmentCard developmentCardChosen = null;
    private Boolean activeEffect = null ;
    private Boolean supportTheChurch=null;
    private Boolean actionEnded=null;
    private PersonalBonusTiles personalBonusTilesChosen=null;
    private PlayerColor playerColorChosen=null;
    private LeaderCard leaderCardChosen=null;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice=new TempModelStateForLeaderChoice();
    private String stateModelChoices="StateDefault";
    private InformationFromNetworking informationFromNetworking;
    private long timerConnection;
    private ResourceList resourceChosen;
    private int numberOfServantsToPay;
    private FamilyMember familyMemberChosen;
    private TowerActionSpace towerActionSpaceChosen;
    private ActionSpace actionSpaceChosen;
    private Action actionToDo=null;

    public synchronized int waitIntChosen(){
        setIntChosen(-1);
        setStateChoice();
        while(intChosen==-1 || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return intChosen;
    }

    public synchronized void waitAllInitialLeaderCardChosen() {

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000 || this.stateModelChoices.equals("StateChoice")) {  //aspetto 20 secondi per far scegliere a tutti il leader
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
    }

    public synchronized LeaderCard waitLeaderCardChosen(){
        setLeaderCardChosen(null);
        setStateChoice();
        while(leaderCardChosen==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return leaderCardChosen;
    }

    public synchronized TowerCardSpace waitTowerCardChosen(){
        setTowerCardSpaceChosen(null);
        setStateChoice();
        while(towerCardSpaceChosen==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return towerCardSpaceChosen;
    }
    public synchronized TowerActionSpace waitTowerActionSpaceChosen(){
        setTowerActionSpaceChosen(null);
        setStateChoice();
        while(towerActionSpaceChosen==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return towerActionSpaceChosen;
    }

    public synchronized ActionSpace waitActionSpaceChosen(){
        setActionSpaceChosen(null);
        setStateChoice();
        while(actionSpaceChosen==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return actionSpaceChosen;
    }

    public synchronized DevelopmentCard waitDevelopmentCardChosen(){
        setDevelopmentCardChosen(null);
        setStateChoice();
        while(developmentCardChosen==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return developmentCardChosen;
    }

    public synchronized boolean waitActiveEffect(){
        setActiveEffect(false);
        setStateChoice();
        while(activeEffect==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return activeEffect;
    }
    public synchronized ResourceList waitResourceChosen(){
        setResourceChosen(null);
        setStateChoice();
        while(resourceChosen==null|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return resourceChosen;
    }

    public synchronized PersonalBonusTiles waitPersonalBonusTilesChosen(){
        setPersonalBonusTilesChosen(null);
        setStateChoice();
        while(personalBonusTilesChosen==null || this.stateModelChoices.equals("StateChoice")){
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
        while(numberOfServantsToPay!=-1 || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return numberOfServantsToPay;
    }

    public synchronized boolean waitSupportTheChurch(){
        setSupportTheChurch(false);
        setStateChoice();
        while(supportTheChurch==null || this.stateModelChoices.equals("StateChoice")){
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
        while(playerColorChosen==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return playerColorChosen;
    }
    public synchronized FamilyMember waitFamilyMemberChosen(){
        setFamilyMemberChosen(null);
        setStateChoice();
        while(familyMemberChosen==null || this.stateModelChoices.equals("StateChoice")){
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
        while(actionToDo==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return actionToDo;
    }

    public void setActionSpaceChosen(ActionSpace actionSpaceChosen) {
        this.actionSpaceChosen = actionSpaceChosen;
    }

    public void setTowerActionSpaceChosen(TowerActionSpace towerActionSpaceChosen) {
        this.towerActionSpaceChosen = towerActionSpaceChosen;
    }

    public void setFamilyMemberChosen(FamilyMember familyMemberChosen) {
        this.familyMemberChosen = familyMemberChosen;
    }

    public int waitPlayerConnection(){
        boolean timerStarted=false;
        setStateConnection();
        while(informationFromNetworking.getPlayerNameList().size()<4&&stateModelChoices.equals("StateConnection")){     //continuo a ciclare finchè non si connettono 4 player o il tempo scade
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(informationFromNetworking.getPlayerNameList().size()>=2&&!timerStarted){
                createTimerConnection();
                timerStarted=true;
            }
        }return informationFromNetworking.getPlayerNameList().size();
    }


    public void createTimerConnection() {

        Timer timer1 = new Timer();
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

    public void setIntChosen(int choice){
        this.intChosen=choice;
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

    public void setActionEnded(Boolean actionEnded) {
        this.actionEnded = actionEnded;
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

    public void setDevelopmentCardChosen(DevelopmentCard developmentCardChosen) {
        this.developmentCardChosen = developmentCardChosen;
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

    public void setResourceChosen(ResourceList resourceChosen) {
        this.resourceChosen = resourceChosen;
    }

}
