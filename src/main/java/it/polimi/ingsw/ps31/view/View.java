package it.polimi.ingsw.ps31.view;

import it.polimi.ingsw.ps31.message.MessageEsemple;
import it.polimi.ingsw.ps31.model.Model;

import java.security.MessageDigest;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia on 01/06/2017.
 */
public class View implements Observer {
    @Override
    public void update(Observable o, Object args) {
        System.out.println("ho ricevuto il messaggio: " +args);
    }

}
