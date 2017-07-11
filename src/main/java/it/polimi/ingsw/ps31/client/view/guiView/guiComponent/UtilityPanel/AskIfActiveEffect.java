package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.model.choiceType.ChoiceIfActiveEffect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Giuseppe on 08/07/2017.
 * classe
 */
public class AskIfActiveEffect  extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private JFrame father;

    /* Constructor */
    public AskIfActiveEffect(JFrame father) {
        this.father=father;
    }

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }



    /**
     *metodo che mi permette di ottenere la risposta data dal giocatore
     */
    public Boolean getInput(ChoiceIfActiveEffect choiceIfActiveEffect) {
        // uso finestra di dialogo in lettura

        String[] possibleValues = new String[2];

        possibleValues[0] = "YES";
        possibleValues[1] = "NO";

        String choice = "";
        try {
            choice = JOptionPane.showInputDialog(father, "VUOI ATTIVARE L'EFFETTO DELLA CARTA: "+choiceIfActiveEffect.getCardIdEffect()+" ?", "CARD EFFECT ACTIVATION",
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