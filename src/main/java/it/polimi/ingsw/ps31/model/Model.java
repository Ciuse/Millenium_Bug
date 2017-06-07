package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.server.message.MexVisitable;

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

    public void sendInformation(MexVisitable message) {
        this.setChanged();
        notifyObservers(message);
//        this.setChanged();
//        notifyObservers(new MexStateInfo(new StateCardBox("ciao",2, CardColor.BLUE)));
    }
}

