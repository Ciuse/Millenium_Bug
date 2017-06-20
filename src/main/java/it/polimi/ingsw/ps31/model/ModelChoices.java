package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;

import java.util.Observer;

import static java.lang.Thread.sleep;

/**
 * Created by giulia on 15/06/2017.
 */
public class ModelChoices extends Model {
    private LastModelStateForControl lastModelStateForControl=new LastModelStateForControl();
    private int intChosen=-1;
    private TowerCardSpace towerCardSpaceChosen=null;
    private DevelopmentCard developmentCardChosen = null;
    private boolean activeEffect = false;
    private PersonalBonusTiles personalBonusTilesChosen=null;
    private PlayerColor playerColorChosen=null;
    private LeaderCard leaderCardChosen=null;
    private boolean supportTheChurch=false;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice=new TempModelStateForLeaderChoice();
    private String stateModelChoices;

    public synchronized int waitIntChosen(){
        setIntChosen(-1);
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

    public synchronized DevelopmentCard waitDevelopmentCardChosen(){
        setDevelopmentCardChosen(null);
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
        while(activeEffect==false|| this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return activeEffect;
    }

    public synchronized PersonalBonusTiles waitPersonalBonusTilesChosen(){
        setPersonalBonusTilesChosen(null);
        while(personalBonusTilesChosen==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return personalBonusTilesChosen;
    }

    public synchronized boolean waitSupportTheChurch(){
        setSupportTheChurch(false);
        while(supportTheChurch==false || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return supportTheChurch;
    }

    public synchronized PlayerColor waitPlayerColorChosen(){
        setPlayerColorChosen(null);
        while(playerColorChosen==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return playerColorChosen;
    }

    public void setSupportTheChurch(boolean supportTheChurch) {
        this.supportTheChurch = supportTheChurch;
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

    public void setLastModelStateForControl(LastModelStateForControl lastModelStateForControl) {
        this.lastModelStateForControl = lastModelStateForControl;
    }

    public void setDevelopmentCardChosen(DevelopmentCard developmentCardChosen) {
        this.developmentCardChosen = developmentCardChosen;
    }

    public void setActiveEffect(boolean activeEffect) {
        this.activeEffect = activeEffect;
    }

    public void setPersonalBonusTilesChosen(PersonalBonusTiles personalBonusTilesChosen) {
        this.personalBonusTilesChosen = personalBonusTilesChosen;
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
    public void setStateEndTurn(){
        this.stateModelChoices="StateEndTurn";

    }
}
