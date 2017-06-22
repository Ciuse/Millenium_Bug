package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerActionSpace;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;

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
    private PersonalBonusTiles personalBonusTilesChosen=null;
    private PlayerColor playerColorChosen=null;
    private LeaderCard leaderCardChosen=null;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice=new TempModelStateForLeaderChoice();
    private String stateModelChoices;
    private ResourceList resourceChosen;
    private int numberOfServantsToPay;
    private FamilyMember familyMemberChosen;
    private TowerActionSpace towerActionSpaceChosen;
    private ActionSpace actionSpaceChosen;

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
    public synchronized TowerActionSpace waitTowerActionSpaceChosen(){
        setTowerActionSpaceChosen(null);
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
        while(familyMemberChosen==null || this.stateModelChoices.equals("StateChoice")){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return familyMemberChosen;
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

    public void setNumberOfServantsToPay(int numberOfServantsToPay) {
        this.numberOfServantsToPay = numberOfServantsToPay;
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

    public void setResourceChosen(ResourceList resourceChosen) {
        this.resourceChosen = resourceChosen;
    }
}
