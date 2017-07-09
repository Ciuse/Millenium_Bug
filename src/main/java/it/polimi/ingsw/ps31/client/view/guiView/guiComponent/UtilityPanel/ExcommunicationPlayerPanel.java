package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getMax_number_of_Excommunication;
import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class ExcommunicationPlayerPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private JFrame father = null;
    private GuiView guiView;
    private ButtonCard[] excomm = new ButtonCard[3];

    public ExcommunicationPlayerPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }


    public void attach(ActionListener listener) {
        this.listener = listener;
    }

        public void addComponentsToPane(Container pane) {

            GridBagLayout gbl = new GridBagLayout();
            gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            gbl.rowHeights = new int[]{0, 0, 0, 0};

            gbl.columnWeights = new double[]{0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, Double.MIN_VALUE};
            gbl.rowWeights = new double[]{0.01, 0.98, 0.01, Double.MIN_VALUE};
            pane.setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();
            for (int i = 0; i < getMax_number_of_Excommunication(); i++) {
                excomm[i] = new ButtonCard();
                excomm[i].setName(valueOf(i));
                gbc.gridx = (2 * i) + 1;
                gbc.gridy = 1;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.BOTH;
                pane.add(excomm[i], gbc);
                excomm[i].addActionListener(this);

            }

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();
        for (int i = 0; i < getMax_number_of_Excommunication(); i++) {
            if (valueOf(i).equals(nameButton)) {


            }
        }
    }

    public void setFather(JFrame father) {
        this.father = father;
    }
}
