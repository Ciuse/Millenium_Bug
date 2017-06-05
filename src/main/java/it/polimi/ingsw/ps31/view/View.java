package it.polimi.ingsw.ps31.view;

import it.polimi.ingsw.ps31.message.MessageExample;
import it.polimi.ingsw.ps31.message.Messaggio1;
import it.polimi.ingsw.ps31.message.Messaggio2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia on 01/06/2017.
 */
public class View implements Observer {



    @Override
    public void update(Observable o, Object arg) {
        MessageExample message = (MessageExample) arg;
        message.doSomething();
    }



}