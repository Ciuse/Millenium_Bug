package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;

import java.util.Observable;

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

