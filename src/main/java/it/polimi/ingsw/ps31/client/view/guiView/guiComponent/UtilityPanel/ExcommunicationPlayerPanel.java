package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewExcommunication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getMax_number_of_Excommunication;
import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 08/07/2017.
 */
public class ExcommunicationPlayerPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private JFrame father = null;
    private GuiView guiView;
    private PaintBackgroundPanel[] excomm = new PaintBackgroundPanel[3];

    /* Constructor */
    public ExcommunicationPlayerPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach(ActionListener listener) {
        this.listener = listener;
    }

    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
        public void addComponentsToPane(Container pane) {

            GridBagLayout gbl = new GridBagLayout();
            gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            gbl.rowHeights = new int[]{0, 0, 0, 0};

            gbl.columnWeights = new double[]{0.04, 0.2325, 0.04, 0.2325, 0.04, 0.2325, 0.04, Double.MIN_VALUE};
            gbl.rowWeights = new double[]{0.01, 0.98, 0.01, Double.MIN_VALUE};
            pane.setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();
            for (int i = 0; i < getMax_number_of_Excommunication(); i++) {
                excomm[i] = new PaintBackgroundPanel();
                excomm[i].setName(valueOf(i));
                excomm[i].imageToLoad("/excommunications/excomm_back_"+(i+1)+".png");
                gbc.gridx = (2 * i) + 1;
                gbc.gridy = 1;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.BOTH;
                pane.add(excomm[i], gbc);

            }

        }


    public void fillExcomm(List<StateViewExcommunication> stateViewExcommunicationList) {
        if (!stateViewExcommunicationList.isEmpty()) {
            for (StateViewExcommunication excommunication : stateViewExcommunicationList
                    ) {
                if (excommunication.getId() != 0) {
                    if (excommunication.getPeriod() == 1) {
                        excomm[0].imageToReprint("/excommunications/excomm_1_" + valueOf(excommunication.getId()) + ".png");
                    }
                    if (excommunication.getPeriod() == 2) {
                        excomm[1].imageToReprint("/excommunications/excomm_2_" + valueOf(excommunication.getId() - 7) + ".png");
                    }
                    if (excommunication.getPeriod() == 3) {
                        excomm[2].imageToReprint("/excommunications/excomm_3_" + valueOf(excommunication.getId() - 14) + ".png");
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void setFather(JFrame father) {
        this.father = father;
    }
}
