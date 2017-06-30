package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 29/06/2017.
 */
public class DevelopmentCardOnPersonalBoardPanel extends JPanel implements ActionListener {
    ActionListener listener;

    public DevelopmentCardOnPersonalBoardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,};

        gbl.columnWeights = new double[]{0.1667,0.1667,0.1667,0.1667,0.1667,0.1667,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        ButtonCard jButtonPanel1 = new ButtonCard();
        jButtonPanel1.setName("1");
        jButtonPanel1.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel1,gbc);

        ButtonCard jButtonPanel2 = new ButtonCard();
        jButtonPanel2.setName("2");
        jButtonPanel2.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel2,gbc);

        ButtonCard jButtonPanel3 = new ButtonCard();
        jButtonPanel3.setName("3");
        jButtonPanel3.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel3,gbc);

        ButtonCard jButtonPanel4 = new ButtonCard();
        jButtonPanel4.setName("4");
        jButtonPanel4.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel4,gbc);

        ButtonCard jButtonPanel5 = new ButtonCard();
        jButtonPanel5.setName("5");
        jButtonPanel5.addActionListener(this);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel5,gbc);

        ButtonCard jButtonPanel6 = new ButtonCard();
        jButtonPanel6.setName("6");
        jButtonPanel6.addActionListener(this);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel6,gbc);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String nameButton=jButton.getName();
        JFrame frame = new JFrame(nameButton);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)screenSize.getHeight()/8,(int)screenSize.getWidth()/16);
        frame.setAlwaysOnTop(true);
        frame.setSize(screenSize.width/8,screenSize.height/3);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frame.getContentPane();
        ButtonCard buttonCardToEnlarge = new ButtonCard();
        c.add(buttonCardToEnlarge);
        buttonCardToEnlarge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ButtonCard buttonCard = (ButtonCard)ev.getSource();
                frame.setVisible(false);

            }
        });
    }
}
