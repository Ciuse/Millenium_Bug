package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.message.MessageExample;
import it.polimi.ingsw.ps31.message.Messaggio1;
import it.polimi.ingsw.ps31.message.Messaggio2;
import it.polimi.ingsw.ps31.view.View;

import java.util.Observable;

/**
 * Created by giulia on 01/06/2017.
 */
public class Model extends Observable {

    public Model(View view, View view2) {
        this.addObserver(view);
        this.addObserver(view2);

    }

    public void ask() {
        this.setChanged();
        System.out.println(this.hasChanged());
        MessageExample messageExample = new Messaggio2();
        notifyObservers(messageExample);
    }
}

