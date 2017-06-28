package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.server.VirtualView;

import java.util.Observable;

/**
 * Created by giulia on 01/06/2017.
 */
public class Model extends Observable {
    private final ModelChoices modelChoices;
    public Model() {
        modelChoices=new ModelChoices();
    }
    public void addVirtualView(VirtualView virtualView){
        this.addObserver(virtualView);
    }

    public void notifyViews(MVVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }

    public ModelChoices getModelChoices() {
        return modelChoices;
    }
}

