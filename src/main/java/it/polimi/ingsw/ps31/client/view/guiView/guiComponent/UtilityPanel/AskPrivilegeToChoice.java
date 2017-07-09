package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class AskPrivilegeToChoice extends JOptionPane implements ActionListener{
        private ActionListener listener;
        private JFrame father;

        public AskPrivilegeToChoice(JFrame father) {
            this.father=father;
        }


        public void attach (ActionListener listener){
            this.listener=listener;
        }



        public int getInput(List<String> resourceList) {
            // uso finestra di dialogo in lettura
            int numberOfChoice = resourceList.size();
            String[] possibleValues = new String[numberOfChoice];
            for (int i = 0; i < numberOfChoice; i++) {
                possibleValues[i] = resourceList.get(i);
            }

            String choice = JOptionPane.showInputDialog(father, "SELEZIONA LA LISTA DA OTTENERE", "COUNCIL PRIVILEGE",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]).toString();

            for (int i = 0; i <= numberOfChoice; i++) {
                if (resourceList.get(i).equals(choice)) {
                    return i;
                }
            }
            return 0;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

        }
}