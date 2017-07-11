package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class AskListToPay extends JOptionPane implements ActionListener{
    private ActionListener listener;
    private JFrame father;

    /* Constructor */
    public AskListToPay(JFrame father) {
        this.father=father;
    }

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }



    public int getInput(int cardID) {
        // uso finestra di dialogo in lettura
        int numberOfChoice = 2 ;
        String[] possibleValues = new String[2];
        for (int i = 0; i < numberOfChoice; i++) {
            possibleValues[i] = valueOf(i);
        }

        String choice="";
        try {
            choice = JOptionPane.showInputDialog(father, "SELEZIONA IL NUMERO DELLA LISTA CHE VUOI USARE", "CARD: "+cardID+" LIST TO PAY",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]).toString();

        } catch (NullPointerException e) {
            return 0;
        }


        for (int i = 0; i <= numberOfChoice; i++) {
            if (i== new Integer(choice)) {
                return i;
            }
        }
        return 0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
