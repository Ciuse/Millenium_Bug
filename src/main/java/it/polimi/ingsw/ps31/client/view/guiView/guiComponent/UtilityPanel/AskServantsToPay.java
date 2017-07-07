package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 07/07/2017.
 */
public class AskServantsToPay extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private String input;

    public void attach (ActionListener listener){
        this.listener=listener;
    }



    public String getInput(){
// uso finestra di dialogo in lettura
        input=JOptionPane.showInputDialog("QUANTI SERVITORI VUOI PAGARE PER AUMENTARE IL VALORE DEL TUO FAMILY MEMBER? ");
        Object[] possibleValues = { "prima", "seconda" };
        Object sel_input = JOptionPane.showInputDialog(null, "Scegli", "0");
        input = sel_input.toString();
        return input;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}