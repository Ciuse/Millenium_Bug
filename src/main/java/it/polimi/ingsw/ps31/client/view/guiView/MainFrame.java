package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by giulia on 25/06/2017.
 */
public class MainFrame extends JFrame implements ActionListener {
    private GameBoardPanel gameBoardPanel;
    private PlayerPanel playerPanel;
    private UtilityPanel utilityPanel;

    public MainFrame(GameBoardPanel gameBoardPanel, PlayerPanel playerPanel){
        this.gameBoardPanel = gameBoardPanel;
        this.playerPanel = playerPanel;
    }



    public static void main(String[] v) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("LORENZO IL MAGNIFICO ");
                frame.setSize(screenSize);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().setBackground(new Color(45,55,105));

                try {

                    GridBagLayout gbl = new GridBagLayout();
                    gbl.columnWidths = new int[]{0, 0, 0, 0};
                    gbl.rowHeights = new int[]{0, 0, 0};

                    gbl.columnWeights = new double[]{0.50, 0.014, 0.481, Double.MIN_VALUE};
                    gbl.rowWeights = new double[]{0.5267, 0.469, Double.MIN_VALUE};
                    frame.getContentPane().setLayout(gbl);


                    GameBoardPanel jGameBoardPanel1 = new GameBoardPanel();

                    PlayerPanel jPlayerPanel = new PlayerPanel();

                    UtilityPanel jUtilityPanel = new UtilityPanel();



                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridheight = 2;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    jGameBoardPanel1.setPreferredSize(new Dimension(10, 10));
                    frame.add(jGameBoardPanel1, gbc);


                    gbc.gridx = 2;
                    gbc.gridy = 0;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    //gbc.anchor = GridBagConstraints.WEST;
                    jPlayerPanel.setPreferredSize(new Dimension(10, 10));
                    frame.add(jPlayerPanel, gbc);

                    gbc.gridx = 2;
                    gbc.gridy = 1;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    jUtilityPanel.setPreferredSize(new Dimension(10, 10));
                    frame.add(jUtilityPanel, gbc);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                //PlayerPanel playerPanel = new PlayerPanel();


            }
        });


        //diminuisco la grandezza dell gameBoard

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
     //   BufferedImage resizedImage = new BufferedImage(width, height, type);
       // Graphics2D g = resizedImage.createGraphics();
        //g.drawImage(originalImage, 0, 0, width, height, null);
        //g.dispose();
        //return resizedImage;
    //}

}
