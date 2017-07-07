package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.gameResource.Servant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 07/07/2017.
 */
public class AskServantsToPay extends JOptionPane implements ActionListener {
    private ActionListener listener;
    private String input;
    private JFrame father;

    public AskServantsToPay(JFrame father) {
        this.father=father;
    }


    public void attach (ActionListener listener){
        this.listener=listener;
    }



    public String getInput(StateViewPlayer stateViewPlayer) {
    // uso finestra di dialogo in lettura
        int numberOfChoice = stateViewPlayer.getPlayerResources().getSpecificResource(Servant.class).getValue();
        String[] possibleValues = new String[numberOfChoice+1];
        for (int i = 0; i <= numberOfChoice; i++) {
            possibleValues[i] = valueOf(i);
        }

        return JOptionPane.showInputDialog(father, "SELEZIONA QUANTI SERVITORI VUOI PAGARE", "0",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]).toString();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}