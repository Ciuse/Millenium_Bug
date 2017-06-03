package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.message.MessageEsemple;
import it.polimi.ingsw.ps31.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia on 01/06/2017.
 */
public class Model extends Observable {
private  String  args = "";

    public Model(View view) {
            this.addObserver(view);
        }

    public void ask(String args) {
        this.args=args;
        setChanged();
        notifyObservers(this.args);
    }
}

