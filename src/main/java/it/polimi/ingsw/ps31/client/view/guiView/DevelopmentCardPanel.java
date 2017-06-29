package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.*;
//import it.polimi.ingsw.ps31.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 28/06/2017.
 */
public class DevelopmentCardPanel extends JPanel implements ActionListener{
    ActionListener listener;

    public DevelopmentCardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.52,0.48,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.30,0.454,0.156,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        JButton jButtonPanel = new ButtonCard("/devcards_f_en_c_1.png");
        jButtonPanel.setName("/devcards_f_en_c_1.png");
        jButtonPanel.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        //jButtonPanel.setBackground(Color.GREEN);
        //ImageIcon image1 = new ImageIcon("C:\\Users\\giulia\\Desktop\\LorenzoCards_compressed_png\\devcards_f_en_c_2.png");
        //jButtonPanel.setIcon(image1);

        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel,gbc);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String nameButton=jButton.getName();
        JFrame frame = new JFrame(nameButton);
        Container c = frame.getContentPane();
        ButtonCard buttonCardToEnlarge = new ButtonCard(jButton.getName());
        c.add(buttonCardToEnlarge);
        frame.setSize(350,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buttonCardToEnlarge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ButtonCard buttonCard = (ButtonCard)ev.getSource();
                frame.setVisible(false);

            }
        });




    }
}
