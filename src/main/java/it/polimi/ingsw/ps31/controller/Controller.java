package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Controller implements Observer{
    private Model model;
    private View view;

    public void update(Observable o, Object arg) {
        VCMessageVisitor messageVisitor = new VCMessageVisitor();
        messageVisitor.setController(this);
        VCVisitable message = (VCVisitable) arg;
        message.accept(messageVisitor);
    }



}


