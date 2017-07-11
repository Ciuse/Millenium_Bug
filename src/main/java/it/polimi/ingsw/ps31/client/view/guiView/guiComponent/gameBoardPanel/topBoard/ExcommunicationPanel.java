package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewExcommunication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 30/06/2017.
 * è la classe che rappresenta il pannello delle scomuniche
 * @see ButtonCard
 */
public class ExcommunicationPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonExcommunication1;
    private ButtonCard buttonExcommunication2;
    private ButtonCard buttonExcommunication3;

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

        /* Constructor */
    public ExcommunicationPanel() {
        addComponentsToPane(this);
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     * inoltre i bottoni che rappresentano le scomuniche dei tre periodi , vengono creati con un'immagine di default e poi man mano
     * cambia l'immagine in base a quelle pescate
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.333333,0.333333,0.333333, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.15,0.66,0.1899, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonExcommunication1 = new ButtonCard();
        buttonExcommunication1.setName("ExcommunicationFirstPeriod");
        buttonExcommunication1.imageToLoad("/excommunications/excomm_back_1.png");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonExcommunication1.setPreferredSize(new Dimension(10,10));
        buttonExcommunication1.addActionListener(this);
        pane.add(buttonExcommunication1,gbc);



        buttonExcommunication2 = new ButtonCard();
        buttonExcommunication2.setName("ExcommunicationSecondPeriod");
        buttonExcommunication2.imageToLoad("/excommunications/excomm_back_2.png");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonExcommunication2.setPreferredSize(new Dimension(10,10));
        buttonExcommunication2.addActionListener(this);
        pane.add(buttonExcommunication2,gbc);

        buttonExcommunication3 = new ButtonCard();
        buttonExcommunication3.setName("ExcommunicationThirdPeriod");
        buttonExcommunication3.imageToLoad("/excommunications/excomm_back_3.png");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonExcommunication3.setPreferredSize(new Dimension(10,10));
        buttonExcommunication3.addActionListener(this);
        pane.add(buttonExcommunication3,gbc);
    }


    /**
     * Metodo che mi permette di stampare le scomuniche
     */
    public void printExcommunication(List<StateViewExcommunication> stateViewExcommunicationList){

        for (StateViewExcommunication excommunication:stateViewExcommunicationList
             ) {
            if(excommunication.getId()!=0) {
                if (excommunication.getPeriod() == 1) {
                    buttonExcommunication1.imageToReprint("/excommunications/excomm_1_" + valueOf(excommunication.getId()) + ".png");
                }
                if (excommunication.getPeriod() == 2) {
                    buttonExcommunication2.imageToReprint("/excommunications/excomm_2_" + valueOf(excommunication.getId() - 7) + ".png");
                }
                if (excommunication.getPeriod() == 3) {
                    buttonExcommunication3.imageToReprint("/excommunications/excomm_3_" + valueOf(excommunication.getId() - 14) + ".png");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
