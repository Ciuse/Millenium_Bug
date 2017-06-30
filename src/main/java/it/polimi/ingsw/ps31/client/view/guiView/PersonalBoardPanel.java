package it.polimi.ingsw.ps31.client.view.guiView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBoardPanel extends PaintBackgroundPanel implements ActionListener{
    private ActionListener listener;


    public void paintComponent(Graphics g) {
        super.imageToLoad("/personalBoard.jpg");
        super.paintComponent(g);
    }


    public PersonalBoardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0,0};


        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.39, 0.05, 0.39, 0.03,0.23 ,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        DevelopmentCardOnPersonalBoardPanel jPanel1 = new DevelopmentCardOnPersonalBoardPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jPanel1.setBackground(Color.CYAN);
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel1, gbc);
        jPanel1.attach(this);

        DevelopmentCardOnPersonalBoardPanel jPanel2 = new DevelopmentCardOnPersonalBoardPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jPanel2.setBackground(Color.red);
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel2, gbc);
        jPanel2.attach(this);

        PlayerResourcesPanel jPanel3 = new PlayerResourcesPanel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jPanel3.setBackground(Color.green);
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel3, gbc);
        jPanel3.attach(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
