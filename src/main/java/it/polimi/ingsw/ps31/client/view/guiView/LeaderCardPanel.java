package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class LeaderCardPanel extends PaintBackgroundPanel implements  ActionListener {
    private ActionListener listener;
    ButtonCard buttonCard;

    public LeaderCardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonCard = new ButtonCard();
        buttonCard.setName("LeaderCard");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonCard.setOpaque(false);
        buttonCard.setBackground(Color.green);
        pane.add(buttonCard, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard jButton=(ButtonCard)e.getSource();
        String nameButton=jButton.getName();
        if(nameButton.equals("LeaderCard")){
        JFrame frame = new JFrame(nameButton);
        frame.setSize(300,500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)screenSize.getHeight()/8,(int)screenSize.getWidth()/16);
        frame.setAlwaysOnTop(true);
        frame.setSize(screenSize.width/2,screenSize.height/2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Container c = frame.getContentPane();
        //PersonalLeaderCardsPanel personalLeaderCardsPanel = new PersonalLeaderCardsPanel();
        //c.add(personalLeaderCardsPanel);
        }
    }
}
