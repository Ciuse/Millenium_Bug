package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.model.gameResource.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 29/06/2017.
 * classe che mi rappresenta il pannello contenente tutti valori delle risorse del giocatore
 * @see JLabel
 */
public class PlayerResourcesPanel extends JPanel implements ActionListener{
    ActionListener listener;
    private JLabel[] physicalResources = new JLabel[4];
    private JLabel[] pointResources = new JLabel[3];


    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }


    /* Constructor */
    public PlayerResourcesPanel() {
        addComponentsToPane(this);
    }

    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0};

        gbl.columnWeights = new double[]{0.01428,0.01428,0.01428,0.01428,0.01428,0.01428,0.01428, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        physicalResources[0] = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        physicalResources[0].setName(Coin.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        physicalResources[0].setOpaque(false);
        physicalResources[0].setHorizontalAlignment( JLabel.CENTER );
        pane.add( physicalResources[0], gbc);

        physicalResources[1] = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        physicalResources[1].setName(Wood.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        physicalResources[1].setOpaque(false);
        physicalResources[1].setHorizontalAlignment( JLabel.CENTER );
        pane.add( physicalResources[1], gbc);

        physicalResources[2] = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        physicalResources[2].setName(Stone.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        physicalResources[2].setOpaque(false);
        physicalResources[2].setHorizontalAlignment( JLabel.CENTER );
        pane.add( physicalResources[2], gbc);

        physicalResources[3] = new JLabel();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        physicalResources[3].setName(Servant.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        physicalResources[3].setOpaque(false);
        physicalResources[3].setHorizontalAlignment( JLabel.CENTER );
        pane.add( physicalResources[3], gbc);


        pointResources[0] = new JLabel();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        pointResources[0].setName(MilitaryStrength.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        pointResources[0].setOpaque(false);
        pointResources[0].setHorizontalAlignment( JLabel.CENTER );
        pane.add( pointResources[0], gbc);

        pointResources[1] = new JLabel();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        pointResources[1].setName(FaithPoint.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        pointResources[1].setOpaque(false);
        pointResources[1].setHorizontalAlignment( JLabel.CENTER );
        pane.add( pointResources[1], gbc);

        pointResources[2] = new JLabel();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        pointResources[2].setName(VictoryPoint.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        pointResources[2].setOpaque(false);
        pointResources[2].setHorizontalAlignment( JLabel.CENTER );
        pane.add( pointResources[2], gbc);

        }


    /**
     * Metodo che mi permette di visualizzare il valore delle physical resources nello spazio apposita creato
     */
    public void setStringPhysicalResources(ResourceList list){
        for(int i=0;i<4;i++){
            for (Resource resource :list.getListOfResource()
                    ) {
                if(resource.getClass().getSimpleName().equals( physicalResources[i].getName())){
                    physicalResources[i].setText(valueOf(resource.getPhysicalResourceValue()));
                }
            }
        }
    }

    /**
     * Metodo che mi permette di visualizzare il valore delle point resources nello spazio apposita creato
     */
    public void setStringPointResources(ResourceList list){
        for(int i=0;i<3;i++){
            for (Resource resource :list.getListOfResource()
                    ) {
                if(resource.getClass().getSimpleName().equals(pointResources[i].getName())){
                    pointResources[i].setText(valueOf(resource.getPointResourceValue()));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
