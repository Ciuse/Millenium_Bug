package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Created by giulia on 01/06/2017.
 */
public class Model extends Observable {

    public Model() {
    }
    public void addView(View view){
        this.addObserver(view);
    }

    public void notifyViews(MVVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }

}

