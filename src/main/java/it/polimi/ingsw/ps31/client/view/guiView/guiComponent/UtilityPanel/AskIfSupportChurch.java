package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class AskIfSupportChurch extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private JFrame father;

    public AskIfSupportChurch(JFrame father) {
        this.father=father;
    }


    public void attach (ActionListener listener){
        this.listener=listener;
    }



    public Boolean getInput() {
        // uso finestra di dialogo in lettura

        String[] possibleValues = new String[2];

        possibleValues[0] = "YES";
        possibleValues[1] = "NO";

        String choice = "";
        try {
            choice = JOptionPane.showInputDialog(father, "VUOI DARE IL TUO SUPPORTO ALLA CHIESA?", "VATICAN REPORT",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]).toString();
        } catch (NullPointerException e) {
            choice = "NO";
        }


        if (choice.equals("YES")){
            return true;
        }
        if(choice.equals("NO")){
            return false;
        }

        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
