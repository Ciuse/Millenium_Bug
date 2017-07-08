package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.gameResource.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 29/06/2017.
 */
public class PlayerResourcesPanel extends JPanel implements ActionListener{
    ActionListener listener;
    private SpecificResourcePanel[] resources = new SpecificResourcePanel[4];


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public PlayerResourcesPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0};

        gbl.columnWeights = new double[]{0.15,0.15,0.15,0.13,0.42, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        resources[0] = new SpecificResourcePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        resources[0].setName(Coin.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        resources[0].setOpaque(false);
        //resources[i].setBackground(Color.BLUE);
        pane.add(resources[0], gbc);

        resources[1] = new SpecificResourcePanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        resources[1].setName(Wood.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        resources[1].setOpaque(false);
        //resources[i].setBackground(Color.BLUE);
        pane.add(resources[1], gbc);

        resources[2] = new SpecificResourcePanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        resources[2].setName(Stone.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        resources[2].setOpaque(false);
        //resources[i].setBackground(Color.BLUE);
        pane.add(resources[2], gbc);

        resources[3] = new SpecificResourcePanel();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        resources[3].setName(Servant.class.getSimpleName());
        gbc.fill = GridBagConstraints.BOTH;
        resources[3].setOpaque(false);
        //resources[i].setBackground(Color.BLUE);
        pane.add(resources[3], gbc);
        }



    public void setString(ResourceList list){
        for(int i=0;i<4;i++){
            for (Resource resource :list.getListOfResource()
                    ) {
                if(resource.getClass().getSimpleName().equals(resources[i].getName())){
                    resources[i].getResource().setText(valueOf(resource.getPhysicalResourceValue()));
                }
            }
        }
    }
//
//    public Class<? extends PhysicalResource> getSpecificResourceFromPanel(String string){
//        if(string.equals(valueOf(0))){
//            return Coin.class;
//        }if(string.equals(valueOf(1))){
//            return Wood.class;
//        }if(string.equals(valueOf(2))){
//            return Stone.class;
//        }if(string.equals(valueOf(3))){
//            return Servant.class ;
//        }else return null;
//    }

    public SpecificResourcePanel[] getResources() {
        return resources;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
