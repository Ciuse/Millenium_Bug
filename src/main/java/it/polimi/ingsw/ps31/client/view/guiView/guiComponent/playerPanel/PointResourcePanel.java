package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.model.gameResource.*;

import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 08/07/2017.
 */
public class PointResourcePanel extends PaintBackgroundPanel {
    private ActionListener listener;
    private SinglePointResourcePanel[] singlePointResourcePanel = new SinglePointResourcePanel[3];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public PointResourcePanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.08,0.2533,0.08,0.2533,0.08,0.2533, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.15, 0.70, 0.15, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        singlePointResourcePanel[0] = new SinglePointResourcePanel();
        singlePointResourcePanel[0].setName("1");
        //singlePointResourcePanel[0].setBackground(Color.white);
        singlePointResourcePanel[0].imageToLoad("/ImmaginePuntiMilitare.png");
        singlePointResourcePanel[0].setName(MilitaryStrength.class.getSimpleName());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(singlePointResourcePanel[0], gbc);

        singlePointResourcePanel[1] = new SinglePointResourcePanel();
        singlePointResourcePanel[1].setName("2");
        //singlePointResourcePanel[1].setBackground(Color.white);
        singlePointResourcePanel[1].imageToLoad("/ImmaginePuntiVittoria.png");
        singlePointResourcePanel[1].setName(VictoryPoint.class.getSimpleName());
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(singlePointResourcePanel[1], gbc);

        singlePointResourcePanel[2] = new SinglePointResourcePanel();
        singlePointResourcePanel[2].setName("3");
        //singlePointResourcePanel[2].setBackground(Color.white);
        singlePointResourcePanel[2].imageToLoad("/ImmaginePuntiFede.png");
        singlePointResourcePanel[2].setName(FaithPoint.class.getSimpleName());
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(singlePointResourcePanel[2], gbc);


    }

    public void setString(ResourceList list){
        for(int i=0;i<3;i++){
            for (Resource resource :list.getListOfResource()
                    ) {
                if(resource.getClass().getSimpleName().equals(singlePointResourcePanel[i].getName())){
                    singlePointResourcePanel[i].getResourceLabel().setText(valueOf(resource.getPhysicalResourceValue()));
                }
            }
        }
    }
}
