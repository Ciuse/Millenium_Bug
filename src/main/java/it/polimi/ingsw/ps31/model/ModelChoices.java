package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.effect.Effect;

import java.util.Observer;

import static java.lang.Thread.sleep;

/**
 * Created by giulia on 15/06/2017.
 */
public class ModelChoices extends Model {
    int intChosen=-1;
    TowerCardSpace towerCardSpaceChosen=null;
    DevelopmentCard developmentCardChosen = null;
    boolean activeEffect = false;

    public void notifyViews(MVVisitable message) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
            Object[] arrLocal;
            arrLocal = obs.toArray();
            synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */

            for (int i = arrLocal.length-1; i>=0; i--)
                ((Observer)arrLocal[i]).update(this, message);
        }
    }

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
