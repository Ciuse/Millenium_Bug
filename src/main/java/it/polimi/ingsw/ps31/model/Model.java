package it.polimi.ingsw.ps31.model;

import it.polimi.ingsw.ps31.model.StateModel.InfoPlayer;
import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.gameThings.Coin;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.server.message.MesStateInfo;

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
        notifyObservers(new MesStateInfo(new InfoPlayer("ciao", PlayerColor.RED)));
        this.setChanged();
        notifyObservers(new MesStateInfo(new StateCardBox("ciao",2, CardColor.BLUE)));
    }
}

