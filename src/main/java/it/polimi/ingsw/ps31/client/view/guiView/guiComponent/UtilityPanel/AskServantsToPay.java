package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.gameResource.Servant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 07/07/2017.
 */
public class AskServantsToPay extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private JFrame father;


    /* Constructor */
    public AskServantsToPay(JFrame father) {
        this.father=father;
    }

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }


    /**
     *metodo che mi permette di ottenere la risposta data dal giocatore  alla domanda
     */
    public String getInput(StateViewPlayer stateViewPlayer) {
    // uso finestra di dialogo in lettura
        int numberOfChoice = stateViewPlayer.getPlayerResources().getSpecificResource(Servant.class).getValue();
        String[] possibleValues = new String[numberOfChoice+1];
        for (int i = 0; i <= numberOfChoice; i++) {
            possibleValues[i] = valueOf(i);
        }

        Object choice=null;
        choice = JOptionPane.showInputDialog(father, "SELEZIONA QUANTI SERVITORI VUOI PAGARE", "PAY SERVANT",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if(choice!=null){
            return choice.toString();
        }
        else return "0";
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}