package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 28/06/2017.
 */
public class TowerCardAndActionSpacePanel extends JPanel implements ActionListener{
    private ActionListener listener;
    private String numberOfActionSpace;
    ButtonCard jButtonPanel = new ButtonCard();
    SingleSmallActionSpacePanel singleSmallActionSpacePanel = new SingleSmallActionSpacePanel();


    public TowerCardAndActionSpacePanel(String numberOfActionSpace) {
        this.numberOfActionSpace = numberOfActionSpace;
        addComponentsToPane();
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(){
        Container pane=this;
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.55,0.44999,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.25,0.551,0.199,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        jButtonPanel.setName(numberOfActionSpace);
        jButtonPanel.addActionListener(this);
        jButtonPanel.setContentAreaFilled(false);
        jButtonPanel.setPreferredSize(new Dimension(10,10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel,gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //singleSmallActionSpacePanel.setBackground(Color.green);
        singleSmallActionSpacePanel.setOpaque(false);
        singleSmallActionSpacePanel.setPreferredSize(new Dimension(10,10));
        pane.add(singleSmallActionSpacePanel,gbc);
    }

    public SingleSmallActionSpacePanel getSingleSmallActionSpacePanel() {
        return singleSmallActionSpacePanel;
    }

    public String getNumberStringOfActionSpace() {
        return numberOfActionSpace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }


    public ButtonCard getjButtonPanel() {
        return jButtonPanel;
    }
}
