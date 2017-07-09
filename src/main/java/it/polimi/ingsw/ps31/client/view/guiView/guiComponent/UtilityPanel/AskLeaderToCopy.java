package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class AskLeaderToCopy extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private JFrame father;

    public AskLeaderToCopy(JFrame father) {
        this.father=father;
    }


    public void attach (ActionListener listener){
        this.listener=listener;
    }


    public String getInput() {
        // uso finestra di dialogo in lettura
        int numberOfChoice = 20;
        String[] possibleValues = new String[numberOfChoice];
        for (int i = 0; i < numberOfChoice; i++) {
            possibleValues[i] = valueOf(i+1);
        }

        String choice="";
        try {
            choice= JOptionPane.showInputDialog(father, "SELEZIONA L'ID DEL LEADER DA COPIARE", "COPY LEADER",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]).toString();
        } catch (NullPointerException e) {

            return "0";
        }

        return choice;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}