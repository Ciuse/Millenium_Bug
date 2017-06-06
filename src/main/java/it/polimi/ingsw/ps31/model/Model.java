package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.gameThings.Coin;
import it.polimi.ingsw.ps31.server.message.MessageToString;
import it.polimi.ingsw.ps31.server.message.Visitable;
import it.polimi.ingsw.ps31.server.message.Messaggio2;
import it.polimi.ingsw.ps31.client.view.View;

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
        notifyObservers(new MessageToString(new Coin(3)));
    }
}

