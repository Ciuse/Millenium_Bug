package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 */
public class PlayerPanel extends JPanel implements ActionListener {


    public PlayerPanel() {

        addComponentsToPane(this);
    }


    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.07324, 0.486, 0.4867, 0.156,0.18,0.019, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.024, 0.5352, 0.6452, 0.1724, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        PersonalBonusTilesPanel jPersonalBonusTilesPanel = new PersonalBonusTilesPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jPersonalBonusTilesPanel.setBackground(Color.RED);
        jPersonalBonusTilesPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBonusTilesPanel, gbc);
        jPersonalBonusTilesPanel.attach(this);


        PersonalBoardPanel jPersonalBoardPanel = new PersonalBoardPanel();

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        //jPersonalBoardPanel.setBackground(Color.BLUE);
        //jPersonalBoardPanel.setOpaque(false);
        jPersonalBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBoardPanel, gbc);
        jPersonalBoardPanel.attach(this);

        ExtraCardPanel extraCardPanel = new ExtraCardPanel();

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //extraCardPanel.setBackground(Color.green);
        extraCardPanel.setOpaque(false);
        extraCardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(extraCardPanel, gbc);
        extraCardPanel.attach(this);


        LeaderCardPanel jLeaderCardPanel = new LeaderCardPanel();

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        jLeaderCardPanel.setBackground(Color.pink);
        jLeaderCardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jLeaderCardPanel, gbc);
        jLeaderCardPanel.attach(this);

        FamilyMemberPanel jFamilyMemberPanel = new FamilyMemberPanel();

        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        jFamilyMemberPanel.setBackground(Color.red);
        jFamilyMemberPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jFamilyMemberPanel, gbc);
        jFamilyMemberPanel.attach(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
//    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
//        BufferedImage resizedImage = new BufferedImage(width,height,type);
//        Graphics2D g = resizedImage.createGraphics();
//        g.drawImage(originalImage,0,0,width,height,null);
//        g.dispose();
//        return resizedImage;
//    }
}
