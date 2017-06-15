package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.effect.Effect;

import static java.lang.Thread.sleep;

/**
 * Created by giulia on 15/06/2017.
 */
public class ModelChoices extends Model {
    int intChosen=-1;
    TowerCardSpace towerCardSpaceChosen=null;
    DevelopmentCard developmentCardChosen = null;
    boolean activeEffect = false;

    public synchronized int waitIntChosen(){
        while(intChosen==-1){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO SISTEMARE
        }
        return intChosen;
    }

    public synchronized TowerCardSpace waitTowerCardChosen(){
        while(towerCardSpaceChosen==null){
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
        while(developmentCardChosen==null){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return developmentCardChosen;
    }

    public synchronized boolean waitActiveEffect(){
        while(activeEffect==false){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }return activeEffect;
    }

    public void setIntChosen(int choice){
        this.intChosen=choice;
    }

    public void setTowerCardSpaceChosen(TowerCardSpace choice){
        this.towerCardSpaceChosen=choice;
    }

}
